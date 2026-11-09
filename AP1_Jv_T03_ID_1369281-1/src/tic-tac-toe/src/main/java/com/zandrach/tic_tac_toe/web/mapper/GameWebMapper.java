package com.zandrach.tic_tac_toe.web.mapper;

import com.zandrach.tic_tac_toe.domain.model.Game;
import com.zandrach.tic_tac_toe.web.model.GameDto;

import java.util.UUID;

public class GameWebMapper {

    private final BoardWebMapper boardMapper;

    public GameWebMapper(BoardWebMapper boardMapper) {
        this.boardMapper = boardMapper;
    }

    public GameDto toDto(Game game) {
        return new GameDto(game.getId(), boardMapper.toDto(game.getBoard()));
    }

    public Game toDomain(GameDto dto, UUID id) {
        return Game.of(id, boardMapper.toDomain(dto.board()));
    }
}
