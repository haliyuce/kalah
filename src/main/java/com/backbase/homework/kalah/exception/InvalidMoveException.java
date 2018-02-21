package com.backbase.homework.kalah.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidMoveException extends NegativeGameResponseException{
    public InvalidMoveException(String message) {
        super(message);
    }
}
