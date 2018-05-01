package board;

import util.FieldState;
import util.Square;

public class Move {
    private final Square startSquare;
    private final Square endSquare;
    private int score;
    private boolean isPawnPromotion = false;
    private FieldState officerType = FieldState.EMPTY;

    public Move() {
        startSquare = Square.NULL;
        endSquare = Square.NULL;
        score = 0;
    }

    public Move(final Square startSquare, final Square endSquare, int score) {
        this.startSquare = startSquare;
        this.endSquare = endSquare;
        this.score = score;
    }

    public Move(final Square startSquare, final Square endSquare, int score, final boolean isPawnPromotion, final FieldState officerType) {
       this.startSquare = startSquare;
       this.endSquare = endSquare;
       this.score = score;
       this.isPawnPromotion = isPawnPromotion;
       this.officerType = officerType;
    }

    public Move(final Square startSquare, final Square endSquare) {
        this(startSquare, endSquare, 0);
    }

    public Square getStartSquare() {
        return startSquare;
    }

    public Square getEndSquare() {
        return endSquare;
    }

    public int getScore() {
        return score;
    }

    public FieldState getOfficerType() {
        return officerType;
    }

    public boolean isPawnPromotion() {
        return isPawnPromotion;
    }

    public boolean isNull() {
        return (startSquare == Square.NULL || endSquare == Square.NULL);
    }

    public void setScore(int score) {
        this.score = score;
    }

    public  void setPawnPromotion(boolean isPawnPromotion) {
        this.isPawnPromotion = isPawnPromotion;
    }

    public void setOfficerType(FieldState officerType) {
        this.officerType = officerType;
    }

    @Override
    public String toString() {
        return startSquare.toString() + "-" + endSquare.toString();
    }

}