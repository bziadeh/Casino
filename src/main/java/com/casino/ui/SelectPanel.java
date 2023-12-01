package com.casino.ui;

import com.casino.Casino;
import com.casino.user.User;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class SelectPanel extends AbstractPanel {

    @FXML private Text usernameText;

    @Override
    void handle(User user) {
        usernameText.setText(user.getUsername());
    }

    @FXML
    public void startBlackjack() {
        Casino casino = Casino.getInstance();
        casino.getPrimaryStage().getScene().setRoot(casino.getScenes().get("blackjack"));
    }
}
