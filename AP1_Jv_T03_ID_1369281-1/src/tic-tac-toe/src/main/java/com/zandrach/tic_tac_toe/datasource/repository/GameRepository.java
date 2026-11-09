package com.zandrach.tic_tac_toe.datasource.repository;

import com.zandrach.tic_tac_toe.domain.model.Game;

import java.util.Optional;
import java.util.UUID;

public interface GameRepository {
    void save(Game game);
    Optional<Game> findById(UUID id);
}
