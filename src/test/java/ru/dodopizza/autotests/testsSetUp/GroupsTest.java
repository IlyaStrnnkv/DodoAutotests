package ru.dodopizza.autotests.testsSetUp;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static java.lang.System.getProperty;

/**
 * The interface to define groups of tests
 */
public interface GroupsTest {
    String base = "base";
    String smoke = "smoke";
    String regress = "regress";


    default Properties getPropertiesXml() {
        Properties properties = new Properties();
        try {
            properties.loadFromXML(new FileInputStream(getProperty("config.location.env")));
        } catch (IOException exception) {
            throw new IllegalArgumentException("Property file not found");
        }
        return properties;
    }
}