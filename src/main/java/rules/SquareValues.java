package rules;

import util.FieldState;
import util.Square;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;

public class SquareValues {

    public SquareValues() {
        initAllSquareValues();
    }

    private static Map<Square, Integer> kingSquareValues,
            whiteQueenSquareValues, blackQueenSquareValues,
            whitePawnSquareValues, blackPawnSquareValues,
            whiteRookSquareValues, blackRookSquareValues,
            whiteBishopSquareValues, blackBishopSquareValues,
            whiteKnightSquareValues, blackKnightSquareValues;

    private static final int[][] queenValues = new int[][]{
            {2, 3, 4, 3, 4, 3, 3, 2},
            {2, 3, 4, 4, 4, 4, 3, 2},
            {3, 4, 4, 4, 4, 4, 4, 3},
            {3, 3, 4, 4, 4, 4, 3, 3},
            {2, 3, 3, 4, 4, 3, 3, 2},
            {2, 2, 2, 3, 3, 2, 2, 2},
            {2, 2, 2, 2, 2, 2, 2, 2},
            {0, 0, 0, 0, 0, 0, 0, 0}};

    private static final int[][] pawnValues = new int[][]{
            {0, 0, 0, 0, 0, 0, 0, 0},
            {7, 7, 13, 23, 26, 13, 7, 7},
            {-2, -2, 4, 12, 15, 4, -2, -2},
            {-3, -3, 2, 9, 11, 2, -3, -3},
            {-4, -4, 0, 6, 8, 0, -4, -4},
            {-4, -4, 0, 4, 6, 0, -4, -4},
            {-1, -1, 1, 5, 6, 1, -1, -1},
            {0, 0, 0, 0, 0, 0, 0, 0}};

    private static final int[][] rookValues = new int[][]{
            {9, 9, 11, 10, 11, 9, 9, 9},
            {4, 6, 7, 9, 9, 7, 6, 4},
            {9, 10, 10, 11, 11, 10, 10, 9},
            {8, 8, 8, 9, 9, 8, 8, 8},
            {6, 6, 5, 6, 6, 5, 6, 6},
            {4, 5, 5, 5, 5, 5, 5, 4},
            {3, 4, 4, 6, 6, 4, 4, 3},
            {0, 0, 0, 0, 0, 0, 0, 0}};

    private static final int[][] bishopValues = new int[][]{
            {2, 3, 4, 4, 4, 4, 3, 2},
            {4, 7, 7, 7, 7, 7, 7, 4},
            {3, 5, 6, 6, 6, 6, 5, 3},
            {3, 5, 7, 7, 7, 7, 5, 3},
            {4, 5, 6, 8, 8, 6, 5, 4},
            {4, 5, 5, -2, -2, 5, 5, 4},
            {5, 5, 5, 3, 3, 5, 5, 5},
            {0, 0, 0, 0, 0, 0, 0, 0}};

    private static final int[][] knightValues = new int[][]{
            {-2, 2, 7, 9, 9, 7, 2, -2},
            {1, 4, 12, 13, 13, 12, 4, 1},
            {5, 11, 18, 19, 19, 18, 11, 5},
            {3, 10, 14, 14, 14, 14, 10, 3},
            {0, 5, 8, 9, 9, 8, 5, 0},
            {-3, 1, 3, 4, 4, 3, 1, -3},
            {-5, -3, -1, 0, 0, -1, -3, -5},
            {-7, -5, -4, -2, -2, -4, -5, -7}};

    public Map<Square, Integer> getSquareValuesOfType(FieldState pieceType) {
        switch (pieceType) {
            case BLACK_KING: case WHITE_KING: return getKingSquareValues();
            case WHITE_QUEEN: return getWhiteQueenSquareValues();
            case BLACK_QUEEN: return getBlackQueenSquareValues();
            case WHITE_PAWN: return getWhitePawnSquareValues();
            case BLACK_PAWN: return getBlackPawnSquareValues();
            case WHITE_ROOK: return getWhiteRookSquareValues();
            case BLACK_ROOK: return getBlackRookSquareValues();
            case WHITE_BISHOP: return getWhiteBishopSquareValues();
            case BLACK_BISHOP: return getBlackBishopSquareValues();
            case WHITE_KNIGHT: return getWhiteKnightSquareValues();
            case BLACK_KNIGHT: return getBlackKnightSquareValues();
            default:
                System.out.println("[SquareValues]: oh noes!");
        }
        return null;
    }

    public Map<Square, Integer> getKingSquareValues() {
        return kingSquareValues;
    }

    public Map<Square, Integer> getWhiteQueenSquareValues() {
        return whiteQueenSquareValues;
    }

    public Map<Square, Integer> getBlackQueenSquareValues() {
        return blackQueenSquareValues;
    }

