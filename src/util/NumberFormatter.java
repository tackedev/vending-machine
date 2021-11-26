package util;

import java.text.DecimalFormat;

/**
 * @author tackedev
 */
public class NumberFormatter {

    private static final DecimalFormat FORMATTER = new DecimalFormat("###,###");

    public static String formatToVND(int value) {
        return String.format("%7s", FORMATTER.format(value));
    }

}
