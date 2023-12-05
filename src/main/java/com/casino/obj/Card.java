package com.casino.obj;

import lombok.Getter;

public class Card {

    @Getter
    private String suit;

    @Getter
    private String rank;

    @Getter
    private int value;

    public Card(String suit, String rank, int value) {
        this.suit = suit;
        this.rank = rank;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Suit: " + suit + " and Rank: " + rank;
    }
}
