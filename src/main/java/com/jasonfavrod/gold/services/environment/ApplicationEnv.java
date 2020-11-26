package com.jasonfavrod.gold.services.environment;

import java.util.Map;

public abstract class ApplicationEnv {
    protected Map<String, String> systemEnv;

    public ApplicationEnv() {
        systemEnv = System.getenv();
    }

    public abstract boolean isProd();
    public abstract boolean isNonProd();
    public abstract boolean isDev();

    /**
     * Get the value of the given var from the System environment.
     * If the variable does not exist, return null.
     * @param var A System environment variable.
     * @return The value of the given var, or null.
     */
    public String resolveEnvVar(String var) {
        if (systemEnv.containsKey(var)) {
            System.out.println("resolving " + var + " to " + systemEnv.get(var));
            return systemEnv.get(var);
        }
        System.out.println("resolving " + var + " to null");
        return null;
    }
}
