package rules;

import board.Board;
import board.Move;
import util.Color;
import util.FieldState;
import util.MultiLevelQueue;
import util.Square;

import java.util.ArrayList;
import java.util.List;


public abstract class CommonRules {

    abstract public List<Move> getLegalMoves(final Board gamestate, Square square, Color piececolor);

    boolean squareIsEmpty(final Board gameState, final Square square)
    {
        return gameState.getFieldState(square) == FieldState.EMPTY;
    }

    int getScoreValueAtMoveEnd(final Board gameState, final Square square) {
        int value = gameState.getFieldState(square).getValue();
        return (value > 0) ? value : -value;

    }

    boolean sameColorOnBothSquares(final Board gameState, final Square square1, final Square square2)
    {
        if (squareIsEmpty(gameState, square1) || squareIsEmpty(gameState, square2)) {
            return false;
        }
        final Color color1 = gameState.getFieldState(square1).getColor();
        final Color color2 =  gameState.getFieldState(square2).getColor();
        return (color1 == color2);
    }
    boolean canCapture(final Board gameState, final Square startSquare, final Square captureSquare)
    {
        final FieldState possiblePiece = gameState.getFieldState(captureSquare);
        if (possiblePiece == FieldState.EMPTY) {
            return false;
        }

        final FieldState piece = gameState.getFieldState(startSquare);
        if (piece.getColor() == possiblePiece.getColor()) {
            return false;
        }

        return true;
    }
    private boolean allMovesPutKingInCheck(final Board gameState, Square square, Color piececolor)
    {
        final List<Move> moves = getLegalMoves(gameState,square,piececolor);
        for (final Move m : moves) {
            if (!movePutsKingInCheck(gameState, m)) {
                return false;
            }
        }
        return true;
    }

    private boolean movePutsKingInCheck(Board gameState, Move m) {
        return false;
    }


}

