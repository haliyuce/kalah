package com.backbase.homework.kalah.service.rule.impl;

import com.backbase.homework.kalah.model.Board;
import com.backbase.homework.kalah.model.Game;
import com.backbase.homework.kalah.model.MoveResponse;
import com.backbase.homework.kalah.model.Player;
import com.backbase.homework.kalah.service.rule.PostProcessors;

public class ParallelEmptyPitPostProcessor implements PostProcessors {
    @Override
    public void checkAndApply(Game game, MoveResponse lastStoneAction, Player player) {

        Board board = game.getBoard();

        if(!lastStoneAction.isOnKalah()) {
            if( board.getPitStoneCount(lastStoneAction.getLastMoveX(), lastStoneAction.getLastMoveY()) == 1) {
                if(lastStoneAction.getLastMoveY() == 0) {
                    board.setWestKalah(board.getWestKalah() + board.getPitStoneCount(lastStoneAction.getLastMoveX(), 1) +1);
                } else {
                    board.setEastKalah(board.getEastKalah() + board.getPitStoneCount(lastStoneAction.getLastMoveX(), 0) +1);
                }

                board.resetPitStoneCount(lastStoneAction.getLastMoveX(), 1);
                board.resetPitStoneCount(lastStoneAction.getLastMoveX(), 0);
            }
        }

        System.out.println("AAAA eastkalah -->" + board.getEastKalah() + ", westKalah --> " + board.getWestKalah());

    }
}
