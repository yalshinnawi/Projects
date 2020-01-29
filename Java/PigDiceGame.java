/*
 * Yousef Al-Shinnawi
 * Lab 16 Pig Game Extra Credit
 */
import java.util.*;
public class PigDiceGame {
    public static void main(String[] args) {
        int computerScore = 0;
        int playerScore = 0;
        int round = 1;
        int sum = 0;
        int die = 0;
        Random gen = new Random();
        Scanner keyboard = new Scanner(System.in);
        String response;
        System.out.println("This is a dice rolling game between you and the computer,\nif you would like to begin press enter or press 'q' to quit.");
        response = keyboard.nextLine();
        if (response.equals("q")) {
            System.exit(0);
        } else {
        }
        while (playerScore <= 100 && computerScore <= 100) {
            System.out.println("Start of round " + round);
            playerScore += player();
            computerScore += computer();
            System.out.printf("End of round %d %nPlayer: %d Computer: %d %n", round, playerScore, computerScore);
            if (playerScore >= 100 || computerScore >= 100) {
                break;
            }
            round++;
            System.out.println("Would you like to start the next round?\nIf so, press enter, if not press 'q'");
            response = keyboard.nextLine();
            if (response.equalsIgnoreCase("q")) {
                break;
            } else {
            }
        } 
        if (playerScore >= 100 || computerScore >= 100) {
            if (playerScore > computerScore) {
                System.out.println("Congratulations. You beat the computer!\nYour total score was " + playerScore + "!");
            } else if (computerScore > playerScore) {
                System.out.println("So sorry, the computer wins.\nThe computer's total score was " + computerScore + ".");
            } else {
                System.out.println("Tie game.\nYour scores were both " + playerScore + ".");
            }
        } else {
            System.out.println("It seems the game was not finished,\nno one wins, please restart to try again.");
        }
    }
    public static int player() {
        int sum = 0;
        int die = 0;
        Random gen = new Random();
        Scanner keyboard = new Scanner(System.in);
        String response;
        while (die != 1) {
            die = gen.nextInt(6) + 1;
            sum += die;
            if (die == 1) {
                System.out.println("\nYou rolled a 1, so your turn is over and you have recieved no points this round.");
                sum = 0;
                break;
            }
            System.out.println("You rolled a " + die + ",\nand your total score is now " + sum + ".\nWould you like to roll again?\nPress enter if so, if not press 'q'.");
            response = keyboard.nextLine();
                if (response.equalsIgnoreCase("q")) {
                    break;
                } else {
                }
        }
        return sum;
    }
    public static int computer() {
        int sum = 0;
        int die = 0;
        Random gen = new Random();
        Scanner keyboard = new Scanner(System.in);
        String response;
        while (sum <= 20) {
            die = gen.nextInt(6) + 1;
            sum += die;
            if (die == 1) {
                sum = 0;
                break;
            }
        }
        return sum;
    }
}