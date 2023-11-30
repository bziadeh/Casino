package com.casino.ui;

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
}
