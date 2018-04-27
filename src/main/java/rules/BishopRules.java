package rules;

import board.Board;
import board.Move;
import util.Color;
import util.MultiLevelQueue;
import util.Square;

import java.util.ArrayList;
import java.util.List;

public class BishopRules extends CommonRules {
    @Override
    public List<Move> getLegalMoves(Board gamestate, Square currentSquare, Color piececolor) {
        final List<Move> moves = new ArrayList<>();
        MultiLevelQueue<Square> possibleMoves = currentSquare.getBishopMoves();
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

/*    private boolean validBishopCapture(final Board gamestate, final Square startSquare, final Square captureSquare) {
        final FieldState possiblePiece = gamestate.getFieldState(captureSquare);
        return false;
    }*/
}
