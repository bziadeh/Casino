package com.casino.games;

import com.casino.Casino;
import com.casino.user.User;
import javax.swing.JLabel;

public class Blackjack extends Game {

    public Blackjack(Casino casino, User user) {
        super(casino, user);
    }

    @Override
    public void startGame() {
        // todo: implement startGame()
        add(new JLabel("Blackjack Page"));
        setVisible(true);
    }

    @Override
    public void exitGame() {
        // todo: implement exitGame()
    }
}
