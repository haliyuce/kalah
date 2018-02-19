package com.backbase.homework.kalah.model.rule.impl;

import com.backbase.homework.kalah.model.Board;
import com.backbase.homework.kalah.model.MoveResponse;
import com.backbase.homework.kalah.model.Player;
import com.backbase.homework.kalah.model.rule.Rule;

public class LastStoneIntoKalahRule implements Rule {
    @Override
    public void checkAndApply(Board board, MoveResponse lastStoneAction, Player player, Player turn) {
        if(!lastStoneAction.isOnKalah()) {
            turn = player; // take turn if last stone is not on kalah. If it is, give another round
        }
    }
}
