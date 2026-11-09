package com.zandrach.tic_tac_toe.datasource.repository;

import com.zandrach.tic_tac_toe.datasource.mapper.GameEntityMapper;
import com.zandrach.tic_tac_toe.datasource.storage.GameStorage;
import com.zandrach.tic_tac_toe.domain.model.Game;

import java.util.Optional;
import java.util.UUID;

public class GameRepositoryImpl implements GameRepository {
    private final GameStorage storage;
    private final GameEntityMapper mapper;

    public GameRepositoryImpl(GameStorage storage, GameEntityMapper mapper) {
        this.storage = storage;
        this.mapper = mapper;
    }

    @Override
    public void save(Game game) {
        storage.save(mapper.toEntity(game));
    }

    @Override
    public Optional<Game> findById(UUID id) {
        return storage.findById(id)
                .map(mapper::toDomain);
    }
}
