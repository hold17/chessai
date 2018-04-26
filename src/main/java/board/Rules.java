package board;

import util.Color;
import util.FieldState;
import util.Square;

import java.util.ArrayList;
import java.util.List;
import util.MultiLevelQueue;


public class Rules {
    public List<Move> getLegalMoves(final Board gamestate, Square square) {
        final List<Move> moves = new ArrayList<>();
        final FieldState piece = gamestate.getFieldState(square);

        switch (piece) {
            case PAWN:
            case WHITE_PAWN:
            case BLACK_PAWN:
                moves.addAll(getLegalPawnMoves(gamestate,square,gamestate.getFieldState(square).getColor()));
                break;
            case ROOK:
            case WHITE_ROOK:
            case BLACK_ROOK:
                moves.addAll(getLegalRookMoves(gamestate, square));
//                moves.addAll(getLegalPawnMoves(gamestate,square,gamestate.getFieldState(square).getColor()));
                break;
            case KNIGHT:
            case WHITE_KNIGHT:
            case BLACK_KNIGHT:
                moves.addAll(getLegalKnightMoves(gamestate, square));
//                moves.addAll(getLegalPawnMoves(gamestate,square,gamestate.getFieldState(square).getColor()));
                break;
            case BISHOP:
            case WHITE_BISHOP:
            case BLACK_BISHOP:
                moves.addAll(getLegalBishopMoves(gamestate, square));
//                moves.addAll(getLegalPawnMoves(gamestate,square,gamestate.getFieldState(square).getColor()));
                break;
            case QUEEN:
            case WHITE_QUEEN:
            case BLACK_QUEEN:
                moves.addAll(getLegalQueenMoves(gamestate, square));
//                moves.addAll(getLegalPawnMoves(gamestate,square,gamestate.getFieldState(square).getColor()));
                break;
            case KING:
            case WHITE_KING:
            case BLACK_KING:
                moves.addAll(getLegalKingMoves(gamestate, square));
//                moves.addAll(getLegalPawnMoves(gamestate,square,gamestate.getFieldState(square).getColor()));
                break;
        }

        return moves;
    }

    private List<Move> getLegalKingMoves(final Board gameState, final Square square)
    {
        final List<Move> moves = new ArrayList<>();
        final MultiLevelQueue<Square> squares = square.getKingMoves();
        while (squares.size() > 0) {
            final Square newSquare = squares.next();
            if (sameColorOnBothSquares(gameState, square, newSquare)) {
                continue;
            }
            moves.add(new Move(square, newSquare,0));
        }
        return moves;
    }

    private List<Move> getLegalQueenMoves(final Board gameState, final Square square)
    {
        final List<Move> moves = new ArrayList<>();
        final MultiLevelQueue<Square> squares = square.getQueenMoves();
        while (squares.size() > 0) {
            final Square newSquare = squares.next();
            if (!squareIsEmpty(gameState, newSquare)) {
                squares.removeLevel();
            }
            if (sameColorOnBothSquares(gameState, square, newSquare)) {
                continue;
            }
            moves.add(new Move(square, newSquare,0));
        }
        return moves;
    }

    private List<Move> getLegalRookMoves(final Board gameState, final Square square)
    {
        final List<Move> moves = new ArrayList<>();
        final MultiLevelQueue<Square> squares = square.getRookMoves();
        while (squares.size() > 0) {
            final Square newSquare = squares.next();
            if (!squareIsEmpty(gameState, newSquare)) {
                squares.removeLevel();
            }
            if (sameColorOnBothSquares(gameState, square, newSquare)) {
                continue;
            }
            moves.add(new Move(square, newSquare,0));
        }
        return moves;
    }

    private List<Move> getLegalBishopMoves(final Board gameState, final Square square)
    {
        final List<Move> moves = new ArrayList<>();
        final MultiLevelQueue<Square> squares = square.getBishopMoves();
        while (squares.size() > 0) {
            final Square newSquare = squares.next();
            if (!squareIsEmpty(gameState, newSquare)) {
                squares.removeLevel();
            }
            if (sameColorOnBothSquares(gameState, square, newSquare)) {
                continue;
            }
            moves.add(new Move(square, newSquare,0));
        }
        return moves;
    }

    private List<Move> getLegalKnightMoves(final Board gameState, final Square square)
    {
        final List<Move> moves = new ArrayList<>();
        final MultiLevelQueue<Square> squares = square.getKnightMoves();
        while (squares.size() > 0) {
            Square newSquare = squares.next();
            if (sameColorOnBothSquares(gameState, square, newSquare)) {
                continue;
            }
            moves.add(new Move(square, newSquare,0));
        }
        return moves;
    }


    private List<Move> getLegalPawnMoves(final Board gameState, final Square square, Color color)
    {
        final List<Move> moves = new ArrayList<>();
        final MultiLevelQueue<Square> squares = square.getPawnMoves(color);
        boolean canAdvance = true;
        while (squares.size() > 0) {
            final Square newSquare = squares.next();
            if (square.sameDiagonal(newSquare)) {
                if (validPawnCapture(gameState, square, newSquare)) {
                    int score =  getScoreValueAtMoveEnd(gameState,newSquare);
//                    moves.add(new Move(square, newSquare,score));
                    moves.add(new Move(square, newSquare,-1000));
                }
            } else {
                if (canAdvance && squareIsEmpty(gameState, newSquare)) {
                    moves.add(new Move(square, newSquare,0)); // Mangler identificering af Skak
                } else {
                    canAdvance = false;
                }
            }
        }
        return moves;
    }
    private boolean validPawnCapture(final Board gameState, final Square startSquare, final Square captureSquare)
    {
        final FieldState possiblePiece = gameState.getFieldState(captureSquare);
        if (possiblePiece == FieldState.EMPTY) {
            return false;
        }

        final FieldState piece = gameState.getFieldState(startSquare);
        if (piece.getColor() == possiblePiece.getColor()) {
            return false;
        }

        return true;
    }
    private boolean squareIsEmpty(final Board gameState, final Square square)
    {
        return gameState.getFieldState(square) == FieldState.EMPTY;
    }

    private int getScoreValueAtMoveEnd(final Board gameState, final Square square) {
        int value = gameState.getFieldState(square).getValue();
        return (value > 0) ? value : -value;

    }

    private boolean sameColorOnBothSquares(final Board gameState, final Square square1, final Square square2)
    {
        if (squareIsEmpty(gameState, square1) || squareIsEmpty(gameState, square2)) {
            return false;
        }
        final Color color1 = gameState.getFieldState(square1).getColor();
        final Color color2 =  gameState.getFieldState(square2).getColor();
        return (color1 == color2);
    }

}
