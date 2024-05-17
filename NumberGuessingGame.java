package task;

import java.util.Random;
import java.util.Scanner;

public class Game {

	public static void main(String[] args) {	
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to number guessing game!");
		String s = "yes";
		while(s.equals("yes")) {
			Random ran = new Random();
			int num = ran.nextInt(1000);
			int guess=-1, tries=0;
			System.out.println("Guess a number between 1 to 1000");
			
			while(guess!=num) {
				guess = sc.nextInt();
				tries++;
				
			if(guess==num) {
				System.out.println("Wow! you guessed the number");
				System.out.println("It took you " + tries + " guesses");
				System.out.println("Would you like to play again? give \"yes\" or \"no\"");
				s = sc.next().toLowerCase();
			}
			else if(guess>num) {
				System.out.println("Your guess is too high, Try again");
			}
			else {
				System.out.println("Your guess is too low, Try again");
			}
		}
	  }
	}

}
