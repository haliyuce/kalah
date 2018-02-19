package com.backbase.homework.kalah.service.validation;

import com.backbase.homework.kalah.exception.NegativeGameResponseException;
import com.backbase.homework.kalah.model.Game;
import com.backbase.homework.kalah.model.Move;

public interface Validator {
    void validate(Game game, Move move) throws NegativeGameResponseException;
}
