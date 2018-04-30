package rules;

import board.Board;
import board.Move;
import util.Color;
import util.FieldState;
import util.MultiLevelQueue;
import util.Square;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class PawnRules extends CommonRules {
    @Override
    public List<Move> getLegalMoves(final Board gamestate, final Square currentSquare, Color piececolor) {
        final List<Move> moves = new ArrayList<>();
        MultiLevelQueue<Square> possibleMoves = currentSquare.getPawnMoves(piececolor);
        while (possibleMoves.size() > 0) {
            final Square newSquare = possibleMoves.next();
            if (!squareIsEmpty(gamestate, newSquare)) {
                if (!sameColorOnBothSquares(gamestate, currentSquare, newSquare) && currentSquare.sameDiagonal(newSquare))
                    moves.add(new Move(currentSquare, newSquare, getScoreValueAtMoveEnd(gamestate, newSquare)));
                possibleMoves.removeSpecificLevel(possibleMoves.getCurrentLevelName());
            } else {
                // don't move to empty diagonal square
                if (currentSquare.sameDiagonal(newSquare))
                    continue;

                int score = getScoreValueAtMoveEnd(gamestate, newSquare);
                // is it a pawn promotion move?
                if (newSquare.isEndSquare()) {
                    score += piececolor == Color.WHITE ? -10 : 10; // need better values and/or method
                    moves.addAll(generatePawnPromotionMoves(currentSquare, newSquare, score, piececolor));
                    continue;
                }

                // maybe return square value instead
                moves.add(new Move(currentSquare, newSquare, score));
            }
        }

        return moves;
    }

    private List<Move> generatePawnPromotionMoves(final Square currentSquare, final Square newSquare, int score, Color pieceColor) {
        List<Move> moves = new ArrayList<>();
        for (FieldState fieldState : EnumSet.allOf(FieldState.class)) {
            if (fieldState.getColor() == pieceColor && fieldState.isOfficer()) {
                moves.add(new Move(currentSquare, newSquare, score, true, fieldState));
            }
        }
        return moves;
    }

}