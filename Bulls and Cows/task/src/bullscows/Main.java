package bullscows;

import java.util.*;

public class Main {
    String userInput;
    String secretCode;
    int bulls;
    int cows;

    Main(String userInput, String secretCode) {
        this.userInput = userInput;
        this.secretCode = secretCode;
    }

    public static void main(String[] args) {
        System.out.printf("The random secret number is %s", generateRandomNumber());

        /*Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        String secretCode = "9305";
        Main code = new Main(userInput, secretCode);
        code.checkCode();
        code.printOutput();*/
    }

    public static String generateRandomNumber() {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();

        if (size > 10) {
            System.out.println("Error");
            return null;
        }
        StringBuilder secretCode = new StringBuilder();

        while (secretCode.length() < size) {
            StringBuilder pseudoRandomNumber = new StringBuilder(String.valueOf(System.nanoTime())).reverse();
            while (pseudoRandomNumber.charAt(0) == '0') {
                pseudoRandomNumber.delete(0, 1);
            }
            if (pseudoRandomNumber.length() < size) {
                continue;
            }

            int[] uniqueDigits = new int[10];
            Arrays.fill(uniqueDigits, 0);

            for (int i = 0; i < pseudoRandomNumber.length() && secretCode.length() < size; i++) {
                int digit = Character.getNumericValue(pseudoRandomNumber.charAt(i));
                if (++uniqueDigits[digit] > 1) {
                    secretCode = new StringBuilder();
                    break;
                }
                secretCode.append(pseudoRandomNumber.charAt(i));
            }
        }
        return String.valueOf(secretCode);
    }

    void checkCode() {
        // Checks if the user's input is identical to the secret code
        if (userInput.equals(secretCode)) {
            bulls = 4;
        } else {
            for (int i = 0; i < secretCode.length(); i++) {
                // if the characters are the same
                if (userInput.charAt(i) == secretCode.charAt(i)) {
                    addBulls();
                    // if the secret code contains the character
                } else if (secretCode.contains(String.valueOf(userInput.charAt(i)))) {
                    addCows();
                }
            }
        }
    }

    void addBulls() {
        bulls++;
    }

    void addCows() {
        cows++;
    }

    void printOutput() {
        // Prints the bulls and cows
        if (bulls > 0 && cows > 0) {
            System.out.printf("Grade: %d bull(s) and %d cow(s). The secret code is %s", bulls, cows, secretCode);
        } else if (bulls > 0) {
            System.out.printf("Grade: %d bull(s). The secret code is %s", bulls, secretCode);
        } else if (cows > 0) {
            System.out.printf("Grade: %d cows(s). The secret code is %s", cows, secretCode);
        } else {
            System.out.printf("Grade: None. The secret code is %s", secretCode);
        }
    }
}
