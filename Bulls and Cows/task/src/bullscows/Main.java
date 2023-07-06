package bullscows;

import java.util.*;

public class Main {
    String userInput;
    String secretCode;
    int bulls;
    int cows;
    int turns = 0;

    Main(String secretCode) {
        this.secretCode = secretCode;
    }

    public static void main(String[] args) {
        // Generate the secret code
        String secretCode = generateRandomNumber();
        while  (secretCode == null) {
            secretCode = generateRandomNumber();
        }

        Main game = new Main(secretCode);
        // Create a loop until the game is finished
        game.checkCode();
        while (!game.printOutput().equals("WON")) {
            game.checkCode();
        }
    }

    public static String generateRandomNumber() {
        System.out.println("Please, enter the secret code's length:");
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();

        // Check that the size of the secret code requested be <= 10
        if (size > 10) {
            System.out.println("Error");
            return null;
        }

        System.out.println("Okay, let's start a game!");
        StringBuilder secretCode = new StringBuilder();

        while (secretCode.length() < size) {
            StringBuilder pseudoRandomNumber = new StringBuilder(String.valueOf(System.nanoTime())).reverse();

            // Trim all the 0 at the beginning of the string
            while (pseudoRandomNumber.charAt(0) == '0') {
                pseudoRandomNumber.delete(0, 1);
            }
            // If size not enough, we start again
            if (pseudoRandomNumber.length() < size) {
                continue;
            }

            int[] uniqueDigits = new int[10];
            Arrays.fill(uniqueDigits, 0);

            // Find all the unique digits
            for (int i = 0; i < pseudoRandomNumber.length() && secretCode.length() < size; i++) {
                int digit = Character.getNumericValue(pseudoRandomNumber.charAt(i));
                if (++uniqueDigits[digit] > 1) {
                    secretCode = new StringBuilder();
                    break;
                }
                // Generate the secret code
                secretCode.append(pseudoRandomNumber.charAt(i));
            }
        }
        return String.valueOf(secretCode);
    }

    void checkCode() {
        // Initialize all inputs
        turns++;
        bulls = 0;
        cows = 0;
        System.out.printf("Turn %d:\n", turns);
        Scanner scanner = new Scanner(System.in);
        userInput = scanner.nextLine();

        // Checks if the user's input is identical to the secret code
        if (userInput.equals(secretCode)) {
            bulls = secretCode.length();
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

    String printOutput() {
        // Prints the bulls and cows
        if (bulls == secretCode.length()) {
            System.out.printf("Grade: %d bull(s).\nCongratulations! You guessed the secret code.", bulls);
            return "WON";
        }
        else if (bulls > 0 && cows > 0) {
            System.out.printf("Grade: %d bull(s) and %d cow(s)\n", bulls, cows);
            return "ONGOING";
        } else if (bulls > 0) {
            System.out.printf("Grade: %d bull(s)\n", bulls);
            return "ONGOING";
        } else if (cows > 0) {
            System.out.printf("Grade: %d cows(s)\n", cows);
            return "ONGOING";
        } else {
            System.out.println("Grade: None.");
            return "ONGOING";
        }
    }
}