    public Map<Square, Integer> getWhitePawnSquareValues() {
        return whitePawnSquareValues;
    }

    public Map<Square, Integer> getBlackPawnSquareValues() {
        return blackPawnSquareValues;
    }

    public Map<Square, Integer> getWhiteRookSquareValues() {
        return whiteRookSquareValues;
    }

    public Map<Square, Integer> getBlackRookSquareValues() {
        return blackRookSquareValues;
    }

    public Map<Square, Integer> getWhiteBishopSquareValues() {
        return whiteBishopSquareValues;
    }

    public Map<Square, Integer> getBlackBishopSquareValues() {
        return blackBishopSquareValues;
    }

    public Map<Square, Integer> getWhiteKnightSquareValues() {
        return whiteKnightSquareValues;
    }

    public Map<Square, Integer> getBlackKnightSquareValues() {
        return blackKnightSquareValues;
    }

    private static void initAllSquareValues() {
        initKingSquareValues();
        initWhiteQueenSquareValues();
        initWhitePawnSquareValues();
        initWhiteRookSquareValues();
        initWhiteBishopSquareValues();
        initWhiteKnightSquareValues();
        initBlackQueenSquareValues();
        initBlackPawnSquareValues();
        initBlackRookSquareValues();
        initBlackBishopSquareValues();
        initBlackKnightSquareValues();
    }

    private static void initKingSquareValues() {
        kingSquareValues = new EnumMap<Square, Integer>(Square.class);
        for (final Square square : EnumSet.allOf(Square.class)) {
            if (square == Square.NULL)
                continue;
            kingSquareValues.put(square, 0);
        }
    }

    private static void initWhiteQueenSquareValues() {
        whiteQueenSquareValues = new EnumMap<Square, Integer>(Square.class);
        int i = 0, j = 0;
        initWhiteSquareValues(whiteQueenSquareValues, queenValues);
    }

    private static void initWhitePawnSquareValues() {
        whitePawnSquareValues = new EnumMap<Square, Integer>(Square.class);
        int i = 0, j = 0;
        initWhiteSquareValues(whitePawnSquareValues, pawnValues);
    }

    private static void initWhiteRookSquareValues() {
        whiteRookSquareValues = new EnumMap<Square, Integer>(Square.class);
        int i = 0, j = 0;
        initWhiteSquareValues(whiteRookSquareValues, rookValues);
    }

    private static void initWhiteBishopSquareValues() {
        whiteBishopSquareValues = new EnumMap<Square, Integer>(Square.class);
        initWhiteSquareValues(whiteBishopSquareValues, bishopValues);
    }

    private static void initWhiteKnightSquareValues() {
        whiteKnightSquareValues = new EnumMap<Square, Integer>(Square.class);
        initWhiteSquareValues(whiteKnightSquareValues, knightValues);
    }

    private static void initBlackQueenSquareValues() {
        blackQueenSquareValues = new EnumMap<Square, Integer>(Square.class);
        initBlackSquareValues(blackQueenSquareValues, queenValues);
    }

    private static void initBlackPawnSquareValues() {
        blackPawnSquareValues = new EnumMap<Square, Integer>(Square.class);
        initBlackSquareValues(blackPawnSquareValues, pawnValues);
    }

    private static void initBlackRookSquareValues() {
        blackRookSquareValues = new EnumMap<Square, Integer>(Square.class);
        initBlackSquareValues(blackRookSquareValues, rookValues);
    }

    private static void initBlackBishopSquareValues() {
        blackBishopSquareValues = new EnumMap<Square, Integer>(Square.class);
        initBlackSquareValues(blackBishopSquareValues, bishopValues);
    }

    private static void initBlackKnightSquareValues() {
        blackKnightSquareValues = new EnumMap<Square, Integer>(Square.class);
        initBlackSquareValues(blackKnightSquareValues, knightValues);
    }

    private static void initWhiteSquareValues(Map<Square, Integer> whiteSquareValues, int[][] squareValues) {
        int i = 0, j = 0;
        for (final Square square : EnumSet.allOf(Square.class)) {
            if (square == Square.NULL)
                continue;
            whiteSquareValues.put(square, squareValues[i][j]);
            j++;
            if (j == 8) {
                i++;
                j = 0;
            }
        }
    }

    private static void initBlackSquareValues(Map<Square, Integer> blackSquareValues, int[][] squareValues) {
        int i = 7, j = 0;
        for (final Square square : EnumSet.allOf(Square.class)) {
            if (square == Square.NULL)
                continue;
            blackSquareValues.put(square, squareValues[i][j]);
            j++;
            if (j == 8) {
                i--;
                j = 0;
            }
        }
    }

}