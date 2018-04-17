package util;

public enum Piece {
    NULL(0),
    PAWN(1),
    KNIGHT(2),
    KING(3),
    BISHOP(5),
    ROOK(6),
    QUEEN(7),
    WHITE_PAWN(1),
    WHITE_KNIGHT(2),
    WHITE_KING(3),
    WHITE_BISHOP(5),
    WHITE_ROOK(6),
    WHITE_QUEEN(7),
    BLACK_PAWN(-1),
    BLACK_KNIGHT(-2),
    BLACK_KING(-3),
    BLACK_BISHOP(-5),
    BLACK_ROOK(-6),
    BLACK_QUEEN(-7);

    private int value;

    Piece(final int value)
    {
        this.value = value;
    }

    public int getValue()
    {
        return value;
    }

    public Color getColor()
    {
        return (isWhite() ? Color.WHITE : Color.BLACK);
    }

    public boolean isWhite()
    {
        return value > 0;
    }

    public boolean isBlack()
    {
        return value < 0;
    }

    public boolean isKing()
    {
        return Math.abs(value) == KING.getValue();
    }

    public boolean isQueen()
    {
        return Math.abs(value) == QUEEN.getValue();
    }

    public boolean isRook()
    {
        return Math.abs(value) == ROOK.getValue();
    }

    public boolean isBishop()
    {
        return Math.abs(value) == BISHOP.getValue();
    }

    public boolean isKnight()
    {
        return Math.abs(value) == KNIGHT.getValue();
    }

    public boolean isPawn()
    {
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
