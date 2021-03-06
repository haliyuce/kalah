package com.backbase.homework.kalah.model;

import lombok.Data;

@Data
public class Move {

    private int startPitIndexX, startPitIndexY;

    private Player player;

    private int playerOrder;

    public Move(int startPitIndexX, int startPitIndexY, Player player) {

        this.startPitIndexX = startPitIndexX;
        this.startPitIndexY = startPitIndexY;
        this.player = player;
    }

}
