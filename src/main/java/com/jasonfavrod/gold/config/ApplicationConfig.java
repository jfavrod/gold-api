package com.jasonfavrod.gold.config;
import com.jasonfavrod.gold.GoldApplication;
import com.jasonfavrod.gold.gateways.prices.PricesGateway;
import com.jasonfavrod.gold.gateways.prices.PricesMySqlGateway;
import com.jasonfavrod.gold.gateways.prices.PricesPostgresGateway;
import com.jasonfavrod.gold.services.environment.ApplicationEnv;
import com.jasonfavrod.gold.services.environment.ApplicationEnvImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.naming.ConfigurationException;
import java.util.logging.Logger;

@Configuration
@PropertySource("classpath:application.properties")
public class ApplicationConfig {
    @Value("${app_url.dev}") public String devUrl;
    @Value("${app_url.non_prod}") public String nonProdUrl;
    @Value("${app_url.prod}") public String prodUrl;

    private static final String className = ApplicationConfig.class.getName().replace("com.jasonfavrod.gold.config.", "");

    @Autowired private ApplicationEnv appEnv;
    @Autowired private Logger logger;

    @Bean
    public PricesGateway pricesGateway() {
        PricesGateway prices = new PricesPostgresGateway();

        try {
            if (appEnv.isProd()) {
                prices = new PricesPostgresGateway();
            }
            else if (appEnv.isNonProd()) {
                prices = new PricesMySqlGateway();
            }
            else if (appEnv.isDev()) {
                prices = new PricesPostgresGateway();
            }
            else {
                throw new ConfigurationException("Failed to determine APP_ENV");
            }
        }
        catch (Exception ex) {
            logger.warning(className + ": failed to determine APP_ENV, using default PricesGateway.");
        }

        return prices;
    }

    @Bean
    public Logger logger() {
        return Logger.getLogger(GoldApplication.class.getName());
    }

    @Bean
    public ApplicationEnv appEnv() {
        return new ApplicationEnvImpl();
    }
}
