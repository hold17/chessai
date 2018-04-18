package Board;


import util.Color;
import util.FieldState;
import util.Square;

public class Board {
    private int moveCount;
    public Color player; //Aktuel spiller
    public Color winner; // Vinder
    public Color machine; // AI


    public Board getDeepCopy() {
        Board board             = new Board();
        for (int i = 0; i < board.field.length; i++) {
            board.field[i] = this.field[i];
        }
        board.player       = this.player;
        board.winner            = this.winner;
        board.machine            = this.machine;
        board.moveCount         = this.moveCount;
        board.gameOver          = this.gameOver;
        board.currentField = this.currentField;
        return board;
    }

    FieldState fState;
//    public fState winner;

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean gameOver;
    public FieldState[] field;
//    public fState player;
    public int currentField;

    public Board() {
        gameOver = false;
        moveCount = 0;

        field = initializeBoard();   // HEX 88

        Color winner = null; // Ingen vinder ved start af spillet
        currentField = -1;
        Color Startingplayer = Color.WHITE;
    }

    private FieldState[] initializeBoard() {
        FieldState[] fieldStates = new FieldState[128];
        for (int i = 0; i<fieldStates.length; i++) {
            fieldStates[i] = FieldState.EMPTY;
        }
        fieldStates[Square.A1.getValue()] = FieldState.WHITE_ROOK;
        fieldStates[(Square.B1.getValue())] = FieldState.WHITE_KNIGHT;
        fieldStates[(Square.C1.getValue())] = FieldState.WHITE_BISHOP;
        fieldStates[(Square.D1.getValue())] = FieldState.WHITE_QUEEN;
        fieldStates[(Square.E1.getValue())] = FieldState.WHITE_KING;
        fieldStates[(Square.F1.getValue())] = FieldState.WHITE_BISHOP;
        fieldStates[(Square.G1.getValue())] = FieldState.WHITE_KNIGHT;
        fieldStates[(Square.H1.getValue())] = FieldState.WHITE_ROOK;
        for (int i = Square.A2.getValue(); i<fieldStates.length; i+=16) {
            fieldStates[i] = FieldState.WHITE_PAWN;
        }
        fieldStates[Square.A8.getValue()] = FieldState.BLACK_ROOK;
        fieldStates[(Square.B8.getValue())] = FieldState.BLACK_KNIGHT;
        fieldStates[(Square.C8.getValue())] = FieldState.BLACK_BISHOP;
        fieldStates[(Square.D8.getValue())] = FieldState.BLACK_KING;
        fieldStates[(Square.E8.getValue())] = FieldState.BLACK_QUEEN;
        fieldStates[(Square.F8.getValue())] = FieldState.BLACK_BISHOP;
        fieldStates[(Square.G8.getValue())] = FieldState.BLACK_KNIGHT;
        fieldStates[(Square.H8.getValue())] = FieldState.BLACK_ROOK;
        for (int i = Square.A7.getValue(); i<fieldStates.length; i+=16) {
            fieldStates[i] = FieldState.BLACK_PAWN;
        }

        return fieldStates;
    }

    /**
     * Tager kommandoer direkte fra konsollen, hvor der skal skrives i Winboard format, derfor skal strengen behandles
     * @param from
     * @param to
     * @return
     */
    public boolean move (int from, int to) {
        FieldState piece = field[from];
        // Flytning af brik, som udgangspunkt med forudsætning om lovlige træk
        field[from] = fState.EMPTY;
        field[to] = piece;
        moveCount++;

//        player = (player == fState.O) ? fState.X : fState.O;   // Skift spiller
//        currentField = fieldnumber;
        return true;
    }



}
