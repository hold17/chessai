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

    public List<Move> getLegalMoves(final Board gameState, final Square currentSquare, final Color piececolor) {
        final List<Move> moves = new ArrayList<>();
        final FieldState piece = gameState.getFieldState(currentSquare);
        switch (piece) {
            case PAWN:
            case WHITE_PAWN:
            case BLACK_PAWN:
                moves.addAll(pawnrules.getLegalMoves(gameState, currentSquare, piececolor));
                break;
            case ROOK:
            case WHITE_ROOK:
            case BLACK_ROOK:
                moves.addAll(rookrules.getLegalMoves(gameState, currentSquare, piececolor));
                break;
            case KNIGHT:
            case WHITE_KNIGHT:
            case BLACK_KNIGHT:
                moves.addAll(knightrules.getLegalMoves(gameState, currentSquare, piececolor));
                break;
            case BISHOP:
            case WHITE_BISHOP:
            case BLACK_BISHOP:
                moves.addAll(bishoprules.getLegalMoves(gameState, currentSquare, piececolor));
                break;
            case QUEEN:
            case WHITE_QUEEN:
            case BLACK_QUEEN:
                moves.addAll(queenrules.getLegalMoves(gameState, currentSquare, piececolor));
                break;
            case KING:
            case WHITE_KING:
            case BLACK_KING:
                moves.addAll(kingrules.getLegalMoves(gameState, currentSquare, piececolor));
                break;
        }

        return moves;
    }

    public static boolean moveResultsInCheck(final Board board, final Move move) {
        final Board childBoard = new Board(board);
        childBoard.move(move);

        return inCheck(childBoard);
    }

    /**
     * Hacky as fuck, but it sure as hell works
     *
     * @param board  Current state of the board
     * @return Whether or not the king is in check.
     */
    public static boolean inCheck(final Board board) {
        final int kingPosition = findKingPosition(board);
        final Square kingSquare = Square.getSquare(kingPosition);
        if(kingPosition == 101) {
            System.out.println("Konge");
        }

        // ROOKS and QUEENS
        final RookRules rr = new RookRules();
        final List<Move> rookMoves = rr.getLegalMoves(board, kingSquare, board.getCurrentPlayerColor());
        for (final Move move : rookMoves) {
            final int endPosition = move.getEndSquare().getValue();
            if (board.field[endPosition].getColor() == board.getCurrentPlayerColor()) continue;

            if (board.field[endPosition].isRook() || board.field[endPosition].isQueen()) return true;
        }

        // BISHOPS and QUEENS
        final BishopRules br = new BishopRules();
        final List<Move> bishopMoves = br.getLegalMoves(board, kingSquare, board.getCurrentPlayerColor());
        for (final Move move : bishopMoves) {
            final int endPosition = move.getEndSquare().getValue();
            if (board.field[endPosition].getColor() == board.getCurrentPlayerColor()) continue;

            if (board.field[endPosition].isBishop() || board.field[endPosition].isQueen()) return true;
        }

        // PAWNS
        final PawnRules pr = new PawnRules();
        final List<Move> pawnMoves = pr.getLegalMoves(board, kingSquare, board.getCurrentPlayerColor());
        for (final Move move : pawnMoves) {
            final int endPosition = move.getEndSquare().getValue();
            if (board.field[endPosition].getColor() == board.getCurrentPlayerColor()) continue;

            if (board.field[endPosition].isPawn()) return true;
        }

        // KNIGHTS
        final KnightRules kr = new KnightRules();
        final List<Move> knightMoves = kr.getLegalMoves(board, kingSquare, board.getCurrentPlayerColor());
        for (final Move move : knightMoves) {
            final int endPosition = move.getEndSquare().getValue();
            if (board.field[endPosition].getColor() == board.getCurrentPlayerColor()) continue;

            if (board.field[endPosition].isKnight()) return true;
        }

        return false;
    }

    /**
     * Finds the king and returns the position in the <code>field</code> array in a <code>Board</code>.
     *
     * @param board  Current state of the game
     * @return  The position of the king in the <code>field</code> array, or -1 if the king was not found.
     */
    public static int findKingPosition(final Board board) {
        int length = board.field.length-1;
        for (int i = length; i >= 0; i--) {
            if (board.field[i].isKing()) return i;
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
