package com.zandrach.tic_tac_toe.domain.service;

import com.zandrach.tic_tac_toe.datasource.repository.GameRepository;
import com.zandrach.tic_tac_toe.domain.exception.GameNotFoundException;
import com.zandrach.tic_tac_toe.domain.exception.GameOverException;
import com.zandrach.tic_tac_toe.domain.exception.InvalidBoardException;
import com.zandrach.tic_tac_toe.domain.model.Board;
import com.zandrach.tic_tac_toe.domain.model.Game;
import com.zandrach.tic_tac_toe.domain.model.Mark;
import com.zandrach.tic_tac_toe.domain.model.Move;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GameServiceImpl implements GameService {

    private final GameRepository repository;

    public GameServiceImpl(GameRepository repository) {
        this.repository = repository;
    }

    @Override
    public Move getNextMove(Game game) {
        Board board = game.getBoard();
        Mark current = currentPlayer(board);

        Move bestMove = null;
        int bestScore = (current == Mark.X) ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (Move cell : emptyCells(board)) {
            Board next = board.withMove(cell.row(), cell.col(), current);
            int score = minimax(next, opposite(current));

            if (current == Mark.X) {
                if (score > bestScore) {
                    bestScore = score;
                    bestMove = cell;
                }
            } else {
                if (score < bestScore) {
                    bestScore = score;
                    bestMove = cell;
                }
            }
        }

        if (bestMove == null) {
            throw new GameOverException("You can't make a move!");
        }

        return bestMove;
    }

    @Override
    public void validateBoard(Game game) {
        Game savedGame = repository.findById(game.getId())
                .orElseThrow(() -> new GameNotFoundException("Game not found: " + game.getId()));

        Board savedBoard = savedGame.getBoard();
        Board incomingBoard = game.getBoard();

        int changes = 0;

        for (int row = 0; row < Board.SIZE; row++) {
            for (int col = 0; col < Board.SIZE; col++) {
                Mark savedMark = savedBoard.get(row, col);
                Mark incomingMark = incomingBoard.get(row, col);

                if (savedMark != incomingMark) {
                    changes++;

                    if (changes > 1) {
                        throw new InvalidBoardException("Only one move is allowed");
                    }

                    if (savedMark != Mark.NONE) {
                        throw new InvalidBoardException("Previous moves must not be changed");
                    }

                    if (incomingMark != Mark.X) {
                        throw new InvalidBoardException("Player must play X");
                    }
                }
            }
        }

        if (changes == 0) {
            throw new InvalidBoardException("No move was made");
        }
    }

    @Override
    public boolean isGameOver(Game game) {
        Board board = game.getBoard();
        return hasWinningLine(board, Mark.X)
                || hasWinningLine(board, Mark.O)
                || board.isFull();
    }

    @Override
    public Game play(Game game) {
        if (isGameOver(game)) {
            throw new GameOverException("Game is already over. Thanks for playing!^^");
        }

        validateBoard(game);

        if (isGameOver(game)) {
            repository.save(game);
            return game;
        }

        Move computerMove = getNextMove(game);
        Board afterComputer = game.getBoard().withMove(
                computerMove.row(),
                computerMove.col(),
                Mark.O
        );
        Game updated = game.withBoard(afterComputer);

        repository.save(updated);
        return updated;
    }

    @Override
    public Game createGame() {
        Game game = Game.create();
        repository.save(game);
        return game;
    }

    @Override
    public Game findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new GameNotFoundException("Game not found: " + id));
    }

    private int count(Board board, Mark mark) {
        int count = 0;

        for (int row = 0; row < Board.SIZE; row++) {
            for (int col = 0; col < Board.SIZE; col++) {
                if (board.get(row, col) == mark) {
                    count++;
                }
            }
        }
        return count;
    }

    private boolean hasWinningLine(Board board, Mark mark) {
        for (int row = 0; row < Board.SIZE; row++) {
            if (board.get(row, 0) == mark
                    && board.get(row, 1) == mark
                    && board.get(row, 2) == mark) {
                return true;
            }
        }

        for (int col = 0; col < Board.SIZE; col++) {
            if (board.get(0, col) == mark
                    && board.get(1, col) == mark
                    && board.get(2, col) == mark) {
                return true;
            }
        }

        if (board.get(0, 0) == mark
                && board.get(1, 1) == mark
                && board.get(2, 2) == mark) {
            return true;
        }

        if (board.get(0, 2) == mark
                && board.get(1, 1) == mark
                && board.get(2, 0) == mark) {
            return true;
        }

        return false;
    }

    private Mark currentPlayer(Board board) {
        if (count(board, Mark.X) == count(board, Mark.O)) {
            return Mark.X;
        }
        return Mark.O;
    }

    private List<Move> emptyCells(Board board) {
        List<Move> cells = new ArrayList<>();
        for (int row = 0; row < Board.SIZE; row++) {
            for (int col = 0; col < Board.SIZE; col++) {
                if (board.get(row, col) == Mark.NONE) {
                    cells.add(new Move(row, col));
                }
            }
        }
        return cells;
    }

    private int evaluate(Board board) {
        if (hasWinningLine(board, Mark.X)) return +10;
        if (hasWinningLine(board, Mark.O)) return -10;
        return 0;
    }

    private int minimax(Board board, Mark current) {
        if (hasWinningLine(board, Mark.X)
                || hasWinningLine(board, Mark.O)
                || board.isFull()) {
            return evaluate(board);
        }

        if (current == Mark.X) {
            int best = Integer.MIN_VALUE;
            for (Move cell : emptyCells(board)) {
                Board next = board.withMove(cell.row(), cell.col(), Mark.X);
                int score = minimax(next, Mark.O);
                best = Math.max(best, score);
            }
            return best;
        } else {
            int best = Integer.MAX_VALUE;
            for (Move cell : emptyCells(board)) {
                Board next = board.withMove(cell.row(), cell.col(), Mark.O);
                int score = minimax(next, Mark.X);
                best = Math.min(best, score);
            }
            return best;
        }
    }

    private Mark opposite(Mark mark) {
        return mark == Mark.X ? Mark.O : Mark.X;
    }
}
