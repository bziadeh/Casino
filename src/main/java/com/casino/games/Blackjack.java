package com.casino.games;

import com.casino.Casino;
import com.casino.Config;
import com.casino.obj.Card;
import com.casino.obj.Deck;
import com.casino.obj.Hand;
import com.casino.user.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
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
    @FXML private Button restartButton;

    private Consumer<Double> start;
    private double bet = 0.0;

    // Pre-defined starting image views, others are created at runtime
    @FXML private ImageView playerCardOneView;
    @FXML private ImageView playerCardTwoView;
    @FXML private ImageView dealerCardOneView;
    @FXML private ImageView cardBackView;

    // Components that display information
    @FXML private Text totalBetText;
    @FXML private Button totalBetButton;
    @FXML private TextField betField;
    @FXML private Text balanceText;
    @FXML private Text insufficientFundsError;
    @FXML private Text invalidBetError;
    @FXML private Text wonText;
    @FXML private Text loseText;

    // Store dynamic image views to delete them when the game finishes
    private final List<ImageView> playerImageViews = new ArrayList<>();
    private final List<ImageView> dealerImageViews = new ArrayList<>();

    private final Deck deck = new Deck();
    private final Hand playerHand = new Hand();
    private final Hand dealerHand = new Hand();

    @Override
    public void startGame() {
        setFinishedGame(false);

        if(!initialBetPane.isVisible()) {
            initialBetPane.setVisible(true);
        }

        start = (bet) -> {
            // Update initial bet display text
            totalBetText.setText(Config.DEFAULT_BET_TEXT.replace("0.00", String.format("%.2f", bet)));
            totalBetButton.setText(String.format("%.2f", bet));

            // Select cards for player and dealer
            Card firstCard = playerHand.takeCard(deck);
            Card secondCard = playerHand.takeCard(deck);
            Card firstDealerCard = dealerHand.takeCard(deck);

            playerCardOneView.setImage(firstCard.getImage());
            playerCardTwoView.setImage(secondCard.getImage());
            dealerCardOneView.setImage(firstDealerCard.getImage());
            cardBackView.setVisible(true);

            // Disable initial bet input components
            initialBetPane.setVisible(false);

            // Check if the player immediately hits Blackjack
            if(playerHand.getValue() == 21) {
                win(3);
            }
        };
    }

    @Override
    public void stopGame() {
        deck.repopulate();
        deck.shuffle();

        bet = 0.0;
        playerHand.clear();
        dealerHand.clear();

        totalBetText.setText(Config.DEFAULT_BET_TEXT);
        totalBetButton.setText(Config.DEFAULT_BET_BUTTON);

        restartButton.setVisible(false);
        wonText.setText(Config.DEFAULT_WIN_TEXT);
        wonText.setVisible(false);
        loseText.setVisible(false);

        betField.setText(Config.EMPTY);
        playerCardOneView.setImage(null);
        playerCardTwoView.setImage(null);
        dealerCardOneView.setImage(null);
        cardBackView.setImage(new Image("cards/back_of_card.PNG"));
        cardBackView.setVisible(false);
        clearImageViews();
    }

    @FXML
    public void restart() {
        stopGame();
        startGame();
    }

    @Override
    public void handle(User user) {
        // TODO
    }

    @FXML
    public void onHit() {
        if(isFinishedGame()) return;

        Card card = playerHand.takeCard(deck);

        if(playerHand.getValue() == 21)
            win(2);
        else if (playerHand.getValue() > 21)
            lose();
    }

    @FXML
    public void onStand() {
        if(isFinishedGame()) return;

        Card dealerCard = dealerHand.takeCard(deck);
        if(dealerHand.getCards().size() == 2) {
            cardBackView.setImage(dealerCard.getImage());
        } else {
            ImageView view = new ImageView();
            view.setImage(dealerCard.getImage());

            dealerImageViews.add(view);
            pane.getChildren().add(view);
        }

        if(dealerHand.getValue() > playerHand.getValue() && dealerHand.getValue() <= 21) {
            lose();
            return;
        }

        if(dealerHand.getValue() > 21) {
            win(2);
        } else {
            onStand();
        }
    }

    @FXML
    public void setBetButton(MouseEvent event) {
        // Player specified initial bet using the preset buttons
        Button button = (Button) event.getSource();
        double bet = validateBet(button.getText());
        if(bet == -1) return;
        removeFunds(bet);
        if(insufficientFundsError.isVisible()) insufficientFundsError.setVisible(false);
        if(invalidBetError.isVisible()) invalidBetError.setVisible(false);
        this.bet = bet;
        start.accept(bet);
    }

    @FXML
    public void setBetField(KeyEvent event) {
        // Player specified initial bet using the text field
        if(event.getCode().equals(KeyCode.ENTER)) {
            TextField field = (TextField) event.getSource();
            double bet = validateBet(field.getText());
            if(bet == -1) return;
            removeFunds(bet);
            if(insufficientFundsError.isVisible()) insufficientFundsError.setVisible(false);
            if(invalidBetError.isVisible()) invalidBetError.setVisible(false);
            this.bet = bet;
            start.accept(bet);
        }
    }

    public double validateBet(String text) {
        Casino casino = Casino.getInstance();
        double value;
        try {
            value = Double.parseDouble(text.replace(Config.CURRENCY, ""));
            if(value < 1) {
                casino.displayError(invalidBetError);
                return -1;
            }
        } catch(NumberFormatException e) {
            casino.displayError(invalidBetError);
            return -1;
        }

        double balance = getUser().getBalance();
        if(value > balance) {
            casino.displayError(insufficientFundsError);
            return -1;
        }
        return value;
    }

    public void removeFunds(double amount) {
        // Update the balance of the user
        double balance = getUser().getBalance();
        getUser().setBalance(balance = balance - amount);

        // Update the display components
        String balanceTxt = String.format("%.2f", balance);
        balanceText.setText(Config.DEFAULT_BALANCE_TEXT.replace("0.00", balanceTxt));
    }

    public void clearImageViews() {
        playerImageViews.removeIf(view -> {
            pane.getChildren().remove(view);
            return true;
        });

        dealerImageViews.removeIf(view -> {
            pane.getChildren().remove(view);
            return true;
        });
    }

    private void win(double multiplier) {
        wonText.setVisible(true);
        wonText.setText(Config.DEFAULT_WIN_TEXT.replace("0.00", String.format("%.2f", bet * multiplier)));
        restartButton.setVisible(true);
        setFinishedGame(true);
    }

    private void lose() {
        loseText.setVisible(true);
        restartButton.setVisible(true);
        setFinishedGame(true);
    }

    @Override
    public String getGameId() {
        return "blackjack";
    }
}
