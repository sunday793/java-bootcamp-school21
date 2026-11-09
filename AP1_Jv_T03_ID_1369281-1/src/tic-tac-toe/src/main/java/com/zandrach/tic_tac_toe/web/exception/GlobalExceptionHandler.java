package com.zandrach.tic_tac_toe.web.exception;

import com.zandrach.tic_tac_toe.domain.exception.GameNotFoundException;
import com.zandrach.tic_tac_toe.domain.exception.GameOverException;
import com.zandrach.tic_tac_toe.domain.exception.InvalidBoardException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidBoardException.class)
    public ResponseEntity<String> handleInvalidBoard(InvalidBoardException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(GameNotFoundException.class)
    public ResponseEntity<String> handleNotFound(GameNotFoundException e) {
        return ResponseEntity.status(404).body(e.getMessage());
    }

    @ExceptionHandler(GameOverException.class)
    public ResponseEntity<String> handleGameOver(GameOverException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
