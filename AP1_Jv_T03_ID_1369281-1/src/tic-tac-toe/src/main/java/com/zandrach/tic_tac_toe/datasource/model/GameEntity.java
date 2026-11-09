package com.zandrach.tic_tac_toe.datasource.model;

import java.util.UUID;

public class GameEntity {
    private final UUID id;
    private final BoardEntity board;

    public GameEntity(UUID id, BoardEntity board) {
        this.id = id;
        this.board = board;
    }

    public UUID getId() {
        return id;
    }

    public BoardEntity getBoard() {
        return board;
    }
}
