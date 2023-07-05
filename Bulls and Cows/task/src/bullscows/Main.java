package bullscows;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int[] secretCode = {9, 3, 0, 5};
        int bulls = 0;
        int cows = 0;

        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();

        for (int i = 0; i < secretCode.length; i++) {
            for (int j = 0; j < secretCode.length; j++) {
                int number = Character.getNumericValue(input.charAt(j));
                if (i == j && secretCode[i] == number) {
                    bulls++;
                }
                else if (i != j && secretCode[i] == number && secretCode[j] != number) {
                    cows++;
                }
            }
        }

        if (bulls > 0 && cows == 0) {
            System.out.printf("Grade: %d bulls(s). The secret code is %d%d%d%d.", bulls, secretCode[0], secretCode[1], secretCode[2], secretCode[3]);
        }
        else if (cows > 0 && bulls == 0) {
            System.out.printf("Grade: %d cow(s). The secret code is %d%d%d%d.", cows, secretCode[0], secretCode[1], secretCode[2], secretCode[3]);
        }
        else if (bulls > 0 && cows > 0) {
            System.out.printf("Grade: %d bulls(s) and %d cow(s). The secret code is %d%d%d%d.", bulls, cows, secretCode[0], secretCode[1], secretCode[2], secretCode[3]);
        }
        else {
            System.out.printf("None. The secret code is %d%d%d%d.", secretCode[0], secretCode[1], secretCode[2], secretCode[3]);
        }
    }
}
