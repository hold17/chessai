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
        long iterationTimeStart = System.currentTimeMillis();
        prune(board, 0, MAX_ALPHA, MAX_BETA, 0);
        long iterationTimePassed = System.currentTimeMillis() - iterationTimeStart;
        System.err.println("Time " + iterationTimePassed + " ms");
        StringBuilder sb = new StringBuilder("# Nodes per ply:\n");

        for (int i = 0; i < nodesPerPly.length; i++) {
            if (nodesPerPly[i] != 0) {
                sb.append(i).append(". ").append(nodesPerPly[i]).append("\n");
                nodesPerPly[i] = 0; // reset
            }
        }

        System.err.println(sb);
    }

    private int prune(Board board, int score, int alpha, int beta, int ply) {
        nodesPerPly[ply]++;

        if (ply++ == MAX_PLY || board.gameOver) return score;

        return calculateMinMax(board, alpha, beta, ply);
    }


    private int calculateMinMax(final Board board, int alpha, int beta, final int ply) {
        final Color playerColor = board.getCurrentPlayerColor();
        Move bestMove = null;

        for (int i = 0; i < board.field.length; i++) {

            final Color pieceColor = board.field[i].getColor();

            if (Square.isValid(i) && (playerColor == pieceColor)) {
                final Square fromSquare = Square.getSquare(i);
                final List<Move> legalMoves = RULES.getLegalMoves(board, fromSquare, pieceColor);

                for (final Move legalMove : legalMoves) {
                    if (legalMove == null) continue;

                    int score = playerColor == Color.WHITE ? legalMove.getScore() : -legalMove.getScore();

                    final Board childBoard = new Board(board);

                    childBoard.move(legalMove);
                    score += prune(childBoard, score, alpha, beta, ply);

                    if (score > alpha && playerColor == Color.WHITE) {
                        alpha = score;
                        bestMove = legalMove;
                    } else if (score < beta && playerColor == Color.BLACK) {
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

        return playerColor == Color.WHITE ? alpha : beta;
    }
}