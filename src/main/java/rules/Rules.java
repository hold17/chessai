package rules;

import board.Board;
import board.Move;
import util.Color;
import util.FieldState;
import util.Square;

import java.util.ArrayList;
import java.util.List;

public class Rules {
    private CommonRules pawnrules,rookrules,knightrules,bishoprules,queenrules,kingrules;
    public Rules() {
        pawnrules = new PawnRules();
        rookrules = new RookRules();
        knightrules = new KnightRules();
        bishoprules = new BishopRules();
        queenrules = new BishopRules();
        kingrules = new KingRules();
    }
    public List<Move> getLegalMoves(final Board gamestate, Square square, Color piececolor) {
        final List<Move> moves = new ArrayList<>();
        final FieldState piece = gamestate.getFieldState(square);
        final Color color = gamestate.getFieldState(square).getColor();
        switch (piece) {
            case PAWN:
            case WHITE_PAWN:
            case BLACK_PAWN:
                moves.addAll(pawnrules.getLegalMoves(gamestate,square,color));
                break;
            case ROOK:
            case WHITE_ROOK:
            case BLACK_ROOK:
                moves.addAll(rookrules.getLegalMoves(gamestate, square,color));
//                moves.addAll(getLegalPawnMoves(gamestate,square,gamestate.getFieldState(square).getColor()));
                break;
            case KNIGHT:
            case WHITE_KNIGHT:
            case BLACK_KNIGHT:
                moves.addAll(knightrules.getLegalMoves(gamestate, square,color));
//                moves.addAll(getLegalPawnMoves(gamestate,square,gamestate.getFieldState(square).getColor()));
                break;
            case BISHOP:
            case WHITE_BISHOP:
            case BLACK_BISHOP:
                moves.addAll(bishoprules.getLegalMoves(gamestate, square, color));
//                moves.addAll(getLegalPawnMoves(gamestate,square,gamestate.getFieldState(square).getColor()));
                break;
            case QUEEN:
            case WHITE_QUEEN:
            case BLACK_QUEEN:
                moves.addAll(queenrules.getLegalMoves(gamestate, square,color));
//                moves.addAll(getLegalPawnMoves(gamestate,square,gamestate.getFieldState(square).getColor()));
                break;
            case KING:
            case WHITE_KING:
            case BLACK_KING:
                moves.addAll(kingrules.getLegalMoves(gamestate, square,color));
//                moves.addAll(getLegalPawnMoves(gamestate,square,gamestate.getFieldState(square).getColor()));
                break;
        }

        return moves;
    }

}
