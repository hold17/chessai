package board;

import util.Color;
import util.FieldState;
import util.Square;

public class Board {
    private int moveCount;
    Color player; //Aktuel spiller
    Color winner; // Vinder
    Color machine; // AI
    private Color startingPlayer;
    private long maxMoveTime;
    private long currentMoveStartTime;

    private String lastMove;

    public boolean gameOver;

    public FieldState[] field;
    //    public fState player;
    private int currentField;
    FieldState fState;


//    public fState winner;
    public Board() {
        field = new FieldState[128];
        initializeBoard();   // 0x88

    }

    /**
     * Kopierer brættet til alpha-beta
     * @return kopi af bræt
     */
    Board getDeepCopy() {
        Board board = new Board();
        board.field = this.field.clone();
        board.player = this.player;
        board.winner = this.winner;
        board.machine = this.machine;
        board.moveCount = this.moveCount;
        board.gameOver = this.gameOver;
        board.currentField = this.currentField;
        return board;
    }

    boolean isGameOver() {
        return gameOver;
    }

    public void initializeBoard() {
        gameOver = false;
        moveCount = 0;
        winner = Color.NULL; // Ingen vinder ved start af spillet
        currentField = -1;
        startingPlayer = Color.WHITE;
        machine = Color.BLACK;
        player = Color.WHITE;

        for (int i = 0; i<field.length; i++) {
            field[i] = FieldState.EMPTY;
        }

        field[Square.A1.getValue()] = FieldState.WHITE_ROOK;
        field[(Square.B1.getValue())] = FieldState.WHITE_KNIGHT;
        field[(Square.C1.getValue())] = FieldState.WHITE_BISHOP;
        field[(Square.D1.getValue())] = FieldState.WHITE_QUEEN;
        field[(Square.E1.getValue())] = FieldState.WHITE_KING;
        field[(Square.F1.getValue())] = FieldState.WHITE_BISHOP;
        field[(Square.G1.getValue())] = FieldState.WHITE_KNIGHT;
        field[(Square.H1.getValue())] = FieldState.WHITE_ROOK;
        for (int i = Square.A2.getValue(); i<Square.A2.getValue()+8; i++) {
            field[i] = FieldState.WHITE_PAWN;
        }

        field[Square.A8.getValue()] = FieldState.BLACK_ROOK;
        field[(Square.B8.getValue())] = FieldState.BLACK_KNIGHT;
        field[(Square.C8.getValue())] = FieldState.BLACK_BISHOP;
        field[(Square.D8.getValue())] = FieldState.BLACK_KING;
        field[(Square.E8.getValue())] = FieldState.BLACK_QUEEN;
        field[(Square.F8.getValue())] = FieldState.BLACK_BISHOP;
        field[(Square.G8.getValue())] = FieldState.BLACK_KNIGHT;
        field[(Square.H8.getValue())] = FieldState.BLACK_ROOK;
        for (int i = Square.A7.getValue(); i<Square.A7.getValue()+8; i++) {
            field[i] = FieldState.BLACK_PAWN;
        }

    }

    /**
     * Tager kommandoer direkte fra konsollen, hvor der skal skrives i Winboard format, derfor skal strengen behandles
     * @param from
     * @param to
     * @return
     */
    public void move (int from, int to) {
        FieldState piece = field[from];
        // Flytning af brik, som udgangspunkt med forudsætning om lovlige træk
        field[from] = fState.EMPTY;
        field[to] = piece;
        moveCount++;
        lastMove = " " + Square.getSquare(from) + Square.getSquare(to);
        player = player == Color.WHITE ? Color.BLACK : Color.WHITE;   // Skift spiller
//        currentField = fieldnumber;
    }

    public void go(Board board, Algorithm algorithm) {
        currentMoveStartTime = System.currentTimeMillis()/1000;
        algorithm.aiPlay(board, 5);

    }

    public FieldState getFieldState(final Square square) {
        return field[square.getValue()];
    }

    public void setMachineColor(Color player) {
        this.machine = player;
    }

    Color getMachineColor() {
        return this.machine;
    }

    public String getLastMove() {
        return lastMove;
    }



}
