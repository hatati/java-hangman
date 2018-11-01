import javax.swing.*;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        String result = JOptionPane.showInputDialog("Insert result word");
        String letter;
        boolean playing = true;
        boolean wordFinished = true;
        boolean containsLetter = false;
        int lives = 3;

        //Build string placeholder
        StringBuilder lines = new StringBuilder();
        for (int i = 1; i <= result.length(); i++) {
            if (i != result.length())
                lines.append("_ ");
            else
                lines.append("_");
        }

        //Print placeholder and welcome string
        System.out.println("Welcome! Start Guessing");
        System.out.println(lines);

        //Scanner to take input from the user
        Scanner input = new Scanner(System.in);

        while (playing) {
            //Is the word guessed
            if(!wordFinished)
                wordFinished = true;


            //If input is "quit" exit loop
            letter = input.nextLine();
            if (letter.equals("quit"))
                playing = false;

            // We only accept input with a length of 1
            if (letter.length() > 1 || letter.length() == 0) {
                System.out.println("Input must be only one character long");
                continue;
            }

            //Input must not be a number
            try {
                Integer.parseInt(letter);
                System.out.println("Input must not be a number");
                continue;
            } catch (NumberFormatException ex) {
                //continue;
            }

            //Loop through the string and check if the input is present
            for (int i = 0; i < result.length(); i++) {
                String resultLetter = result.substring(i, i + 1);

                //Replace first char of string
                if (resultLetter.equals(letter) && i == 0){
                    lines.replace(i, i + 1, resultLetter);
                    containsLetter = true;
                }
                //Replace another char in the placeholder string because its double the size
                else if (resultLetter.equals(letter)){
                    lines.replace(i * 2, i * 2 + 1, resultLetter);
                    containsLetter = true;
                }
            }

            //If the guess is wrong then decrement lives
            if (!containsLetter) {
                lives--;
                System.out.println("Sorry no such letter in the word");
                System.out.println("Remaining lives: " + lives);
            }

            //Reset containsLetter for the next input
            containsLetter = false;

            //If there are no more lives, then break out of the loop
            if(lives == 0){
                System.out.println("NO MORE LIVES. GAME OVER!");
                break;
            }

            // Check if all the letters are guessed. If they are then set wordFinished to true
            for(int i = 0; i < lines.length(); i=i+2){
                if(lines.substring(i,i+1).equals("_")){
                    wordFinished = false;
                }
            }

            //If wordFinished is true, then congratulate the user and break out of the loop
            if(wordFinished){
                System.out.println("YEAH MAN! YOU GUESSED THE WORD. YOU'RE AWESOME!");
                break;
            }

            //Print placeholder
            System.out.println(lines);

        }

        //Print the full word
        System.out.println(lines);
    }
}
