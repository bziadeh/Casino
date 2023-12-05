package com.casino.obj;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {

    private final List<Card> cards = new ArrayList<>();

    public Card takeCard(Deck deck) {
        if(deck.getCards().isEmpty()) {
            return null;
        }
        Card card = deck.getCards().remove(0);
        cards.add(card);
        return card;
    }

    public void clear() {
        cards.clear();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
