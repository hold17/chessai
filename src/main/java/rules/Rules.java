package rules;

import board.Board;
import board.Move;
import util.Color;
import util.FieldState;
import util.Square;

import java.util.ArrayList;
import java.util.List;

public class Rules {
    private CommonRules pawnrules, rookrules, knightrules, bishoprules, queenrules, kingrules;

    public Rules() {
        pawnrules = new PawnRules();
        rookrules = new RookRules();
        knightrules = new KnightRules();
        bishoprules = new BishopRules();
        queenrules = new QueenRules();
        kingrules = new KingRules();
    }

    public List<Move> getLegalMoves(final Board gamestate, final Square currentSquare, final Color piececolor) {
        final List<Move> moves = new ArrayList<>();
        final FieldState piece = gamestate.getFieldState(currentSquare);
        switch (piece) {
            case PAWN:
            case WHITE_PAWN:
            case BLACK_PAWN:
                moves.addAll(pawnrules.getLegalMoves(gamestate, currentSquare, piececolor));
                break;
            case ROOK:
            case WHITE_ROOK:
            case BLACK_ROOK:
                moves.addAll(rookrules.getLegalMoves(gamestate, currentSquare, piececolor));
                break;
            case KNIGHT:
            case WHITE_KNIGHT:
            case BLACK_KNIGHT:
                moves.addAll(knightrules.getLegalMoves(gamestate, currentSquare, piececolor));
                break;
            case BISHOP:
            case WHITE_BISHOP:
            case BLACK_BISHOP:
                moves.addAll(bishoprules.getLegalMoves(gamestate, currentSquare, piececolor));
                break;
            case QUEEN:
            case WHITE_QUEEN:
            case BLACK_QUEEN:
                moves.addAll(queenrules.getLegalMoves(gamestate, currentSquare, piececolor));
                break;
            case KING:
            case WHITE_KING:
            case BLACK_KING:
                moves.addAll(kingrules.getLegalMoves(gamestate, currentSquare, piececolor));
                break;
        }

        return moves;
    }

}
