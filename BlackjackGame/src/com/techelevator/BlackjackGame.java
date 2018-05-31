package com.techelevator;

import java.util.Scanner;

public class BlackjackGame {

	public static void main(String[] args) {
		
	Scanner input = new Scanner(System.in);
		
	int[] deckValueArray = {1, 10, 10, 10, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 10, 10, 10, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 10, 10, 10, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 10, 10, 10, 10, 9, 8, 7, 6, 5, 4, 3, 2};
	double arandom1To52 = Math.random() * 52;
	int acardNumber = (int) arandom1To52;
	String[] deckCardArray = {"Ah", "Kh", "Qh", "Jh", "10h", "9h", "8h", "7h", "6h", "5h", "4h", "3h", "2h", "Ad", "Kd", "Qd", "Jd", "10d", "9d", "8d", "7d", "6d", "5d", "4d", "3d", "2d", "Ac", "Kc", "Qc", "Jc", "10c", "9c", "8c", "7c", "6c", "5c", "4c", "3c", "2c", "As", "Ks", "Qs", "Js", "10s", "9s", "8s", "7s", "6s", "5s", "4s", "3s", "2s"};
	//System.out.println(acardNumber);
	//System.out.println(deckValueArray[acardNumber]);
	//System.out.println(deckCardArray[acardNumber]);
	
	// filling both hands and calculating hand values
	int i = 0;
	int dealerCount = 0;
	int playerCount = 0;
	String dealerCard1 = "";
	String dealerCard2 = "";
	String playerCard1 = "";
	String playerCard2 = "";
	for (i = 0; i < 4; i++) {
		double random1To52 = Math.random() * 52;	// create random integer 1 to 52
		int cardNumber = (int) random1To52;		// set card number equal to random integer 1 to 52
		
		if (i % 2 == 0) { 						// populate players hand and value
			playerCount = playerCount + deckValueArray[cardNumber];
			if (playerCard1.length() > 1) {
				playerCard2 = deckCardArray[cardNumber];
			}
			else {
				playerCard1 = deckCardArray[cardNumber];
			}
		}
		else {									// populate dealers hand and value
			dealerCount = dealerCount + deckValueArray[cardNumber];
			if (dealerCard1.length() > 1) {
				dealerCard2 = deckCardArray[cardNumber];
			}
			else {
				dealerCard1 = deckCardArray[cardNumber];
			}
		}
	
	}
	
	// begin game
	// print out player and dealer hands
	System.out.println("Your Hand: " + playerCard1 + " " + playerCard2 + " for a score of " + playerCount);
	System.out.println("Dealer Hand: " + dealerCard1 + " " + dealerCard2 + " for a score of " + dealerCount);
	
	// take user input Hit or Stand
	System.out.println("Hit or Stand?");
	String action = input.nextLine();
	
	String stand = "Stand";
	String hit = "Hit";
	if (action.equals(stand)) {
		while (dealerCount < 17) {
			double random1To52 = Math.random() * 52;
			int cardNumber = (int) random1To52;
			dealerCount = dealerCount + deckValueArray[cardNumber];
			System.out.println("Dealer draws again");
			System.out.println("Dealer recieves: " + deckCardArray[cardNumber] + " for a total of " + dealerCount);
			if (dealerCount > 21) {
				System.out.print("Dealer busts");
			}
			else if (dealerCount > 17) {
				System.out.print("Dealer stands");
			}
		}
	}
	}
	

}
