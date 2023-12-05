package com.casino.ui;

import com.casino.user.User;
import lombok.Getter;

public abstract class AbstractPanel {

    @Getter private User user;

    public void setUser(User user) {
        this.user = user;
        handle(user);
    }

    public abstract void handle(User user);
}
