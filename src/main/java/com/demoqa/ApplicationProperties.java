package com.demoqa;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

public class ApplicationProperties {

    private static final Properties properties;

    static {
        String propertiesFilePath = "src/main/resources/application.properties";
        properties = new Properties();
        try (BufferedReader reader = new BufferedReader(new FileReader(propertiesFilePath))) {
            properties.load(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getBaseUrl() {
        return getProperty("base.url");
    }

    private static String getProperty(String propertyKey) {
        return Optional.of(propertyKey)
                .map(properties::getProperty)
                .orElseThrow(() -> new RuntimeException(propertyKey + " is not found"));
    }
}
