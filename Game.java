import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.io.File;
import java.util.ArrayList;

// IDEAS TO IMPLEMENT
// CENTERING EVERYTHING
// ADDING AVAILABLE AND GUESSED LETTERS EACH TURN

public class Game{

    List<String> wordList = new ArrayList<>();


    String word;

    public Game() {
        try {
            wordList = Files.readAllLines(new File("validwords.txt").toPath(), Charset.defaultCharset());
        } catch (Exception e) {
            e.printStackTrace();
        }
        word = wordList.get((int) Math.floor(Math.random() * wordList.size()));
    }

    

    public void playGame(){
        String guess;
        int guessesLeft = 6;
        HashSet<Character> letters = new HashSet<>();
        int correctLetterCount = 0;
        Scanner sc = new Scanner(System.in);
        
        for(char ch : word.toCharArray()){
            letters.add(ch);
        }

        System.out.println("Welcome to spie51's version of Wordle!");
        System.out.println("You must try and guess the 5-Letter Word in less than 6 guesses.");
        System.out.println("Under each guess, you'll get some hints to help you out.");
        System.out.println("A \"G\" means that the letter is in the right location.");
        System.out.println("A \"Y\" means that the letter is in the word, but not in the right location.");
        System.out.println("Nothing means that the letter isn't in the word.");
        System.out.println("Good Luck!");

        while(guessesLeft > 0){
            System.out.println("Enter your guess (words should be 5 letters in length):");
            guess = sc.next().toLowerCase();
            if(guess.length() > 5 || !wordList.contains(guess)){
                if(guess.length() > 5){
                    System.out.println("Guess is too long!");
                }   
                else{
                    System.out.println("Guess is not a valid word!");
                }
                continue;
           }

           for(int i = 0; i < 5; i++){
                if(guess.charAt(i) == word.charAt(i)){
                    correctLetterCount++;
                    System.out.printf("G");
                }
                else if(letters.contains(guess.charAt(i))){
                    System.out.printf("Y");
                }
                else{
                    System.out.printf(" ");
                }
           }
           System.out.printf("\n");
           
           guessesLeft--;

           if(correctLetterCount == 5){
               System.out.println("Congrats! you have succesfully guessed today's word.");
               break;
           }
           else{
               System.out.println("Incorrect guess! Only " + guessesLeft + " guess(es) left.");
           }

           System.out.printf("\n");
           correctLetterCount = 0;
        }
        if(correctLetterCount != 5){
            System.out.println("Oh no! You ran out of guesses! Game Over. The word was " + word.toUpperCase());
        }
        
        sc.close();
    }
}
