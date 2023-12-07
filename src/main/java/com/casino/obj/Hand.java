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

    public int getValue() {
        int value = 0;
        List<Card> processLater = new ArrayList<>();

        for(Card card : cards) {
            int cardValue = card.getValue();
            if(cardValue < 11) {
                value += cardValue;
            } else {
                processLater.add(card);
            }
        }

        for(Card card : processLater) {
            value += (card.getValue() + value > 21) ? 1 : 11;
        }
        return value;
    }

    public void clear() {
        cards.clear();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
