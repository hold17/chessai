package rules;

import board.Board;
import board.Move;
import util.Color;
import util.MultiLevelQueue;
import util.Square;

import java.util.ArrayList;
import java.util.List;

public class PawnRules extends CommonRules {
    public List<Move> getLegalMoves(final Board gameState, final Square square, Color piececolor)
    {
        final List<Move> moves = new ArrayList<>();
        final MultiLevelQueue<Square> squares = square.getPawnMoves(piececolor);
        boolean canAdvance = true;
        int score = 0;
        while (squares.size() > 0) {
            final Square newSquare = squares.next();
            if (square.sameDiagonal(newSquare)) {
                if (validPawnCapture(gameState, square, newSquare)) {
                    score =  getScoreValueAtMoveEnd(gameState,newSquare);
//                    moves.add(new Move(square, newSquare,score));
                    moves.add(new Move(square, newSquare,score));
                }
            } else {
                if (canAdvance && squareIsEmpty(gameState, newSquare)) {
                    if(newSquare.isEndSquare()) score =+20;
                    moves.add(new Move(square, newSquare,score)); // Mangler identificering af Skak
                } else {
                    canAdvance = false;
                }
            }
        }
        return moves;
    }
//    private boolean validPawnCapture(final Board gameState, final Square startSquare, final Square captureSquare)
//    {
//        final FieldState possiblePiece = gameState.getFieldState(captureSquare);
//        if (possiblePiece == FieldState.EMPTY) {
//            return false;
//        }
//
//        final FieldState piece = gameState.getFieldState(startSquare);
//        if (piece.getColor() == possiblePiece.getColor()) {
//            return false;
//        }
//
//        return true;
//    }
    private boolean validPawnCapture(final Board gameState, final Square startSquare, final Square captureSquare) {
        return canCapture(gameState,startSquare,captureSquare);
    }
}
