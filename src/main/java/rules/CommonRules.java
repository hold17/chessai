package rules;

import board.Board;
import board.Move;
import util.Color;
import util.FieldState;
import util.Square;

import java.util.List;

public abstract class CommonRules {
    abstract public List<Move> getLegalMoves(final Board gamestate, final Square square, final Color piececolor);

    boolean squareIsEmpty(final Board gameState, final Square square) {
        return gameState.getFieldState(square) == FieldState.EMPTY;
    }

    boolean sameColorOnBothSquares(final Board gameState, final Square square1, final Square square2) {
        final Color color1 = gameState.getFieldState(square1).getColor();
        final Color color2 = gameState.getFieldState(square2).getColor();
        return (color1 == color2);
    }

    int getScoreValueAtMoveEnd(final Board gameState, final Square square) {
        final int value = gameState.getFieldState(square).getValue();
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

