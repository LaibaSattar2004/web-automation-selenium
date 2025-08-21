package com.assignment.core;

import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static final Properties PROPS = new Properties();

    static {
        try (InputStream is = Config.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (is != null) {
                PROPS.load(is);
            } else {
                System.err.println("️ config.properties not found in resources. Using defaults only.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Unable to load config.properties", e);
        }
    }

    /**
     * Get a property value by key.
     * Looks first in System properties (e.g. -Dbrowser=firefox),
     * then in config.properties.
     */
    public static String get(String key) {
        return System.getProperty(key, PROPS.getProperty(key));
    }

    /**
     * Get a property value with a default.
     * Example: Config.get("browser", "chrome")
     */
    public static String get(String key, String def) {
        return System.getProperty(key, PROPS.getProperty(key, def));
    }
}
