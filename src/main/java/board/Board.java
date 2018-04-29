package board;

import util.Color;
import util.FieldState;
import util.Square;

public class Board {
    public FieldState[] field;
    private String lastMove;
    private int moveCount;
    private int currentField;
    public boolean gameOver;
    private Color startingPlayer;
    private Color player; //Aktuel spiller
    private Color machine; // AI
    private Color winner; // Vinder
//    private long maxMoveTime;
//    private long currentMoveStartTime;

    public Board() {
        field = new FieldState[128];
        initializeBoard();   // HEX 88
    }

    public Board(Board board) {
        this.field = board.field.clone();
        this.player = board.player;
        this.winner = board.winner;
        this.machine = board.machine;
        this.moveCount = board.moveCount;
        this.gameOver = board.gameOver;
        this.currentField = board.currentField;
        this.startingPlayer = board.startingPlayer;
        this.lastMove = board.lastMove;
//        this.maxMoveTime = board.maxMoveTime;
//        this.currentMoveStartTime = board.currentMoveStartTime;
    }

    public void initializeBoard() {
        gameOver = false;
        moveCount = 0;
        winner = Color.NULL; // Ingen vinder ved start af spillet
        currentField = -1;
        startingPlayer = Color.WHITE;
        machine = Color.BLACK;
        player = Color.WHITE;

        for (int i = 0; i < field.length; i++) {
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
        for (int i = Square.A2.getValue(); i < Square.A2.getValue() + 8; i++) {
            field[i] = FieldState.WHITE_PAWN;
        }

        field[Square.A8.getValue()] = FieldState.BLACK_ROOK;
        field[(Square.B8.getValue())] = FieldState.BLACK_KNIGHT;
        field[(Square.C8.getValue())] = FieldState.BLACK_BISHOP;
        field[(Square.D8.getValue())] = FieldState.BLACK_QUEEN;
        field[(Square.E8.getValue())] = FieldState.BLACK_KING;
        field[(Square.F8.getValue())] = FieldState.BLACK_BISHOP;
        field[(Square.G8.getValue())] = FieldState.BLACK_KNIGHT;
        field[(Square.H8.getValue())] = FieldState.BLACK_ROOK;
        for (int i = Square.A7.getValue(); i < Square.A7.getValue() + 8; i++) {
            field[i] = FieldState.BLACK_PAWN;
        }
    }

    /**
     * Tager kommando i Winboard format, derfor skal strengen behandles
     *
     * @param from
     * @param to
     */
    public void moveAlgebraic(int from, int to) {
        final Square fromSquare = Square.getSquare(from);
        final Square toSquare = Square.getSquare(to);

        Move move = new Move(fromSquare, toSquare, 0);
        move(move);
    }

    /**
     * Tager pawn promotion kommando i Winboard format, derfor skal strengen behandles
     *
     * @param from
     * @param to
     * @param officerType Queen, Rook, Bishop, Knight
     */
    public void movePawnPromotion(int from, int to, String officerType) {
        final Square fromSquare = Square.getSquare(field[from].getValue());
        final Square toSquare = Square.getSquare(field[to].getValue());

        Move move = new Move(fromSquare, toSquare, 0, true, getFieldStateByLetter(officerType));
        move(move);
    }

    public void move(Move move) {
        final int from = move.getStartSquare().getValue();
        final int to = move.getEndSquare().getValue();

        lastMove = " " + Square.getSquare(from) + Square.getSquare(to);

        FieldState piece;
        if (move.isPawnPromotion()) {
            piece = move.getOfficerType();
            lastMove += piece.getLetter();
        } else
            piece = field[from];

        // Flytning af brik, som udgangspunkt med forudsætning om lovlige træk
        field[from] = FieldState.EMPTY;
        field[to] = piece;

        moveCount++;

        if (player == Color.WHITE) player = Color.BLACK;
        else player = Color.WHITE;
    }

//    public void go(Board board, MoveAlgorithm moveAlgorithm) {
//        currentMoveStartTime = System.currentTimeMillis() / 1000;
//        moveAlgorithm.aiPlay(board);
//    }

    boolean isGameOver() {
        return gameOver;
    }

    public FieldState getFieldState(final Square square) {
        return field[square.getValue()];
    }

    public Color getMachineColor() {
        return this.machine;
    }

    public Color getPlayerColor() {
        return this.startingPlayer;
    }

    public Color getCurrentPlayerColor() {
        return this.player;
    }

    public String getLastMove() {
        return lastMove;
    }

    public void setMachineColor(Color player) {
        this.machine = player;
    }

    private FieldState getFieldStateByLetter(String letter) {
        switch(letter) {
            case "Q": return FieldState.WHITE_QUEEN;
            case "R": return FieldState.WHITE_ROOK;
            case "B": return FieldState.WHITE_BISHOP;
            case "N": return FieldState.WHITE_KNIGHT;
            case "P": return FieldState.WHITE_PAWN;
            case "q": return FieldState.BLACK_QUEEN;
            case "r": return FieldState.BLACK_ROOK;
            case "b": return FieldState.BLACK_BISHOP;
            case "n": return FieldState.BLACK_KNIGHT;
            case "p": return FieldState.BLACK_PAWN;
            default:
                System.err.println("[Board.getFieldStateByLetter]: Oh noes! This argument doesn't work:" + letter);
        }
        return null;
    }

}