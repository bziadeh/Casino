package com.casino.ui;

import com.casino.Casino;
import com.casino.Deck;
import com.casino.user.User;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class SelectPanel {

    @FXML private Text usernameText;
    private User user;

    public void setUser(User user) {
        this.user = user;
        usernameText.setText(user.getUsername());
    }

    @FXML
    public void startBlackjack () {
        Casino casino = Casino.getInstance();
        casino.getPrimaryStage().getScene().setRoot(casino.getScenes().get("blackjack"));
    }
}
