import java.util.Scanner;
import java.util.Random;

public class NumberGuessingGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int maxAttempts = 5;
        int rounds = 0;
        int totalScore = 0;
        boolean playAgain = true;

        System.out.println("Welcome to the Number Guessing Game!");

        while (playAgain) {
            rounds++;
            int attempts = 0;
            int numberToGuess = random.nextInt(100) + 1;
            boolean hasGuessedCorrectly = false;

            System.out.println("\nRound " + rounds);
            System.out.println("I have selected a number between 1 and 100. Can you guess it?");

            while (attempts < maxAttempts && !hasGuessedCorrectly) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attempts++;

                if (userGuess == numberToGuess) {
                    System.out.println("Congratulations! You've guessed the correct number.");
                    hasGuessedCorrectly = true;
                    totalScore += (maxAttempts - attempts + 1) * 10; // Higher score for fewer attempts
                } else if (userGuess < numberToGuess) {
                    System.out.println("Your guess is too low.");
                } else {
                    System.out.println("Your guess is too high.");
                }

                if (attempts == maxAttempts && !hasGuessedCorrectly) {
                    System.out.println("Sorry, you've used all your attempts. The correct number was " + numberToGuess);
                }
            }

            System.out.print("Do you want to play another round? (yes/no): ");
            String response = scanner.next();
            playAgain = response.equalsIgnoreCase("yes");
        }

        System.out.println("\nGame Over! You played " + rounds + " rounds.");
        System.out.println("Your total score is: " + totalScore);

        scanner.close();
    }
}
