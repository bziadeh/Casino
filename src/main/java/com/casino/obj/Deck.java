package com.casino.obj;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.Getter;

public class Deck {

    @Getter
    private final List<Card> cards = new ArrayList<>();

    private final String[] suit = { "Hearts", "Clubs", "Diamonds", "Spades" };
    private final String[] rank = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "King", "Queen", "Ace" };

    public Deck() {
        repopulate();
        shuffle();
    }

    public void repopulate() {
        cards.clear();

        for (int i = 0; i < suit.length; i++) {
            for (int j = 0; j < rank.length; j++) {
                int value;

                try {
                    value = Integer.parseInt(rank[j]);
                } catch (NumberFormatException e) {
                    value = j < 12 ? 10 : 11;
                }

                cards.add(new Card(suit[i], rank[j], value));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }
}
