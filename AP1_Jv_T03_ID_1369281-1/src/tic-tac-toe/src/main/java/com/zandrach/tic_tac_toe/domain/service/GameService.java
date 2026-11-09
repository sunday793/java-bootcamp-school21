package com.zandrach.tic_tac_toe.domain.service;

import com.zandrach.tic_tac_toe.domain.model.Game;
import com.zandrach.tic_tac_toe.domain.model.Move;

import java.util.UUID;

public interface GameService {
    Move getNextMove(Game game);
    void validateBoard(Game game);
    boolean isGameOver(Game game);
    Game play(Game game);
    Game createGame();
    Game findById(UUID id);
}
