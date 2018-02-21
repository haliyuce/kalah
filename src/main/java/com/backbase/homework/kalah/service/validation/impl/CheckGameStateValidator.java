package com.backbase.homework.kalah.service.validation.impl;

import com.backbase.homework.kalah.exception.ImmatureGameStateException;
import com.backbase.homework.kalah.model.Game;
import com.backbase.homework.kalah.model.Move;
import com.backbase.homework.kalah.service.validation.Validator;

public class CheckGameStateValidator implements Validator {

    @Override
    public void validate(Game game, Move move) throws ImmatureGameStateException {

        if(game.getState() == Game.GameState.FINISHED) {
            throw new ImmatureGameStateException("Game has already been finished");
        }

        if(game.getState() == Game.GameState.AWAITING_PLAYER) {
            throw new ImmatureGameStateException("Game needs another player to start");
        }

    }

}
