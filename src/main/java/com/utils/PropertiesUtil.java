package com.utils;

import com.constants.FrameworkConstants;
import com.enums.ConfigProperties;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import java.util.Properties;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PropertiesUtil {

    private static Properties properties = new Properties();
    private static final HashMap<String, String> CONFIGMAP = new HashMap<>();

    static {
        try (FileInputStream fis = new FileInputStream(FrameworkConstants.getConfigPropertiesPath())) {
            properties.load(fis);
            properties.entrySet().forEach(entry -> CONFIGMAP.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue())));

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public static String getValue(ConfigProperties key) {
        if (Objects.isNull(CONFIGMAP.get(key.toString().toLowerCase())) || Objects.isNull(key)) {
            new RuntimeException("The given property or value is null, please check the property " + key);
        }
        
        return CONFIGMAP.get(key.toString().toLowerCase());
    }
}