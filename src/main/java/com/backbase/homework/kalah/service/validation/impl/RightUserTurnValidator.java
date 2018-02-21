package com.backbase.homework.kalah.service.validation.impl;

import com.backbase.homework.kalah.exception.WrongUserTurnException;
import com.backbase.homework.kalah.model.Game;
import com.backbase.homework.kalah.model.Move;
import com.backbase.homework.kalah.service.validation.Validator;

public class RightUserTurnValidator implements Validator{
    @Override
    public void validate(Game game, Move move) throws WrongUserTurnException {
        if(!game.getTurn().equals(move.getPlayer())) {
            throw new WrongUserTurnException("This is not your turn");
        }
    }
}
