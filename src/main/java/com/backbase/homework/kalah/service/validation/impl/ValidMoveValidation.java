package com.backbase.homework.kalah.service.validation.impl;

import com.backbase.homework.kalah.exception.NegativeGameResponseException;
import com.backbase.homework.kalah.model.Game;
import com.backbase.homework.kalah.model.Move;
import com.backbase.homework.kalah.service.validation.Validator;

public class ValidMoveValidation implements Validator{
    @Override
    public void validate(Game game, Move move) throws NegativeGameResponseException {
        if(move.getStartPitIndexY() >1 || move.getStartPitIndexY()<0 || move.getStartPitIndexX() > 5 || move.getStartPitIndexX() < 0) {
            throw new NegativeGameResponseException("X should be between 0-5 , Y should be between 0-1!!");
        }

        if((move.getPlayerOrder() == 2 && move.getStartPitIndexY() == 0) || (move.getPlayerOrder() == 1 && move.getStartPitIndexY() == 1)) {
            throw new NegativeGameResponseException("Each user can only play from his/her own pits!!");
        }
    }
}
