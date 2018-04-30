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
        ArrayList<Move> moves = new ArrayList<>();
        MultiLevelQueue<Square> possibleMoves = currentSquare.getQueenMoves();
        addMovesAI(possibleMoves,gamestate,currentSquare,moves);
//        if(piececolor == Color.WHITE) addMovesAI(possibleMoves,gamestate,currentSquare,moves);
//        if(piececolor == Color.BLACK) addMovesOpponent(possibleMoves,gamestate,currentSquare,moves);
        return moves;
    }

}
