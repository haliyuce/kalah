package com.backbase.homework.kalah.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
public class MoveResponse {

    @Setter(AccessLevel.NONE)
    private int lastMoveX, lastMoveY;
    @Setter(AccessLevel.NONE)
    private boolean onEastKalah = false, onWestKalah;

    public MoveResponse(int lastMoveX, int lastMoveY, boolean onEastKalah, boolean onWestKalah) {
        this.lastMoveX = lastMoveX;
        this.lastMoveY = lastMoveY;
        this.onEastKalah = onEastKalah;
        this.onWestKalah = onWestKalah;
    }

    public boolean isOnKalah() {
        return onWestKalah && onEastKalah;
    }
}
