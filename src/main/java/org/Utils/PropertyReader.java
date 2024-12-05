package org.Utils;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyReader {
    public PropertyReader() {
    }

    public static String readKey(String key) {

        FileInputStream file = null;
        Properties props = null;
        try {
            file = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/data.properties");
            props = new Properties();
            props.load(file);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                file.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return props.getProperty(key);
    }
}
