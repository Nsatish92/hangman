import java.util.*;

public class Main {
    private static String[] words = {"computer", "java", "programming", "software", "developer"};
    private static Random rand = new Random();
    private static String word = words[rand.nextInt(words.length)];
    private static char[] wordArray = word.toCharArray();
    private static char[] hiddenWordArray = new char[wordArray.length];
    private static int wrongGuessCount = 0;
    private static int maxWrongGuessCount = 6;
    private static List<Character> guessedLetters = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Welcome to Hangman!");
        System.out.println("The word has " + wordArray.length + " letters.");

        Arrays.fill(hiddenWordArray, '_');
        printHiddenWord();

        while (wrongGuessCount < maxWrongGuessCount) {
            System.out.println("Guess a letter:");
            Scanner scanner = new Scanner(System.in);
            char guess = scanner.nextLine().charAt(0);

            if (guessedLetters.contains(guess)) {
                System.out.println("You already guessed that letter. Try again.");
                continue;
            }

            guessedLetters.add(guess);

            if (word.contains(String.valueOf(guess))) {
                updateHiddenWord(guess);
                printHiddenWord();
                if (!String.valueOf(hiddenWordArray).contains("_")) {
                    System.out.println("Congratulations, you won!");
                    break;
                }
            } else {
                wrongGuessCount++;
                System.out.println("Wrong guess. You have " + (maxWrongGuessCount - wrongGuessCount) + " guesses left.");
            }
        }

        if (wrongGuessCount == maxWrongGuessCount) {
            System.out.println("Sorry, you lost. The word was: " + word);
        }
    }

    private static void updateHiddenWord(char guess) {
        for (int i = 0; i < wordArray.length; i++) {
            if (wordArray[i] == guess) {
                hiddenWordArray[i] = guess;
            }
        }
    }

    private static void printHiddenWord() {
        System.out.println(String.valueOf(hiddenWordArray));
    }
}
