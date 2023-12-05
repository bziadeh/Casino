package com.casino.games;

import com.casino.obj.Card;
import com.casino.obj.Deck;
import com.casino.obj.Hand;
import com.casino.user.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Blackjack extends Game {

    @FXML private Pane pane;
    @FXML private Pane initialBetPane;
    private Consumer<Double> start;

    // Pre-defined starting image views, others are created at runtime
    @FXML private ImageView playerCardOneView;
    @FXML private ImageView playerCardTwoView;
    @FXML private ImageView dealerCardOneView;

    // Components that display information on the players current bet
    @FXML private Text totalBetText;
    @FXML private Button totalBetButton;

    // Store dynamic image views to delete them when the game finishes
    private final List<ImageView> playerImageViews = new ArrayList<>();
    private final List<ImageView> dealerImageViews = new ArrayList<>();

    private final Deck deck = new Deck();
    private final Hand playerHand = new Hand();
    private final Hand dealerHand = new Hand();

    @Override
    public void startGame() {
        start = (bet) -> {
            // Update initial bet display text
            totalBetText.setText(String.format("Total Bet:   $%.1f", bet));
            totalBetButton.setText(String.valueOf(bet));

            // Select cards for player and dealer
            Card firstCard = playerHand.takeCard(deck);
            Card secondCard = playerHand.takeCard(deck);
            Card firstDealerCard = dealerHand.takeCard(deck);

            playerCardOneView.setImage(firstCard.getImage());
            playerCardTwoView.setImage(secondCard.getImage());
            dealerCardOneView.setImage(firstDealerCard.getImage());

            // Disable initial bet input components
            initialBetPane.setVisible(false);
        };
    }

    @Override
    public void stopGame() {
        deck.repopulate();
        deck.shuffle();

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

    @FXML
    public void setBetButton(MouseEvent event) {
        Button button = (Button) event.getSource();
        double value = Double.parseDouble(button.getText().replace("$", ""));
        start.accept(value);
    }

    @FXML
    public void setBetField(KeyEvent event) {
        if(event.getCode().equals(KeyCode.ENTER)) {
            TextField field = (TextField) event.getSource();
            double value = Double.parseDouble(field.getText().replace("$", ""));
            start.accept(value);
        }
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
