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
        ArrayList<Move> moves = new ArrayList<>();
        MultiLevelQueue<Square> possibleMoves = currentSquare.getKingMoves();
        if(piececolor == Color.WHITE) addMovesAI(possibleMoves,gamestate,currentSquare,moves);
        if(piececolor == Color.BLACK) addMovesOpponent(possibleMoves,gamestate,currentSquare,moves);
        return moves;
    }



    boolean isCheck(Board board, Color kingColor) {
        int kingPosition = Rules.findKingPosition(board,kingColor);
        final Square kingSquare = Square.getSquare(kingPosition);
        for (int i = 0; i < board.field.length; i++) {

            final Color pieceColor = board.field[i].getColor();

            if (kingColor != Color.NULL && Square.isValid(i) && (kingColor != pieceColor)) {
                final Square fromSquare = Square.getSquare(i);
                final List<Move> legalMoves = getLegalMoves(board, fromSquare, pieceColor);
                for (final Move legalMove : legalMoves) {
                    if (legalMove == null) continue;
                    if(legalMove.getEndSquare() == kingSquare) {
                        return true;
                    }

                }
            }
        }
        return false;
    }



    /**
     * Hacky as fuck, but it sure as hell works
     *
     * @param board  Current state of the board
     * @return Whether or not the king is in check.
     */


}
