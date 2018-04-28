package rules;

import board.Board;
import board.Move;
import util.Color;
import util.MultiLevelQueue;
import util.Square;

import java.util.ArrayList;
import java.util.List;

public class KingRules extends CommonRules {

    /*
     * when the king is threatened, all moves that blocks/eliminates a checkmate
     * or a king move that brings the king out of a (potential?) checkmate
     * gets max priority. If king moves, should it be a method that checks if the new position
     * brings it in danger or should it be decided by the algorithm that the move is bad
     * purely by way of it getting a low score because the king will be checked?
     */

    @Override
    public List<Move> getLegalMoves(Board gamestate, Square currentSquare, Color piececolor) {
        final List<Move> moves = new ArrayList<>();
        MultiLevelQueue<Square> possibleMoves = currentSquare.getKingMoves();
        while (possibleMoves.size() > 0) {
            final Square newSquare = possibleMoves.next();
            final Move potentialMove = new Move(currentSquare, newSquare, getScoreValueAtMoveEnd(gamestate, newSquare));
            if (Rules.moveResultsInCheck(gamestate, potentialMove)) continue; // This doesn't work as expected?

            if (!squareIsEmpty(gamestate, newSquare)) {

                if (!sameColorOnBothSquares(gamestate, currentSquare, newSquare))

                    moves.add(potentialMove);
                possibleMoves.removeSpecificLevel(possibleMoves.getCurrentLevelName());
            } else
                // maybe return square value instead
                moves.add(new Move(currentSquare, newSquare, 0));
        }
        return moves;
    }

}
