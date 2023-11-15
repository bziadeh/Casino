package com.casino.games;

import com.casino.Casino;
import com.casino.user.User;
import lombok.Getter;
import javax.swing.JPanel;

public abstract class Game extends JPanel {

    @Getter private final Casino casino;
    @Getter private final User user;

    public Game(Casino casino, User user) {
        this.casino = casino;
        this.user = user;
    }

    public abstract void startGame();
    public abstract void exitGame();
}
