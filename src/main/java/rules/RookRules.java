package rules;

import board.Board;
import board.Move;
import util.Color;
import util.FieldState;
import util.MultiLevelQueue;
import util.Square;

import java.util.ArrayList;
import java.util.List;

public class RookRules extends CommonRules {
    @Override
    public List<Move> getLegalMoves(Board gamestate, Square currentSquare, Color piececolor) {
        final List<Move> moves = new ArrayList<>();
        MultiLevelQueue<Square> possibleMoves = currentSquare.getRookMoves();
        while (possibleMoves.size() > 0) {
            final Square newSquare = possibleMoves.next();
            if (!squareIsEmpty(gamestate, newSquare)) {
                if (!sameColorOnBothSquares(gamestate, currentSquare, newSquare))
                    moves.add(new Move(currentSquare, newSquare, getScoreValueAtMoveEnd(gamestate, newSquare)));
                possibleMoves.removeSpecificLevel(possibleMoves.getCurrentLevelName());
            } else
                // maybe return square value instead
                moves.add(new Move(currentSquare, newSquare, 0));
        }
        return moves;
    }

}
