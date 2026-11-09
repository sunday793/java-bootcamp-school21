package com.zandrach.tic_tac_toe.domain.exception;

public class GameOverException extends RuntimeException {
    public GameOverException(String message) {
        super(message);
    }
}
