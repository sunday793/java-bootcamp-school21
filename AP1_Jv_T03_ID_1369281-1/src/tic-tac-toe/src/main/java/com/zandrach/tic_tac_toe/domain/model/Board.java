package com.zandrach.tic_tac_toe.domain.model;

import java.util.Arrays;

public final class Board {

    public static final int SIZE = 3;
    public static final int EMPTY = Mark.NONE.getValue();

    private final int[][] cells;

    private Board(int[][] cells) {
        this.cells = cells;
    }

    public static Board empty() {
        return new Board(new int[SIZE][SIZE]);
    }

    public static Board of(int[][] source) {
        validateShape(source);
        return new Board(deepCopy(source));
    }

    private static void validateShape(int[][] source) {
        if (source.length != SIZE) {
            throw new IllegalArgumentException("Board must be " + SIZE + "x" + SIZE);
        }
        for (int[] row : source) {
            if (row.length != SIZE) {
                throw new IllegalArgumentException("Each row must have " + SIZE + " cells");
            }
        }
    }

    public Mark get(int row, int col) {
        checkBounds(row, col);
        return Mark.fromValue(cells[row][col]);
    }

    public int[][] snapshot() {
        return deepCopy(cells);
    }

    public Board withMove(int row, int col, Mark mark) {
        checkBounds(row, col);
        if (cells[row][col] != EMPTY) {
            throw new IllegalStateException("Cell (" + row + ", " + col + ") is already taken");
        }
        int[][] copy = deepCopy(cells);
        copy[row][col] = mark.getValue();
        return new Board(copy);
    }

    private static int[][] deepCopy(int[][] source) {
        int[][] dst = new int[source.length][];
        for (int i = 0; i < source.length; i++) {
            dst[i] = Arrays.copyOf(source[i], source[i].length);
        }
        return dst;
    }

    private static void checkBounds(int row, int col) {
        if (row < 0 || row >= SIZE || col < 0 || col >= SIZE) {
            throw new IndexOutOfBoundsException("(" + row + ", " + col + ")");
        }
    }

    public boolean isFull() {
        for (int[] row : cells) {
            for (int v : row) {
                if (v == EMPTY) return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Board other)) {
            return false;
        }
        return Arrays.deepEquals(this.cells, other.cells);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(cells);
    }
}
