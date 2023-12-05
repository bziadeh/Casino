package com.casino.games;

import com.casino.obj.Card;
import com.casino.obj.Deck;
import com.casino.ui.AbstractPanel;
import com.casino.user.User;
import javafx.fxml.FXML;

import java.util.ArrayList;
import java.util.List;

public class Blackjack extends AbstractPanel {

    private final Deck deck = new Deck();

    private List<Card> playerHand = new ArrayList<>();

    private List<Card> dealerHand = new ArrayList<>();

    @Override
    public void handle(User user) {

    }

    @FXML
    public void onHit() {
        System.out.println("Hit button clicked.");
    }
}
