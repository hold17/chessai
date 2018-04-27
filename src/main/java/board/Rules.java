package board;

import util.Color;
import util.FieldState;
import util.MultiLevelQueue;
import util.Square;

import java.util.ArrayList;
import java.util.List;


public class Rules {
    public List<Move> getLegalMoves(final Board gamestate, Square currentSquare) {
        final List<Move> moves = new ArrayList<>();
        final FieldState piece = gamestate.getFieldState(currentSquare);

        switch (piece) {
            case PAWN:
            case WHITE_PAWN:
            case BLACK_PAWN:
                moves.addAll(getLegalPawnMoves(gamestate, currentSquare, gamestate.getFieldState(currentSquare).getColor()));
                break;
            case ROOK:
            case WHITE_ROOK:
            case BLACK_ROOK:
                moves.addAll(getLegalRookMoves(gamestate, currentSquare));
                break;
            case KNIGHT:
            case WHITE_KNIGHT:
            case BLACK_KNIGHT:
                moves.addAll(getLegalKnightMoves(gamestate, currentSquare));
                break;
            case BISHOP:
            case WHITE_BISHOP:
            case BLACK_BISHOP:
                moves.addAll(getLegalBishopMoves(gamestate, currentSquare));
                break;
            case QUEEN:
            case WHITE_QUEEN:
            case BLACK_QUEEN:
                moves.addAll(getLegalQueenMoves(gamestate, currentSquare));
                break;
            case KING:
            case WHITE_KING:
            case BLACK_KING:
                moves.addAll(getLegalKingMoves(gamestate, currentSquare));
                break;
        }

        return moves;
    }

    private List<Move> getLegalKingMoves(final Board gameState, final Square currentSquare) {
        final List<Move> moves = new ArrayList<>();
        final MultiLevelQueue<Square> possibleMoves = currentSquare.getKingMoves();
        while (possibleMoves.size() > 0) {
            final Square newSquare = possibleMoves.next();
            if (sameColorOnBothSquares(gameState, currentSquare, newSquare)) {
                continue;
            }
            moves.add(new Move(currentSquare, newSquare, 0));
        }
        return moves;
    }

    private List<Move> getLegalQueenMoves(final Board gameState, final Square currentSquare) {
        final List<Move> moves = new ArrayList<>();
        final MultiLevelQueue<Square> possibleMoves = currentSquare.getQueenMoves();
        while (possibleMoves.size() > 0) {
            final Square newSquare = possibleMoves.next();
            if (!squareIsEmpty(gameState, newSquare)) {
                possibleMoves.removeLevel();
            }
            if (sameColorOnBothSquares(gameState, currentSquare, newSquare)) {
                continue;
            }
            moves.add(new Move(currentSquare, newSquare, 0));
        }
        return moves;
    }

    private List<Move> getLegalRookMoves(final Board gameState, final Square currentSquare) {
        final List<Move> moves = new ArrayList<>();
        final MultiLevelQueue<Square> possibleMoves = currentSquare.getRookMoves();
        while (possibleMoves.size() > 0) {
            final Square newSquare = possibleMoves.next();
            if (!squareIsEmpty(gameState, newSquare)) {
                possibleMoves.removeLevel();
            }
            if (sameColorOnBothSquares(gameState, currentSquare, newSquare)) {
                continue;
            }
            moves.add(new Move(currentSquare, newSquare, 0));
        }
        return moves;
    }

    private List<Move> getLegalBishopMoves(final Board gameState, final Square currentSquare) {
        final List<Move> moves = new ArrayList<>();
        final MultiLevelQueue<Square> possibleMoves = currentSquare.getBishopMoves();
        while (possibleMoves.size() > 0) {
            final Square newSquare = possibleMoves.next();
            if (!squareIsEmpty(gameState, newSquare)) {
                possibleMoves.removeLevel();
            }
            if (sameColorOnBothSquares(gameState, currentSquare, newSquare)) {
                continue;
            }
            moves.add(new Move(currentSquare, newSquare, 0));
        }
        return moves;
    }

    private List<Move> getLegalKnightMoves(final Board gameState, final Square currentSquare) {
        final List<Move> moves = new ArrayList<>();
        final MultiLevelQueue<Square> possibleMoves = currentSquare.getKnightMoves();
        while (possibleMoves.size() > 0) {
            Square newSquare = possibleMoves.next();
            if (sameColorOnBothSquares(gameState, currentSquare, newSquare)) {
                continue;
            }
            moves.add(new Move(currentSquare, newSquare, 0));
        }
        return moves;
    }


    private List<Move> getLegalPawnMoves(final Board gameState, final Square currentSquare, Color color) {
        final List<Move> moves = new ArrayList<>();
        final MultiLevelQueue<Square> possibleMoves = currentSquare.getPawnMoves(color);
        boolean canAdvance = true;
        while (possibleMoves.size() > 0) {
            final Square newSquare = possibleMoves.next();
            if (currentSquare.sameDiagonal(newSquare)) {
                if (validCapture(gameState, currentSquare, newSquare)) {
                    int score = getScoreValueAtMoveEnd(gameState, newSquare);
//                    moves.add(new Move(currentSquare, newSquare,score));
                    moves.add(new Move(currentSquare, newSquare, 1000));
                }
            } else {
                if (canAdvance && squareIsEmpty(gameState, newSquare)) {
                    moves.add(new Move(currentSquare, newSquare, 0)); // Mangler identificering af Skak
                } else {
                    canAdvance = false;
                }
            }
        }
        return moves;
    }

    private boolean validCapture(final Board gameState, final Square startPosition, final Square captureSquare) {
        final FieldState possiblePiece = gameState.getFieldState(captureSquare);
        if (possiblePiece == FieldState.EMPTY) {
            return false;
        }

        final FieldState piece = gameState.getFieldState(startPosition);
        if (piece.getColor() == possiblePiece.getColor()) {
            return false;
        }

        return true;
    }

    private boolean squareIsEmpty(final Board gameState, final Square square) {
        return gameState.getFieldState(square) == FieldState.EMPTY;
    }

    private int getScoreValueAtMoveEnd(final Board gameState, final Square square) {
        int value = gameState.getFieldState(square).getValue();
        return (value > 0) ? value : -value;

    }

    private boolean sameColorOnBothSquares(final Board gameState, final Square square1, final Square square2) {
        if (squareIsEmpty(gameState, square1) || squareIsEmpty(gameState, square2)) {
            return false;
        }
        final Color color1 = gameState.getFieldState(square1).getColor();
        final Color color2 = gameState.getFieldState(square2).getColor();
        return (color1 == color2);
    }

}
