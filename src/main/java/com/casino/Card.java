package com.casino;

import lombok.Getter;

public class Card {

	@Getter
	private String suit;

	@Getter
	private String rank;

	public Card(String suit, String rank) {
		this.suit = suit;
		this.rank = rank;
	}

	@Override
	public String toString() {
		return "Suit: " + suit + " and Rank: " + rank;
	}
}
