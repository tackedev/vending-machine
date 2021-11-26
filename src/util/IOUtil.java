package util;

import java.io.IOException;
import java.util.Scanner;

/**
 * @author tackedev
 */
public class IOUtil {

    public static final String OS_NAME = System.getProperty("os.name");

    private static final Scanner SCANNER = new Scanner(System.in);

    private IOUtil() {
    }

    public static void clearConsole() throws IOException {
        if (OS_NAME.contains("Windows")) {
            Runtime.getRuntime().exec("cls");
        } else {
            Runtime.getRuntime().exec("clear");
        }
    }

    public static char getChar(String message, char... acceptedValues) throws IOException {
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
