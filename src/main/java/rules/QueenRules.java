package rules;

import board.Board;
import board.Move;
import util.Color;
import util.MultiLevelQueue;
import util.Square;

import java.util.ArrayList;
import java.util.List;

public class QueenRules extends CommonRules {
    public List<Move> getLegalMoves(Board gamestate, Square currentSquare, Color piececolor) {
        final List<Move> moves = new ArrayList<>();
        MultiLevelQueue<Square> possibleMoves = currentSquare.getQueenMoves();
        while (possibleMoves.size() > 0) {
            final Square newSquare = possibleMoves.next();
            if (!squareIsEmpty(gamestate, newSquare)) {
                if (!sameColorOnBothSquares(gamestate, currentSquare, newSquare))
                    moves.add(new Move(currentSquare, newSquare, getScoreValueAtMoveEnd(gamestate, currentSquare, newSquare)));
                possibleMoves.removeSpecificLevel(possibleMoves.getCurrentLevelName());
            } else
                // maybe return square value instead
                moves.add(new Move(currentSquare, newSquare, getScoreValueAtMoveEnd(gamestate, currentSquare, newSquare)));
        }
        return moves;
    }

}
