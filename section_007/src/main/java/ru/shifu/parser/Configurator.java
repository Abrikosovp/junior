package ru.shifu.parser;

import java.io.FileInputStream;
import java.util.Properties;
/**
 * Configurator

 * @author Pavel Abrikosov(abrikosovp@mail.ru)
 * @version 0.1$
 * @since 0.1
 * 18.12.2018
 */
public class Configurator {
    private final Properties properties = new Properties();

    public Configurator() {
        String path = getClass().getResource("/app.properties").getPath();
        try (FileInputStream fileInputStream = new FileInputStream(path)) {
            this.properties.load(fileInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getValue(String key) {
        return this.properties.getProperty(key);
    }
}
