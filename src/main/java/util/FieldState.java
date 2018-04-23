package util;

/**
 * A <code>FieldState</code> is the state of a single field on a chess board. Its value is used to determine the value
 * of a piece on the board. The values are based on Wikipedia's standard evaluation for each chess piece.
 */
public enum FieldState {
    EMPTY(0),
    PAWN(1),
    KNIGHT(3),
    KING(99),
    BISHOP(3),
    ROOK(5),
    QUEEN(9),
    WHITE_PAWN(1),
    WHITE_KNIGHT(3),
    WHITE_KING(99),
    WHITE_BISHOP(3),
    WHITE_ROOK(5),
    WHITE_QUEEN(9),
    BLACK_PAWN(-1),
    BLACK_KNIGHT(-3),
    BLACK_KING(-99),
    BLACK_BISHOP(-3),
    BLACK_ROOK(-5),
    BLACK_QUEEN(-9);

    private int value;

    FieldState(final int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public Color getColor() {
        Color fieldColor = Color.NULL;
        if (isWhite()) fieldColor = Color.WHITE;
        else if (isBlack()) fieldColor = Color.BLACK;
        return fieldColor;
    }

    public boolean isWhite() {
        return value > 0;
    }

    public boolean isBlack() {
        return value < 0;
    }

    public boolean isKing() {
        return Math.abs(value) == KING.getValue();
    }

    public boolean isQueen() {
        return Math.abs(value) == QUEEN.getValue();
    }

    public boolean isRook() {
        return Math.abs(value) == ROOK.getValue();
    }

    public boolean isBishop() {
        return Math.abs(value) == BISHOP.getValue();
    }

    public boolean isKnight() {
        return Math.abs(value) == KNIGHT.getValue();
    }

    public boolean isPawn() {
        return Math.abs(value) == PAWN.getValue();
    }

    public String getLetter() {
        String letter = "";

        if (isKing()) {
            letter = "K";
        } else if (isQueen()) {
            letter = "Q";
        } else if (isRook()) {
            letter = "R";
        } else if (isBishop()) {
            letter = "B";
        } else if (isKnight()) {
            letter = "N";
        } else if (isPawn()) {
            letter = "P";
        }

        if (isBlack()) {
            letter = letter.toLowerCase();
        }

        return letter;
    }
}
