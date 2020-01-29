/*
 * Yousef Al-Shinnawi
 * Dice Game 
 * 6/28/2018
 */
import java.util.Scanner;
import java.util.Random;
public class DiceGame {
    public static void main(String[] args) {
       int Score = 0;
       int count = 0;
       Random gen = new Random();
       Scanner keyboard = new Scanner(System.in);
       String response;
       int die1;
       int die2;
       System.out.println("This rolls 2 dice and sums up the numbers rolled until you reach or come close to the number 21.");
       System.out.println("If you would like to play, please type start, if not, just press enter.");
       response = keyboard.nextLine();
       if (response.equalsIgnoreCase("start")) {
        } else {
            System.exit(0);
        }
       while (Score < 21) {
           die1 = gen.nextInt(6) + 1;
           die2 = gen.nextInt(6) + 1;
           Score = Score + die1 + die2;
           count ++;
           if (Score >= 21) {
               break;
            }
           System.out.println("Your current score is " + Score + "." + "\nWould you like to continue?\nEnter yes, if you would like to.");
           response = keyboard.nextLine();
           if (response.equalsIgnoreCase("yes")) {
            } else {
                break;
            }
        }
       System.out.println("Your total score was " + Score + ", and you rolled the two dice " + count + " times.");
    }
}