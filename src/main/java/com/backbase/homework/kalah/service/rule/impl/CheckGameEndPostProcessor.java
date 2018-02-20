package com.backbase.homework.kalah.service.rule.impl;

import com.backbase.homework.kalah.model.Board;
import com.backbase.homework.kalah.model.Game;
import com.backbase.homework.kalah.model.MoveResponse;
import com.backbase.homework.kalah.model.Player;
import com.backbase.homework.kalah.service.rule.PostProcessors;

public class CheckGameEndPostProcessor implements PostProcessors {
    @Override
    public void checkAndApply(Game game, MoveResponse lastStoneAction, Player player) {
        boolean winner = true;

        Board board = game.getBoard();

        for (int i=0; i<6;i++) {
            if(board.getPitStoneCount(i, 0) != 0) {
                winner = false;
                break;
            }
        }

        if(winner) {
            game.setWinner(board.getWestKalah()>30?game.getFirstPlayer():game.getSecondPlayer());
            game.setState(Game.GameState.FINISHED);
            return;
        }

        winner = true;

        for (int i=0; i<6;i++) {
            if(board.getPitStoneCount(i, 1) != 0) {
                winner = false;
                break;
            }
        }

        if(winner) {
            game.setWinner(board.getEastKalah()>30?game.getSecondPlayer():game.getFirstPlayer());
            game.setState(Game.GameState.FINISHED);
        }
    }
}
