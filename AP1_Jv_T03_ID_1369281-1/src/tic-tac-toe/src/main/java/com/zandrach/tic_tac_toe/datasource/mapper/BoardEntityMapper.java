package com.zandrach.tic_tac_toe.datasource.mapper;

import com.zandrach.tic_tac_toe.domain.model.Board;
import com.zandrach.tic_tac_toe.datasource.model.BoardEntity;

public class BoardEntityMapper {

    public BoardEntity toEntity(Board board) {
        return new BoardEntity(board.snapshot());
    }

    public Board toDomain(BoardEntity entity) {
        return Board.of(entity.getCells());
    }
}
