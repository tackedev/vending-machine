package com.tackedev.vendingmachine.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author tackedev
 */
public class AwardConfig {

    private static final String PATH;
    private static final String FILENAME = "award.properties";
    private static final Properties PROPERTIES;

    static {
        PATH = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        PROPERTIES = new Properties();
        try {
            PROPERTIES.load(new FileInputStream(PATH + FILENAME));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static int getStreak() {
        return Integer.parseInt(PROPERTIES.getProperty("streak"));
    }

    public static double getRate() {
        return Double.parseDouble(PROPERTIES.getProperty("rate"));
    }

    public static double getIncreasedRate() {
        return Double.parseDouble(PROPERTIES.getProperty("increased_rate"));
    }

    public static int getBudget() {
        return Integer.parseInt(PROPERTIES.getProperty("budget"));
    }

}
