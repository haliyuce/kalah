package com.backbase.homework.kalah.service.rule.impl;

import com.backbase.homework.kalah.model.Game;
import com.backbase.homework.kalah.model.MoveResponse;
import com.backbase.homework.kalah.model.Player;
import com.backbase.homework.kalah.service.rule.PostProcessors;

public class LastStoneIntoKalahPostProcessor implements PostProcessors {
    @Override
    public void checkAndApply(Game game, MoveResponse lastStoneAction, Player currentPlayer) {
        if(!lastStoneAction.isOnKalah()) {
            game.setTurn(currentPlayer.equals(game.getFirstPlayer())?game.getSecondPlayer():game.getFirstPlayer()); // take turn if last stone is not on kalah. If it is, give another round
        }
    }
}
