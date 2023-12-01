package com.casino;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.Getter;
 
public class Deck {
	@Getter
	private static  List<Card> cards = new ArrayList<Card>();
	
	String suit[] = {"Hearts", "Clubs", "Diamonds", "Spades"};
	String rank[] = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "King", "Queen", "Ace"};
	
	public Deck() {
		for (int i = 0; i < 4; i++) {
		    for (int j = 0; j < 13; j++) { 
		    	cards.add(new Card(suit[i], rank[j]));
		    }
		}
		shuffle();
	}

	public static void shuffle() {
		Collections.shuffle(cards);
	}
}
