package com.illiaiievliev.learning.dao.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {
    private static final Logger log = LogManager.getLogger(PropertiesUtil.class);
    private static final String APPLICATION_PROPERTIES_FILE_NAME = "application.properties";
    private static final String FILE_FOR_DB_IS_NOT_LOADED_OR_PASS_ERRORS = "Properties file for db is not loaded or pass errors";

    private PropertiesUtil() {
    }

    public static final Properties PROPERTIES = new Properties();

    static {
        loadProperties();
    }

    public static String get(String key) {
        return PROPERTIES.getProperty(key);
    }

    private static void loadProperties() {
        try (var inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream(APPLICATION_PROPERTIES_FILE_NAME)) {
            PROPERTIES.load(inputStream);
        } catch (IOException e) {
            log.fatal(FILE_FOR_DB_IS_NOT_LOADED_OR_PASS_ERRORS, e);
            throw new RuntimeException(FILE_FOR_DB_IS_NOT_LOADED_OR_PASS_ERRORS, e);
        }
    }
}
