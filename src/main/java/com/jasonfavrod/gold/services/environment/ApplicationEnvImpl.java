package com.jasonfavrod.gold.services.environment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class ApplicationEnvImpl extends ApplicationEnv {
    @Autowired Logger logger;
    @Autowired Environment env;

    private static APP_ENV appEnv = null;

    @Override
    public boolean isProd() {
        return getAppEnv() == APP_ENV.PROD;
    }

    @Override
    public boolean isNonProd() {
        return getAppEnv() == APP_ENV.NON_PROD;
    }

    @Override
    public boolean isDev() {
        return getAppEnv() == APP_ENV.DEV;
    }

    private APP_ENV getAppEnv() {
        if (appEnv == null) {
            try {
                if (resolveEnvVar("APP_ENV").equalsIgnoreCase("PROD")) {
                    appEnv = APP_ENV.PROD;
                    logger.info("APP_ENV=PROD");
                }
                else if (resolveEnvVar("APP_ENV").equalsIgnoreCase("NON_PROD")) {
                    appEnv = APP_ENV.NON_PROD;
                    logger.info("APP_ENV=NON_PROD");
                }
                else {
                    appEnv = APP_ENV.DEV;
                    logger.info("APP_ENV=DEV");
                }
            }
            catch (Exception ex) {
                logger.warning("APP_ENV: Unable to determine APP_ENV: " + ex.getMessage());
                ex.printStackTrace();
            }
        }

        return appEnv;
    }
}
