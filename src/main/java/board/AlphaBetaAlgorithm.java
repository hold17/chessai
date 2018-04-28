package board;

import rules.Rules;
import util.Color;
import util.Square;

import java.util.List;

public class AlphaBetaAlgorithm implements MoveAlgorithm {
    private static final int MAX_ALPHA = -99999;
    private static final int MAX_BETA = -MAX_ALPHA;
    private static int[] nodesPerPly = new int[16]; // increase this if we ever get beyond 6 plies
    private final Rules RULES;
    private final int MAX_PLY;

    public AlphaBetaAlgorithm(int MAX_PLY) {
        this.RULES = new Rules(); // yes it does, I know...
        this.MAX_PLY = MAX_PLY;
    }

    @Override
    public void aiPlay(Board board) {
        prune(board, 0, MAX_ALPHA, MAX_BETA, 0);
        StringBuilder sb = new StringBuilder("# Nodes per ply:\n");
        for (int i = 0; i < nodesPerPly.length; i++)
            if (nodesPerPly[i] != 0) {
                sb.append(i).append(". ").append(nodesPerPly[i]).append("\n");
                nodesPerPly[i] = 0; // reset
            }
        System.out.println(sb);
    }

    private int prune(Board board, int score, int alpha, int beta, int ply) {
        nodesPerPly[ply]++;

        if (ply++ == MAX_PLY || board.gameOver) return score;

        return board.getCurrentPlayerColor() == Color.WHITE
                ? calculateMax(board, alpha, beta, ply) : calculateMin(board, alpha, beta, ply);
    }

    private int calculateMin(Board board, int alpha, int beta, int ply) {
        Move bestMove = null;
        for (int i = 0; i < board.field.length; i++) {
            Color pieceColor = board.field[i].getColor();
            if (Square.isValid(i) && (board.getCurrentPlayerColor() == pieceColor)) {
                final Square fromSquare = Square.getSquare(i);

                List<Move> legalMoves = RULES.getLegalMoves(board, fromSquare, pieceColor);

                for (Move legalMove : legalMoves) {
                    if (legalMove == null) continue;

                    int score = -legalMove.getScore();

                    final Board childBoard = new Board(board);

                    childBoard.move(legalMove);
                    score += prune(childBoard, score, alpha, beta, ply);

                    if (score < beta) {
                        beta = score;
                        bestMove = legalMove;
                    }

                    if (alpha >= beta) break;
                }
            }
        }

        if (bestMove != null) {
            board.move(bestMove);
        }

        return beta;
    }

    private int calculateMax(Board board, int alpha, int beta, int ply) {
        Move bestMove = null;
        for (int i = 0; i < board.field.length; i++) {
            Color piececolor = board.field[i].getColor();
            if (Square.isValid(i) && (board.getCurrentPlayerColor() == piececolor)) {
                final Square fromSquare = Square.getSquare(i);

                List<Move> legalMoves = RULES.getLegalMoves(board, fromSquare, piececolor);

                for (Move legalMove : legalMoves) {
                    if (legalMove == null) continue;

                    int score = legalMove.getScore();

                    final Board childBoard = new Board(board);

                    childBoard.move(legalMove);
                    score += prune(childBoard, score, alpha, beta, ply);

                    if (score > alpha) {
                        alpha = score;
                        bestMove = legalMove;
                    }

                    if (alpha >= beta) break;
                }
            }
        }

        if (bestMove != null) {
            board.move(bestMove);
        }

        return alpha;
    }
}