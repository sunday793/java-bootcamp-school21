package com.zandrach.tic_tac_toe.web.mapper;

import com.zandrach.tic_tac_toe.domain.model.Board;
import com.zandrach.tic_tac_toe.web.model.BoardDto;

public class BoardWebMapper {

    public BoardDto toDto(Board board) {
        return new BoardDto(board.snapshot());
    }

    public Board toDomain(BoardDto dto) {
        return Board.of(dto.cells());
    }
}
