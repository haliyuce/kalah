package com.backbase.homework.kalah.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class WrongUserTurnException extends NegativeGameResponseException{

    public WrongUserTurnException(String message) {
        super(message);
    }
}
