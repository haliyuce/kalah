package com.backbase.homework.kalah.service.validation.impl;

import com.backbase.homework.kalah.exception.NegativeGameResponseException;
import com.backbase.homework.kalah.model.Game;
import com.backbase.homework.kalah.model.Move;
import com.backbase.homework.kalah.service.validation.Validator;

public class CheckGameStateValidator implements Validator {

    @Override
    public void validate(Game game, Move move) throws NegativeGameResponseException {

        if(game.getState() == Game.GameState.FINISHED) {
            throw new NegativeGameResponseException("Game has already been finished");
        }

        if(game.getState() == Game.GameState.AWAITING_PLAYER) {
            throw new NegativeGameResponseException("Game needs another player to start");
        }

    }

}
