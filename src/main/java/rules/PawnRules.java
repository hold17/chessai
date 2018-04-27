package rules;

import board.Board;
import board.Move;
import util.Color;
import util.MultiLevelQueue;
import util.Square;

import java.util.ArrayList;
import java.util.List;

public class PawnRules extends CommonRules {
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

                int score = 0;
                if (newSquare.isEndSquare())
                    score = 1;
                // maybe return square value instead
                moves.add(new Move(currentSquare, newSquare, score));
            }
        }

        return moves;
    }

}
