package com.casino.games;

import com.casino.obj.Card;
import com.casino.obj.Deck;
import com.casino.obj.Hand;
import com.casino.user.User;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class Blackjack extends Game {

    @FXML private Pane pane;

    // Pre-defined starting image views, others are created at runtime
    @FXML private ImageView playerCardOneView;
    @FXML private ImageView playerCardTwoView;
    @FXML private ImageView dealerCardOneView;

    // Store dynamic image views to delete them when the game finishes
    private final List<ImageView> playerImageViews = new ArrayList<>();
    private final List<ImageView> dealerImageViews = new ArrayList<>();

    private final Deck deck = new Deck();
    private final Hand playerHand = new Hand();
    private final Hand dealerHand = new Hand();

    @Override
    public void startGame() {
        Card firstCard = playerHand.takeCard(deck);
        Card secondCard = playerHand.takeCard(deck);

        playerCardOneView.setImage(firstCard.getImage());
        playerCardTwoView.setImage(secondCard.getImage());

        Card firstDealerCard = dealerHand.takeCard(deck);
        dealerCardOneView.setImage(firstDealerCard.getImage());
    }

    @Override
    public void stopGame() {
        deck.repopulate();

        playerHand.clear();
        dealerHand.clear();

        // Clean up dynamically created image views
        playerImageViews.removeIf(view -> {
            pane.getChildren().remove(view);
            return true;
        });

        dealerImageViews.removeIf(view -> {
            pane.getChildren().remove(view);
            return true;
        });
    }

    @Override
    public void handle(User user) {
        // TODO
    }

    @FXML
    public void onHit() {
        System.out.println("Hit button clicked.");
    }

    @Override
    public String getGameId() {
        return "blackjack";
    }
}
