package rules;

import board.Board;
import board.Move;
import util.Color;
import util.MultiLevelQueue;
import util.Square;

import java.util.ArrayList;
import java.util.List;

public class QueenRules extends CommonRules {
    public List<Move> getLegalMoves(Board gamestate, Square square, Color piececolor) {
        final List<Move> moves = new ArrayList<>();
        final MultiLevelQueue<Square> squares = square.getKingMoves();
        while (squares.size() > 0) {
            final Square newSquare = squares.next();
            if (sameColorOnBothSquares(gamestate, square, newSquare)) {
                continue;
            }
            moves.add(new Move(square, newSquare,getScoreValueAtMoveEnd(gamestate,square)));
        }
        return moves;
    }
}
