package com.backbase.homework.kalah.model.rule.impl;

import com.backbase.homework.kalah.model.Board;
import com.backbase.homework.kalah.model.MoveResponse;
import com.backbase.homework.kalah.model.Player;
import com.backbase.homework.kalah.model.rule.Rule;

public class ParallelEmptyPitRule implements Rule {
    @Override
    public void checkAndApply(Board board, MoveResponse lastStoneAction, Player player, Player turn) {

        if(!lastStoneAction.isOnKalah()) {
            if( board.getPitStoneCount(lastStoneAction.getLastMoveX(), lastStoneAction.getLastMoveY()) == 1) {
                if(lastStoneAction.getLastMoveY() == 0) {
                    board.setWestKalah(board.getWestKalah() + board.getPitStoneCount(lastStoneAction.getLastMoveX(), 1));
                    board.resetPitStoneCount(lastStoneAction.getLastMoveX(), 0);
                } else {
                    board.setEastKalah(board.getEastKalah() + board.getPitStoneCount(lastStoneAction.getLastMoveX(), 0));
                    board.resetPitStoneCount(lastStoneAction.getLastMoveX(), 1);
                }
            }
        }

        System.out.println("eastkalah -->" + board.getEastKalah() + ", westKalah --> " + board.getWestKalah());

    }
}
