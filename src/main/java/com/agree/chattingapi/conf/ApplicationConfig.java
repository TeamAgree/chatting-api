package com.agree.chattingapi.conf;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApplicationConfig {
    private Properties properties;
    private static final Logger log = (Logger) LoggerFactory.getLogger(ApplicationConfig.class);

    public ApplicationConfig() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties")) {
            properties = new Properties();

            if (input == null) {
                log.error("Sorry, unable to find application.properties");
                return;
            }

            properties.load(input);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String getJWTKey() {
        return properties.getProperty("jwt.secret-key");
    }
}

