package com.zandrach.tic_tac_toe.domain.exception;

public class InvalidBoardException extends RuntimeException {
    public InvalidBoardException(String message) {
        super(message);
    }
}
