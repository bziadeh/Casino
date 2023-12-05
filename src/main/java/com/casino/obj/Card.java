package com.casino.obj;

import javafx.scene.image.Image;
import lombok.Getter;

public class Card {

    @Getter private String suit;
    @Getter private String rank;
    @Getter private int value;
    @Getter private Image image;

    public Card(String suit, String rank, int value) {
        this.suit = suit;
        this.rank = rank;
        this.value = value;
        setImage();
    }

    private void setImage() {
        String imageFormat = String.format("cards/%s_of_%s.png", (value < 10) ? value : rank, suit).toLowerCase();
        try {
            image = new Image(imageFormat);
        } catch (Throwable th) {
            // todo: handle exception
            th.printStackTrace();
        }
    }
}
