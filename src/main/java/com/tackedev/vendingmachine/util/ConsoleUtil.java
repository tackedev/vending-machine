package com.tackedev.vendingmachine.util;

import java.util.Scanner;

/**
 * @author tackedev
 */
public class ConsoleUtil {

    private static final Scanner SCANNER = new Scanner(System.in);

    private ConsoleUtil() {
    }

    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static char getChar(String message, char... acceptedValues) {
        char result = '\u0000';
        String input;

        do {
            System.out.print(message);

            input = SCANNER.nextLine();

            if (input.length() == 1) {
                for (var c : acceptedValues) {
                    if (input.charAt(0) == c) {
                        result = input.charAt(0);
                        break;
                    }
                }
            }
        } while (result == '\u0000');

        return result;
    }

}
