package br.com.dbccompany.util;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static br.com.dbccompany.util.DefaultValue.CONFIG_PROPERIES;

public class ConfigProperties {
    public static Properties properties;
    private static String configPath = CONFIG_PROPERIES;
    public static void initializePropertyFile(){
        properties = new Properties();
        try {
            InputStream instm = new FileInputStream(configPath);
            properties.load(instm);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
