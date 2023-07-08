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
        if  (secretCode == null) {
            return;
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
        if (!scanner.hasNextInt()) {
            System.out.println("Error");
            return null;
        }
        int size = scanner.nextInt();

        System.out.println("Input the number of possible symbols in the code:");
        if (!scanner.hasNextInt()) {
            System.out.println("Error");
            return null;
        }
        int range = scanner.nextInt();

        // Check that the size of the secret code requested be <= 10
        if (size < 1 || range < 1 || range > 36 || range < size) {
            System.out.println("Error");
            return null;
        }

        Random random = new Random();
        StringBuilder secretCode = new StringBuilder();
        int[] uniqueDigits = new int[36];
        Arrays.fill(uniqueDigits, 0);

        for (int i = 0; i < size; i++) {
            int digit = random.nextInt(range);
            while (uniqueDigits[digit] == 1) {
                digit = random.nextInt(range);
            }
            uniqueDigits[digit]++;
            if (digit < 10) {
                secretCode.append(digit);
            } else {
                secretCode.append((char) ('a' + digit - 10));
            }
        }

        char charToAppend = '*';
        char[] charArray = new char[size];
        Arrays.fill(charArray, charToAppend);
        String secretString = new String(charArray);
        char lastChar =  (char) ('a' + range - 11);

        System.out.printf("The secret is prepared: %s (0-9, %s).\n",
                secretString,
                lastChar != '`' ? "a-" + lastChar : "No symbols"
        );
        System.out.println("Okay, let's start a game!");

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
