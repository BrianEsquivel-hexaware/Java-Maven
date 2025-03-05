package org.example.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtils {

    public static String getProperty(String key) throws IOException {
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("resources.application").getPath();
        String appConfigPath = rootPath + "application.properties";

        Properties props = new Properties();
        props.load(new FileInputStream(appConfigPath));

        String value = props.getProperty(key);

        return value;
    }

    public static String getUserApp(){
        return System.getProperty("user.name");
    }

    public static String getUserPass(){
        return System.getProperty("user.pass");
    }
}
