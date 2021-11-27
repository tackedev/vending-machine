package com.tackedev.vendingmachine.dto;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author tackedev
 */
public class Award {

    private boolean isGiven;

    private static final String PATH = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    private static final String FILENAME = "award.properties";
    private static final Properties PROPERTIES;

    private static Award instance;

    static {
        PROPERTIES = new Properties();
        try {
            PROPERTIES.load(new FileInputStream(PATH + FILENAME));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private Award() {
    }

    public static Award getInstance() {
        if (instance == null) {
            instance = new Award();
        }
        return instance;
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

    public boolean isGiven() {
        return isGiven;
    }

    public void setGiven(boolean given) {
        isGiven = given;
    }
}
