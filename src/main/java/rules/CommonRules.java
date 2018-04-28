package rules;

import board.Board;
import board.Move;
import util.Color;
import util.FieldState;
import util.Square;

import java.util.*;

public abstract class CommonRules {
    private static final Map<FieldState, Map<Square, Integer>> STATIC_EVAL_VALUES = new EnumMap<>(FieldState.class);

    static {
        final SquareValues sq = new SquareValues();
        for (FieldState piece : EnumSet.allOf(FieldState.class)) {
            if (piece == FieldState.EMPTY)
                continue;
            STATIC_EVAL_VALUES.put(piece, sq.getSquareValuesOfType(piece));
        }
        // System.out.println(STATIC_EVAL_VALUES.toString());
    }

    abstract public List<Move> getLegalMoves(final Board gamestate, final Square square, final Color piececolor);

    boolean squareIsEmpty(final Board gameState, final Square square) {
        return gameState.getFieldState(square) == FieldState.EMPTY;
    }

    boolean sameColorOnBothSquares(final Board gameState, final Square square1, final Square square2) {
        final Color color1 = gameState.getFieldState(square1).getColor();
        final Color color2 = gameState.getFieldState(square2).getColor();
        return (color1 == color2);
    }

    int getScoreValueAtMoveEnd(final Board gameState, final Square currentSquare, final Square newSquare) {
        final int value = gameState.getFieldState(newSquare).getValue()
                + STATIC_EVAL_VALUES.get(gameState.getFieldState(currentSquare)).get(newSquare);
        return (value > 0) ? value : -value;
    }

/*    private boolean allMovesPutKingInCheck(final Board gameState, final Square square, final Color piececolor) {
        final List<Move> moves = getLegalMoves(gameState, square, piececolor);
        for (final Move m : moves) {
            if (!movePutsKingInCheck(gameState, m)) {
                return false;
            }
        }
        return true;
    }*/

/*    private boolean movePutsKingInCheck(Board gameState, Move m) {
        return false;
    }*/

}

