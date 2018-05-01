package rules;

import board.AlphaBetaAlgorithm;
import board.Board;
import board.Move;
import util.Color;
import util.FieldState;
import util.Square;
import util.UnrolledLinkedList;

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
    public List<Move> getLegalMoves(final Board gameState, final Square currentSquare, final Color piececolor) {
        final FieldState piece = gameState.getFieldState(currentSquare);
        List<Move> newmoves = new ArrayList<>();
        switch (piece) {

            case WHITE_PAWN:
            case BLACK_PAWN:
                newmoves = pawnrules.getLegalMoves(gameState, currentSquare, piececolor);
                break;
            case WHITE_ROOK:
            case BLACK_ROOK:
//                if(AlphaBetaAlgorithm.currentTurn <=2)break; //Svagt sted at cutoff men man behøver vel ikke tjekke disse træk tidligt i spillet.
                newmoves = rookrules.getLegalMoves(gameState, currentSquare, piececolor);
                break;
            case WHITE_KNIGHT:
            case BLACK_KNIGHT:
                newmoves = knightrules.getLegalMoves(gameState, currentSquare, piececolor);
                break;
            case WHITE_BISHOP:
            case BLACK_BISHOP:
                if(AlphaBetaAlgorithm.currentTurn <=2)break;
                newmoves = bishoprules.getLegalMoves(gameState, currentSquare, piececolor);
                break;
            case WHITE_QUEEN:
            case BLACK_QUEEN:
                if(AlphaBetaAlgorithm.currentTurn <=2)break;
                newmoves = queenrules.getLegalMoves(gameState, currentSquare, piececolor);
                break;
            case WHITE_KING:
            case BLACK_KING:
                newmoves = kingrules.getLegalMoves(gameState, currentSquare, piececolor);
                break;
        }
        return newmoves;
    }


    public static boolean moveResultsInCheck(final Board board, final Move move, Color piececolor) {
        final Board childBoard = new Board(board);
        childBoard.move(move);
        KingRules kingRules = new KingRules();
        return kingRules.isCheck(childBoard, piececolor);
    }


//    public static boolean inCheck(final Board board) {
//        int kingPosition = findKingPosition(board);
//        final Square kingSquare = Square.getSquare(kingPosition);
//
//        // ROOKS and QUEENS
//        final RookRules rr = new RookRules();
//        final List<Move> rookMoves = rr.getLegalMoves(board, kingSquare, board.getCurrentPlayerColor());
//        for (final Move move : rookMoves) {
//            final int endPosition = move.getEndSquare().getValue();
//            if (board.field[endPosition].getColor() == board.getCurrentPlayerColor()) continue;
//
//            if (board.field[endPosition].isRook() || board.field[endPosition].isQueen()) return true;
//        }
//
//        // BISHOPS and QUEENS
//        final BishopRules br = new BishopRules();
//        final List<Move> bishopMoves = br.getLegalMoves(board, kingSquare, board.getCurrentPlayerColor());
//        for (final Move move : bishopMoves) {
//            final int endPosition = move.getEndSquare().getValue();
//            if (board.field[endPosition].getColor() == board.getCurrentPlayerColor()) continue;
//
//            if (board.field[endPosition].isBishop() || board.field[endPosition].isQueen()) return true;
//        }
//
//        // PAWNS
//        final PawnRules pr = new PawnRules();
//        final List<Move> pawnMoves = pr.getLegalMoves(board, kingSquare, board.getCurrentPlayerColor());
//        for (final Move move : pawnMoves) {
//            final int endPosition = move.getEndSquare().getValue();
//            if (board.field[endPosition].getColor() == board.getCurrentPlayerColor()) continue;
//
//            if (board.field[endPosition].isPawn()) return true;
//        }
//
//        // KNIGHTS
//        final KnightRules kr = new KnightRules();
//        final List<Move> knightMoves = kr.getLegalMoves(board, kingSquare, board.getCurrentPlayerColor());
//        for (final Move move : knightMoves) {
//            final int endPosition = move.getEndSquare().getValue();
//            if (board.field[endPosition].getColor() == board.getCurrentPlayerColor()) continue;
//
//            if (board.field[endPosition].isKnight()) return true;
//        }
//
//        return false;
//    }

    /**
     * Finds the king and returns the position in the <code>field</code> array in a <code>Board</code>.
     *
     * @param board  Current state of the game
     * @return  The position of the king in the <code>field</code> array, or -1 if the king was not found.
     */
    static int findKingPosition(final Board board, Color kingColor) {
        int length = board.field.length-1;
        for (int i = length; i >= 0; i--) {
            if(board.getCurrentPlayerColor() == kingColor) {
                if (board.field[i].isKing()) return i;
            }
        }

        return -1;
    }

    /**
     * Checks if a specific <code>Square</code> can be taken by an enemy
     *
     * @param board  Current state of the game
     * @param square  The square to check
     * @return  True if an enemy can take the piece on <code>square</code>. Otherwise False.
     */
    public static boolean isVulnerable(final Board board, Square square) {


        return false;
    }
}
