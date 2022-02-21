/**
 *
 * @author Aubrey Robinson
 * @version 1.0
 * @since 3/12/2020
 * Hangman.java
 *
 * This program allows a user to play Hangman with the computer.
 * --EXPLAIN THE RULES--
 * 
 */
import java.util.*;
import java.lang.*;

public class WheelOfFortuneFinal
{
   //This is an array of Strings
   static String movieList[] = {"Ratatouille", "Beauty and the Beast","Shrek 2","Coco",
   "Spiderman: Far From Home","Spiderman: Into the Spider Verse", "Zootopia", "Snow White",
   "Avengers:End Game","The Matrix","The Lion King","Mulan","Frozen","Moana","Aladdin"};
   // add more to this list by adding more String Literals separated by commas
   /**
    * Entry point of the program
    * @param args input arguments
    */
   public static void main(String[] args)
   {
    Scanner scan = new Scanner(System.in);
    System.out.println("Welcome to Wheel of Fortune!");
    System.out.println("You have 3 attempts to guess\nthe phrase.\n");
    

    //Generate a random number between 0-15 to choose a movie
      Random rand = new Random();
      int randNum = rand.nextInt(15);

    //Choose a random Movie
      String movie = movieList[randNum];
      movie = movie.toUpperCase(); //Change characters in movie to upper case so guessing letters later will be easier.


    // Hide the Movie Title
      char character;
      StringBuilder movieHidden = new StringBuilder(movie.length());
      
      for(int i=0; i<movie.length(); i++){ // cycle through each character in the string movie.
        
        character = movie.charAt(i);//asign each character to the variable character

        if(character >= 65 && character <= 90){// if character is an upper case letter A(65) - Z(90). Then add a star to hide the letter.
          movieHidden.append("*");
        } 
        else{
          movieHidden.append(movie.charAt(i));// but it the character isnt a letter then just add that character.
        }
      }
      System.out.println("This is the hidden phrase:\n["+ movieHidden+"]\n");
      System.out.println("Press enter to Spin the Wheel and see how much you can win per letter!");
      String contin = scan.nextLine();

    // Generate Cash Values
      int randNum2 = rand.nextInt(5);//Random number to select a cash amount from the array
      int[] cashAmounts = {100,200,300,400,500}; // create an array with cash values
      int cashPerLetter = cashAmounts[randNum2]; // asign cash per letter the selected cash amount
      System.out.println("Spinning...\nYou can win: $" + cashPerLetter + " per letter\n");
      System.out.println("Press enter to continue");
      contin = scan.nextLine();//allows the user to click enter to continue.
      
    //Show Common Letters R S T L N E.
      System.out.println("Great news: we are helping you out!\nHere is the phrase again with RSTLNE revealed.");
      for(int c=0; c<movie.length();c++){ // cycle through each character in movie
            String movieLetters = String.valueOf(movie.charAt(c));// asign each character to movieLetters. 
            //v v v then if the character is R S T L N or E replace the star with that character
            if (movieLetters.equals("R") || movieLetters.equals("S") || movieLetters.equals("T") || movieLetters.equals("L")  || movieLetters.equals("N") || movieLetters.equals("E")){ 
              movieHidden.replace(c,c+1,movieLetters);
            }
          }
      System.out.println("["+ movieHidden+"]\n");    

     

      int correct = 0;//keeps track of how many times a correct guess appears in the movie
      int mistakesMade = 0;//keeps track of total mistakes made
      String character4 = "";//stores a character from movie so it can be compared to the guessed character
      int totalWinnings = 0; //keeps track of total winnings
      String choice = "";
      String movieGuessed = "";

        while((mistakesMade < 3) && (!String.valueOf(movieHidden).equals(movie)) && (!movieGuessed.equals(movie))){//keep allowing the player to guess letters
          //as long as they havent made 3 mistakes or guessed all the letters in the movie
          System.out.println("Enter 'a' to the phrase.\nEnter 'b' to guess a letter.");
          choice = scan.nextLine(); // allows user to chose an option.
        while(choice.equals("b")){// if they choose be let them guess letters until they choice a different choice..

        for(int a=0; a<movie.length(); a++){
          System.out.println("Guess a letter.: ");
          String guess = scan.nextLine().toUpperCase();//ask user to guess a letter and turns their input to capital letter so it
          //can be properly compared.
          
          
          for(int b=0; b<movie.length();b++){
            character4 = String.valueOf(movie.charAt(b));//storing the character that is going to be compared to the character guessed.
            if (guess.equals(character4)){//if guessed character is in the movie title.
              movieHidden.replace(b,b+1,guess);//replace that char with the * that is holding its place.
              correct += 1;//increase correct by 1
              totalWinnings += cashPerLetter;//increase the total amount of winnings by the amount of cash earned for each letter.
              
            }
            
          }
          correct -= 1;//if guessed character isnt in the movie title reduce correct by 1
          System.out.println("["+ movieHidden+ "]");

          if(correct< 0){// if correct is below zero
            mistakesMade += 1;//add 1 to mistakes
            System.out.println("Sorry that is incorrect.");
            System.out.println("Mistakes made: "+ mistakesMade + " out of 3.\n");//display total mistakes
          }
          if( mistakesMade == 3 || String.valueOf(movieHidden).equals(movie)){//if you guess the movie or make 3 mistakes end the game.
            if(String.valueOf(movieHidden).equals(movie)){//if you guess the movie congradulate the player and display winnings.
              System.out.println("Congrats you guessed the movie!\nYour total winnings are: $"+totalWinnings);
              choice = "end";// asign choice end to get out of the while loop.
            }
            else if(mistakesMade == 3){//However if you make 3 mistakes display movie title and game over.
              System.out.println("Oh no you made too many mistakes. Better luck next time.\nGAMEOVER");
              System.out.println("The movie was: " + movie);
              choice= "end";// asign choice end to get out of the while loop.
            }
            break;
          }
          System.out.println("Enter 'a' to the phrase.\n Enter 'b' to guess a letter.");
          choice = scan.nextLine();
          correct = 0;//reset correct to zero so it is ready to work as intended.
          break;        
        }
      }
      
    // if user chooses movie
      while(choice.equals("a")){//if the player choose to guess the whole movie at once allow them to.
        System.out.println("Quite the brave soul! Well go on guess the phrase.");
        movieGuessed = scan.nextLine().toUpperCase();// ask the player for their guess and change it to all caps so it can be 
        //compared correctly.
        if (movieGuessed.equals(movie)){//if its right yay they win and print their winnings
          System.out.println("Congrats, you guessed correctly!\nYou just won: $"+ totalWinnings);
          choice = "end";// asign choice end to get out of the while loop.
          
        }
        else{
          mistakesMade +=1;
          System.out.println("Sorry that is incorrect.");
          System.out.println("Mistakes made: "+ mistakesMade + " out of 3.\n");
          choice = "b";// assign choice b to get out of this while loop.
        }
        if(mistakesMade == 3){//However if you make 3 mistakes display movie title and game over.
              System.out.println("Oh no you made too many mistakes. Better luck next time.\nGAMEOVER");
              System.out.println("The movie was: " + movie);
      }
  }
  }
}
}

//if you got this far I want to thank you for reading my code 
//I hope you are well dispite this whole covid situation. 

  
