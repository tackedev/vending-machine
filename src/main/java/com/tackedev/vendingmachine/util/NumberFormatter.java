package com.tackedev.vendingmachine.util;

import java.text.DecimalFormat;

/**
 * @author tackedev
 */
public class NumberFormatter {

    private static final DecimalFormat FORMATTER = new DecimalFormat("#,###,###");

    public static String formatToVND(int value) {
        return String.format("%9s", FORMATTER.format(value)) + " VND";
    }

}
