package board;

import util.Square;

public class Move {

    private final Square startSquare;
    private final Square endSquare;
    private int score;

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

    public Square getStartSquare() {
        return startSquare;
    }

    public Square getEndSquare() {
        return endSquare;
    }

    public boolean isNull() {
        return (startSquare == Square.NULL || endSquare == Square.NULL);
    }

    @Override
    public String toString() {
        return startSquare.toString() + "-" + endSquare.toString();
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}

