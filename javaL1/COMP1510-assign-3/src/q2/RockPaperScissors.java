package q2;

import java.text.NumberFormat;
import java.util.Random;
import java.util.Scanner;

/**
 * <p>A class letting user play "Rock, Paper and Scissors" game with the 
 * computer.</p>
 *
 * @author Hai Hua, Tan
 * @version 1.0
 */
public class RockPaperScissors {
    /**A string stores the choice of the computer.*/
    private String computerPlay;
    
    /**Count winning times of the user.*/
    private int winCount;
    
    /**Count losing times of the user.*/
    private int loseCount;
    
    /**Count tying times.*/
    private int tieCount;
    /**
     * <p>This is the main method (entry point) that gets called by the JVM.</p>
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        /*Note: Create a PockPaperScissors object here.
         *If not using object, all the instant methods need to be static.
         */
        RockPaperScissors game = new RockPaperScissors();
        String toContinue;
        Scanner scan = new Scanner(System.in);
        
        //Print the game instruction at the beginning.
        game.printInstruction();
        
        //Main loop, quit until user enter "n" or "N".
        toContinue = "y";
        do {
            //Computer set up its option
            game.prepare();
            
            
            try {
            String userChoice = game.getUserPlay(scan);
            game.reveal(userChoice);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("continue playing?(y/n)");
            scan.nextLine();
            toContinue = scan.nextLine();
        } while(!toContinue.equalsIgnoreCase("n"));
        game.statistics();
        
        //Close the scanner
        scan.close();
        
        //Thank you and goodbye message.
        System.out.println("Thank you for playing the game. Bye!");
        System.out.println();
        
        System.out.println("Question two was called and ran sucessfully.");
    }
    
    /**
     * A method print out the game instruction.
     */
    public void printInstruction() {
        System.out.println();
        System.out.println("*******************GAME INSTRUCTIONS" 
                            + "****************************");
        System.out.println("*                                   " 
                            + "                           *");
        System.out.println("* 1. Each round you have 3 options, " 
                            + "Enter your option with:    *");
        System.out.println("*    \"R\" or \"r\" - Rock            " 
                            + "                             *");
        System.out.println("*    \"P\" or \"p\" - Pape            " 
                            + "                             *");
        System.out.println("*    \"S\" or \"s\" - Scissors        " 
                            + "                             *");
        System.out.println("* 2. Press any key to continue next round" 
                            + "                      *");
        System.out.println("* 3. At the stop of each round, "
                            + "enter \"n\" or \"N\" to quit.      *");
        System.out.println("*************************************"
                            + "***************************");
        System.out.println();
    }
    
    /**A method that computer randomly generate an option.*/
    private void prepare() {
        final int maxPlay = 3;
        Random generator = new Random();
        int choice = generator.nextInt(maxPlay);
        switch(choice) {
        case 0:
            computerPlay = "R";
            break;
        case 1:
            computerPlay = "P";
            break;
        case 2:
        default:
            computerPlay = "S";
            break;
        }
    }
    
    /**A method prompts user and read in user's choice.
     * @param scan The Scanner object passed from the caller
     * @return a string representing the the user's choice.
     */
    private String getUserPlay(Scanner scan) {
        System.out.println(
                "Please input your play [(R)ock, (P)aper or (S)cissors]:");
        String userPlay;
        userPlay = scan.next();
        if (!userPlay.equalsIgnoreCase("r")
                && !userPlay.equalsIgnoreCase("p")
                && !userPlay.equalsIgnoreCase("s")) {
            throw new IllegalArgumentException(
               "Invalid input! Only \"R\", \"P\" or \"S\" are accepted.");
        }
        return userPlay;
    }
    
    /**Reveal the result.
     *@param userPlay the user's choice 
     */
    private void reveal(String userPlay) {
        System.out.println();
        //Print the computer's play
        System.out.println("Computer plays \"" 
                           + toGesture(computerPlay) 
                           + "\".");
        //Print the user's play
        System.out.println("You paly \"" 
                           + toGesture(userPlay)  
                           + "\".");
        //Print the result.
        if (computerPlay.equalsIgnoreCase(userPlay)) {
            System.out.println("It'a tie.");
            tieCount++;
        } else if (computerPlay.equalsIgnoreCase("r")) {
            if (userPlay.equalsIgnoreCase("s")) {
                System.out.println("Rock crushes scissors. You lose!");
                loseCount++;               
            } else {
                System.out.println("Paper covers rock. You win!");
                winCount++; 
            }            
        } else if (computerPlay.equalsIgnoreCase("p")) {
            if (userPlay.equalsIgnoreCase("r")) {
                System.out.println("Paper covers rock. You lose!");
                loseCount++;               
            } else {
                System.out.println("Scissors cut paper. You win!");
                winCount++; 
            } 
        } else {
            if (userPlay.equalsIgnoreCase("p")) {
                System.out.println("Scissors cut paper. You lose!");
                loseCount++;               
            } else {
                System.out.println("Rock crushes scissors. You win!");
                winCount++; 
            }             
        }
        System.out.println();
    }
    
    /**
     * A method translating the gesture code to the gesture name for displaying.
     * @param str the string of gesture code.
     * @return the string of the gesture name.
     */
    private String toGesture(String str) {
        String gesture = "";
        str = str.toLowerCase();
        switch (str) {
        case "r":
            gesture = "Rock";
            break;
        case "p":
            gesture = "Paper";
            break;
        case "s":
            gesture = "Scissors";
            break;
        default:
            throw new IllegalArgumentException(
                  "Invalid Gesture! Only \"R\", \"P\" or \"S\" are accepted.");
        }
        return gesture;
    }
    
    /**
     * A method to show game statistics after user end the game.
     */
    public void statistics() {
        int totalGames = winCount + tieCount + loseCount;

        
        if (totalGames > 0) {
            NumberFormat fmt = NumberFormat.getPercentInstance();
            System.out.println("****************YOUR SCORE******************");
            System.out.println("Total\tWin\tTie\tLose\tWin Rate");
            System.out.println(winCount + tieCount + loseCount + "\t"
                    + winCount + "\t"
                    + tieCount + "\t"
                    + loseCount + "\t"
                    + fmt.format(((double) winCount / totalGames)));            
        }
        System.out.println("**********************************************");
        System.out.println();
    }    
};
