package com.zandrach.tic_tac_toe.domain.model;

import java.util.Objects;
import java.util.UUID;

public final class Game {
    private final UUID id;
    private final Board board;

    private Game(UUID id, Board board) {
        this.id = Objects.requireNonNull(id, "id");
        this.board = Objects.requireNonNull(board, "board");
    }

    public static Game create() {
        return new Game(UUID.randomUUID(), Board.empty());
    }

    public static Game of(UUID id, Board board) {
        return new Game(id, board);
    }

    public UUID getId() {
        return id;
    }

    public Board getBoard() {
        return board;
    }

    public Game withBoard(Board newBoard) {
        return new Game(id, newBoard);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Game other)) {
            return false;
        }
        return id.equals(other.id) && board.equals(other.board);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, board);
    }
}
