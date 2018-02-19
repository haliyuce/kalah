package com.backbase.homework.kalah.service.validation.impl;

import com.backbase.homework.kalah.exception.NegativeGameResponseException;
import com.backbase.homework.kalah.model.Game;
import com.backbase.homework.kalah.model.Move;
import com.backbase.homework.kalah.service.validation.Validator;

public class RightUserTurnValidator implements Validator{
    @Override
    public void validate(Game game, Move move) throws NegativeGameResponseException {
        if(!game.getTurn().equals(move.getPlayer())) {
            throw new NegativeGameResponseException("This is not your turn");
        }
    }
}
