package com.techelevator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BlackjackGame {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		int[] deckValueArray = { 11, 10, 10, 10, 10, 9, 8, 7, 6, 5, 4, 3, 2, 11, 10, 10, 10, 10, 9, 8, 7, 6, 5, 4, 3, 2,
				11, 10, 10, 10, 10, 9, 8, 7, 6, 5, 4, 3, 2, 11, 10, 10, 10, 10, 9, 8, 7, 6, 5, 4, 3, 2 };
		ArrayList<Integer> deckValueArrayList = new ArrayList<Integer>(); // moving card values into an ArrayList
																			// instead of an array
		for (int value : deckValueArray) {
			deckValueArrayList.add(value);
		}

		double arandom1To52 = Math.random() * 52;
		int acardNumber = (int) arandom1To52;

		String[] deckCardArray = { "Ah", "Kh", "Qh", "Jh", "10h", "9h", "8h", "7h", "6h", "5h", "4h", "3h", "2h", "Ad",
				"Kd", "Qd", "Jd", "10d", "9d", "8d", "7d", "6d", "5d", "4d", "3d", "2d", "Ac", "Kc", "Qc", "Jc", "10c",
				"9c", "8c", "7c", "6c", "5c", "4c", "3c", "2c", "As", "Ks", "Qs", "Js", "10s", "9s", "8s", "7s", "6s",
				"5s", "4s", "3s", "2s" };
		ArrayList<String> deckCardArrayList = new ArrayList<String>(); // moving cards into an ArrayList instead of
																		// array
		for (String card : deckCardArray) {
			deckCardArrayList.add(card);
		}

		Map<String, Integer> cardsAndValues = new HashMap<String, Integer>();
		for (int i = 0; i < deckCardArrayList.size(); i++) {
			cardsAndValues.put(deckCardArrayList.get(i), deckValueArrayList.get(i));
		}

		ArrayList<String> removedCards = new ArrayList<String>();

		// filling both hands and calculating hand values
		int cardsLeft = 52;
		int dealerCount = 0;
		int playerCount = 0;
		int dealerAces = 0;
		int playerAces = 0;
		int dealerCountWithAces = dealerCount - (dealerAces * 10);
		int playerCountWithAces = playerCount - (playerAces * 10);
		int i = 0;

		Map<String, Integer> playerHand = new HashMap<String, Integer>();
		ArrayList<String> playerHandDisplay = new ArrayList<String>();

		Map<String, Integer> dealerHand = new HashMap<String, Integer>();

		for (i = 0; i < 4; i++) {
			double random1To52 = Math.random() * cardsLeft; // create random integer 1 to 52
			cardsLeft--;
			int cardNumber = (int) random1To52; // set card number equal to random integer 1 to 52

			if (i % 2 == 0) { // populate players hand and value
				playerHand.put(deckCardArrayList.get(cardNumber),
						cardsAndValues.get(deckCardArrayList.get(cardNumber)));
				playerCount += cardsAndValues.get(deckCardArrayList.get(cardNumber));
				removedCards.add(deckCardArrayList.get(cardNumber));
				deckCardArrayList.remove(cardNumber);
			} else { // populate dealers hand and value
				dealerHand.put(deckCardArrayList.get(cardNumber),
						cardsAndValues.get(deckCardArrayList.get(cardNumber)));
				dealerCount += cardsAndValues.get(deckCardArrayList.get(cardNumber));
				removedCards.add(deckCardArrayList.get(cardNumber));
				deckCardArrayList.remove(cardNumber);
			}

		}

		// begin game
		// print out player and dealer hands
		for (int j = 0; j < 4; j++) {
			if (j == 0 || j == 2) {
				if (cardsAndValues.get(removedCards.get(j)) == 11) {
					playerAces++;
					playerCountWithAces = playerCount - (playerAces * 10);
				}
			} else {
				if (cardsAndValues.get(removedCards.get(j)) == 11) {
					dealerAces = dealerAces + 1;
					dealerCountWithAces = dealerCount - (dealerAces * 10);
				}
			}
		}

			System.out.println(
					"Your Hand: " + removedCards.get(0) + " " + removedCards.get(2) + " for a score of " + playerCount);
			System.out.println("Dealer shows: " + removedCards.get(3));
		

		/*
		 * if (dealerAces > 0) { System.out.println("Dealer Hand: " +
		 * removedCards.get(1) + " " + removedCards.get(3) + " for a score of " +
		 * dealerCount + " or " + dealerCountWithAces); } else {
		 * System.out.println("Dealer Hand: " + removedCards.get(1) + " " +
		 * removedCards.get(3) + " for a score of " + dealerCount); }
		 */

		// take user input Hit or Stand

		String stand = "Stand";
		String hit = "Hit";
		String quit = "";
		String action = "";
		while (!(quit.equals("N"))) {   // START OF GAME, HIT 'q' TO QUIT

			if (quit.equals("N")) {
				System.out.println("User has quit Game");
				break;
			}

			while (playerCount < 22) {
				
				if (playerCount == 21) {
					System.out.println("Your Hand: " + playerHand.keySet() + " for a score of " + playerCount);
					System.out.println("Player Wins");
					break;
				}
				
				System.out.println("Hit or Stand?");
				action = input.nextLine();

				if (action.equals(stand)) {
					break;
				} 
				
				if (action.equals(hit)) {
					double random1To52 = Math.random() * cardsLeft; // deal mew card
					cardsLeft--;
					int cardNumber = (int) random1To52;
					playerHand.put(deckCardArrayList.get(cardNumber),
							cardsAndValues.get(deckCardArrayList.get(cardNumber)));
					playerCount += cardsAndValues.get(deckCardArrayList.get(cardNumber));

					if (playerCount > 21) {
						if (deckCardArrayList.get(cardNumber).substring(0, 1).equals("A")) {
							playerCount = playerCount - 10;
							playerAces--;
						}
						else {
							System.out.println("Your Hand: " + playerHand.keySet() + " for a score of " + playerCount);
							System.out.println("Bust."); // player busts and loop quits
							break;							
						}
					} else if (playerHand.size() == 7) {
						System.out.println("Your Hand: " + playerHand.keySet() + " for a score of " + playerCount);
						System.out.println("Player Wins"); // player wins and loop quits
						break;
					} else if (playerCount == 21 && playerHand.size() == 2) {
						System.out.println("Your Hand: " + playerHand.keySet() + " for a score of " + playerCount);
						System.out.println("Player Wins");
						break;
					}
					else if (playerCount == 21) {
						System.out.println("Your Hand: " + playerHand.keySet() + " for a score of " + playerCount);
						break;
					}
					removedCards.add(deckCardArrayList.get(cardNumber)); // remove card from deck
					deckCardArrayList.remove(cardNumber);
					System.out.println("Your Hand: " + playerHand.keySet() + " for a score of " + playerCount);

				} else if (action.equals(stand)) {
					break;
				} else {
					System.out.println("Invalid Action. Try Again.");
					continue;
				}
			}

			if (action.equals(stand) || (playerCount == 21 && playerHand.size() != 2)) {
				System.out.println("Dealer Flips");
				System.out.println("Dealer Hand: " + dealerHand.keySet() + " for a score of " + dealerCount);

				while (dealerCount < 17) {
					
					double random1To52 = Math.random() * 52;
					int cardNumber = (int) random1To52;
					dealerCount = dealerCount + deckValueArray[cardNumber];
					if (deckValueArrayList.get(cardNumber) == 11) { // check total with Aces
						if (dealerCount > 21) { 
							dealerCount = dealerCount - 10;
						}
					}
					System.out.println("Dealer draws again");
					System.out.println(
							"Dealer receives: " + deckCardArray[cardNumber] + " for a total of " + dealerCount);
					if (dealerCount > 21) {
						System.out.println("Dealer busts");
					} else if (dealerCount > 17) {
						System.out.println("Dealer stands");
					}
				}
				if (dealerCount > 21) {
					
					System.out.println("Player Wins");
				}
				if (playerCount > dealerCount && dealerCount < 22) {
					System.out.println("Player Wins");
					
				} else if (dealerCount > playerCount  && dealerCount < 22) {
					System.out.println("Dealer Wins");

				} else if (dealerCount == playerCount  && dealerCount < 22) {
					System.out.println("Push");
				}
			}
			
			System.out.println("Play again? Y/N");
			quit = input.nextLine();
			if (quit.equals("Y")) {
				deckCardArrayList.addAll(removedCards);
				removedCards.clear();
				dealerHand.clear();
				playerHand.clear();
				dealerCount = 0;
				dealerAces = 0;
				playerCount = 0;
				playerAces = 0;
				cardsLeft = 52;
				for (i = 0; i < 4; i++) {
					double random1To52 = Math.random() * cardsLeft; // create random integer 1 to 52
					cardsLeft--;
					int cardNumber = (int) random1To52; // set card number equal to random integer 1 to 52

					if (i % 2 == 0) { // populate players hand and value
						if (deckCardArrayList.get(cardNumber).charAt(0) == 'A') {
							playerAces++;
						}
						playerHand.put(deckCardArrayList.get(cardNumber),
								cardsAndValues.get(deckCardArrayList.get(cardNumber)));
						playerCount += cardsAndValues.get(deckCardArrayList.get(cardNumber));
						removedCards.add(deckCardArrayList.get(cardNumber));
						deckCardArrayList.remove(cardNumber);
					} else { // populate dealers hand and value
						if (deckCardArrayList.get(cardNumber).charAt(0) == 'A') {
							dealerAces++;
						}
						dealerHand.put(deckCardArrayList.get(cardNumber),
								cardsAndValues.get(deckCardArrayList.get(cardNumber)));
						dealerCount += cardsAndValues.get(deckCardArrayList.get(cardNumber));
						removedCards.add(deckCardArrayList.get(cardNumber));
						deckCardArrayList.remove(cardNumber);
					}

				}
				for (int j = 0; j < 4; j++) {
					if (j == 0 || j == 2) {
						if (cardsAndValues.get(removedCards.get(j)) == 11) {
							playerCountWithAces = playerCount - (playerAces * 10);
						}
					} else {
						if (cardsAndValues.get(removedCards.get(j)) == 11) {
							dealerCountWithAces = dealerCount - (dealerAces * 10);
						}
					}
				}
				if (playerAces > 0) {
					System.out.println("Your Hand: " + removedCards.get(0) + " " + removedCards.get(2) + " for a score of "
							+ playerCount + " or " + playerCountWithAces);
					System.out.println("Dealer shows: " + removedCards.get(3));
				} else {
					System.out.println(
							"Your Hand: " + removedCards.get(0) + " " + removedCards.get(2) + " for a score of " + playerCount);
					System.out.println("Dealer shows: " + removedCards.get(3));
				}
			}
		}
	}
}


