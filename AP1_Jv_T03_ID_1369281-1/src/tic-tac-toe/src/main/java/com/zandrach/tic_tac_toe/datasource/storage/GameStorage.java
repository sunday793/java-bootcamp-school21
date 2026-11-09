package com.zandrach.tic_tac_toe.datasource.storage;

import com.zandrach.tic_tac_toe.datasource.model.GameEntity;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class GameStorage {

    private final Map<UUID, GameEntity> games = new ConcurrentHashMap<>();

    public void save(GameEntity entity) {
        games.put(entity.getId(), entity);
    }

    public Optional<GameEntity> findById(UUID id) {
        return Optional.ofNullable(games.get(id));
    }
}
