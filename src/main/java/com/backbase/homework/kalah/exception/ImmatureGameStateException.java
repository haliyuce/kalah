package com.backbase.homework.kalah.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ImmatureGameStateException extends NegativeGameResponseException {
    public ImmatureGameStateException(String message) {
        super(message);
    }
}
