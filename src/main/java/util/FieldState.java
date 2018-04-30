package util;

/**
 * A <code>FieldState</code> is the state of a single field on a chess board. Its value is used to determine the value
 * of a piece on the board. The values are based on Wikipedia's standard evaluation for each chess piece.
 */
public enum FieldState {
    EMPTY(0),
/*    KING(99),
    QUEEN(9),
    ROOK(5),
    BISHOP(3),
    KNIGHT(3),
    PAWN(1),*/
    WHITE_KING(99),
    WHITE_QUEEN(9),
    WHITE_ROOK(5),
    WHITE_BISHOP(3),
    WHITE_KNIGHT(3),
    WHITE_PAWN(1),
    BLACK_KING(-99),
    BLACK_QUEEN(-9),
    BLACK_ROOK(-5),
    BLACK_BISHOP(-3),
    BLACK_KNIGHT(-3),
    BLACK_PAWN(-1);

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
        return Math.abs(value) == WHITE_KING.getValue();
    }

    public boolean isQueen() {
        return Math.abs(value) == WHITE_QUEEN.getValue();
    }

    public boolean isRook() {
        return Math.abs(value) == WHITE_ROOK.getValue();
    }

    public boolean isBishop() {
        return Math.abs(value) == WHITE_BISHOP.getValue();
    }

    public boolean isKnight() {
        return Math.abs(value) == WHITE_KNIGHT.getValue();
    }

    public boolean isPawn() {
        return Math.abs(value) == WHITE_PAWN.getValue();
    }

    public boolean isOfficer() {
        // this is terrible
        return Math.abs(value) > WHITE_PAWN.getValue() && Math.abs(value) < WHITE_KING.getValue();
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