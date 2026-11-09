package com.zandrach.tic_tac_toe.datasource.mapper;

import com.zandrach.tic_tac_toe.datasource.model.GameEntity;
import com.zandrach.tic_tac_toe.domain.model.Game;


public class GameEntityMapper {

    private final BoardEntityMapper boardMapper;

    public GameEntityMapper(BoardEntityMapper boardMapper) {
        this.boardMapper = boardMapper;
    }

    public GameEntity toEntity(Game game) {
        return new GameEntity(
                game.getId(),
                boardMapper.toEntity(game.getBoard())
        );
    }

    public Game toDomain(GameEntity entity) {
        return Game.of(
                entity.getId(),
                boardMapper.toDomain(entity.getBoard())
        );
    }
}
