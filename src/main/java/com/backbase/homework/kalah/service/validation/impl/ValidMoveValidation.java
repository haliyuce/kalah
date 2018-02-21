package com.backbase.homework.kalah.service.validation.impl;

import com.backbase.homework.kalah.exception.InvalidMoveException;
import com.backbase.homework.kalah.model.Game;
import com.backbase.homework.kalah.model.Move;
import com.backbase.homework.kalah.service.validation.Validator;

public class ValidMoveValidation implements Validator{
    @Override
    public void validate(Game game, Move move) throws InvalidMoveException {
        if(move.getStartPitIndexY() >1 || move.getStartPitIndexY()<0 || move.getStartPitIndexX() > 5 || move.getStartPitIndexX() < 0) {
            throw new InvalidMoveException("X should be between 0-5 , Y should be between 0-1!!");
        }

        if((move.getPlayerOrder() == 2 && move.getStartPitIndexY() == 0) || (move.getPlayerOrder() == 1 && move.getStartPitIndexY() == 1)) {
            throw new InvalidMoveException("Each user can only play from his/her own pits!!");
        }
    }
}
