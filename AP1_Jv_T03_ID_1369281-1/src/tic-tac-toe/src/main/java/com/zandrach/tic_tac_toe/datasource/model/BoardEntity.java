package com.zandrach.tic_tac_toe.datasource.model;

public class BoardEntity {
    private final int[][] cells;

    public BoardEntity(int[][] cells) {
        this.cells = cells;
    }

    public int[][] getCells() {
        return cells;
    }
}
