package com.casino.games;

import com.casino.ui.AbstractPanel;
import com.casino.user.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Blackjack extends AbstractPanel {

    @Override
    public void handle(User user) {

    }

    @FXML
    public void onHit() {
        System.out.println("Hit button clicked.");
    }
}
