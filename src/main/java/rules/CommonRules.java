package rules;

import board.Board;
import board.Move;
import util.*;

import java.util.ArrayList;
import java.util.List;

public abstract class CommonRules {
    abstract public List<Move> getLegalMoves(final Board gamestate, final Square square, final Color piececolor);

    boolean squareIsEmpty(final Board gameState, final Square square) {
        return gameState.getFieldState(square) == FieldState.EMPTY;
    }

    boolean sameColorOnBothSquares(final Board gameState, final Square square1, final Square square2) {
        final Color color1 = gameState.getFieldState(square1).getColor();
        final Color color2 = gameState.getFieldState(square2).getColor();
        return (color1 == color2);
    }

    int getScoreValueAtMoveEnd(final Board gameState, final Square square) {
        final int value = gameState.getFieldState(square).getValue();
        return (value > 0) ? value : -value;
    }

    void addMovesAI(MultiLevelQueue<Square> possibleMoves, Board gamestate, Square currentSquare, List moves){
        int score = -100;
        int currentscore = 0;
        while (possibleMoves.size() > 0 && currentscore > score) {
            final Square newSquare = possibleMoves.next();
            if (!squareIsEmpty(gamestate, newSquare)) {
                if (!sameColorOnBothSquares(gamestate, currentSquare, newSquare)) {
                    moves.add(new Move(currentSquare, newSquare, currentscore));

                }
//                    Tildel point hvis modstanderen sættes i skak
                possibleMoves.removeSpecificLevel(possibleMoves.getCurrentLevelName());
            }
            score = currentscore;
        }
    }
    void addMovesOpponent(MultiLevelQueue<Square> possibleMoves, Board gamestate, Square currentSquare, List moves){
        int score = 100;
        int currentscore = 0;
        while (possibleMoves.size() > 0 && currentscore < score) {
            final Square newSquare = possibleMoves.next();
            if (!squareIsEmpty(gamestate, newSquare)) {
                if (!sameColorOnBothSquares(gamestate, currentSquare, newSquare)) {
                    moves.add(new Move(currentSquare, newSquare, currentscore));

                }
//                    Tildel point hvis modstanderen sættes i skak
                possibleMoves.removeSpecificLevel(possibleMoves.getCurrentLevelName());
            }
            score = currentscore;
        }
    }


/*    private boolean allMovesPutKingInCheck(final Board gameState, final Square square, final Color piececolor) {
        final List<Move> moves = getLegalMoves(gameState, square, piececolor);
        for (final Move m : moves) {
            if (!movePutsKingInCheck(gameState, m)) {
                return false;
            }
        }
        return true;
    }*/

/*    private boolean movePutsKingInCheck(Board gameState, Move m) {
        return false;
    }*/

}

