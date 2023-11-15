package com.casino.games;

import com.casino.user.User;
import lombok.Getter;

public abstract class Game {

    @Getter private final User user;

    public Game(User user) {
        this.user = user;
    }

    public abstract void startGame();
    public abstract void exitGame();
}
