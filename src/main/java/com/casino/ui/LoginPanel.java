package com.casino.ui;

import com.casino.Casino;
import com.casino.user.User;
import javax.swing.JLabel;
import java.util.function.Consumer;

public class LoginPanel extends AbstractPanel {

    private Consumer<User> onSuccess;

    public LoginPanel(Casino casino) {
        super(casino);
    }

    public void onSuccess(Consumer<User> onSuccess) {
        this.onSuccess = onSuccess;
    }

    public void onEnable() {
        add(new JLabel("Login Page"));
        setVisible(true);
    }
}
