package board;

import util.Color;
import util.FieldState;
import util.Square;

import java.util.ArrayList;
import java.util.List;
import util.MultiLevelQueue;


public class Rules {

    public ArrayList<Move> getMoves(final Board gamestate, Square square) {
        ArrayList<Move> moves = null;

        if(gamestate.getFieldState(square) == FieldState.PAWN) {
            moves.addAll(getLegalPawnMoves(gamestate,square,gamestate.getFieldState(square).getColor()));
        }
        return moves;

    }

    private List<Move> getLegalPawnMoves(final Board gameState, final Square square, Color color)
    {
        final List<Move> moves = new ArrayList<>();
        final MultiLevelQueue<Square> squares = square.getPawnMoves(color);
        boolean canAdvance = true;
        while (squares.size() > 0) {
            final Square newSquare = squares.next();
            if (square.sameDiagonal(newSquare)) {
                if (validPawnCapture(gameState, square, newSquare)) {
                    int score =  getScoreValueAtMoveEnd(gameState,newSquare);
                    moves.add(new Move(square, newSquare,score));
                }
            } else {
                if (canAdvance && squareIsEmpty(gameState, newSquare)) {
                    moves.add(new Move(square, newSquare,0)); // Mangler identificering af Skak
                } else {
                    canAdvance = false;
                }
            }
        }
        return moves;
    }
    private boolean validPawnCapture(final Board gameState, final Square startSquare, final Square captureSquare)
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
    private boolean squareIsEmpty(final Board gameState, final Square square)
    {
        return gameState.getFieldState(square) == FieldState.EMPTY;
    }

    private int getScoreValueAtMoveEnd(final Board gameState, final Square square) {
        int value = gameState.getFieldState(square).getValue();
        return (value > 0) ? value: -value;

    }

}
