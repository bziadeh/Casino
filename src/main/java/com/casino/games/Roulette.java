package com.casino.games;

import com.casino.Casino;
import com.casino.user.User;
import javax.swing.JLabel;

public class Roulette extends Game {

    public Roulette(Casino casino, User user) {
        super(casino, user);
    }

    @Override
    public void startGame() {
        // todo: implement startGame()
        add(new JLabel("Roulette Page"));
        setVisible(true);
    }

    @Override
    public void exitGame() {
        // todo: implement exitGame()
    }
}
