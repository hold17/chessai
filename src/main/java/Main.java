import board.AlphaBetaAlgorithm;
import board.Board;
import board.Move;
import board.MoveAlgorithm;
import rules.Rules;
import util.Color;
import util.FieldState;
import util.Square;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    private static final int MAJOR = 1;
    private static final int MINOR = 0;
    private static final int PATCH = 4;
    private static final boolean IS_SNAPSHOT = true;
    private static final String VERSION = "v" + MAJOR + "." + MINOR + "." + PATCH + (IS_SNAPSHOT ? "-SNAPSHOT" : "");

    public static void main(String[] args) throws Exception {
        final String fileName = System.getProperty("user.dir") + "\\log.txt";
        bum(fileName);
    }

    private static void bum(final String logPath) throws Exception {
        Scanner sc = new Scanner(System.in).useDelimiter("\\s+"); // Move skal bruge parametre, vi splitter derfor på mellemrum
        Board board = new Board();
        File logfile = new File(logPath);
        PrintWriter writer = new PrintWriter(logfile);
        /**
         *  Til winboard
         */
        String command = "";

        while (!board.gameOver) {
            command = sc.next();
            writer.write(command);
            writer.flush();
            switch (command) {
                case "quit":
                    System.exit(0);
                    break;
                case "new":
                    board.initializeBoard();
                    //Nulstil bræt
                    break;
                case "black":
                    board.setMachineColor(Color.BLACK);
                    break;
                case "protover":
                    sc.next();
                    System.out.println("feature ping=1 myname=\"ShallowOrange (" + VERSION + ")\" done=1");
                    break;
                case "ping":
                    System.out.println("pong " + sc.next());
                    break;
                case "white":
                    board.setMachineColor(Color.WHITE);
                    break;
                case "usermove":
                case "move":
//                    command = sc.next();
//                    writer.write(" "+command);
                    final String moveStr = sc.next();
                    move(moveStr, board);
                    writer.write("White: " + board.getLastMove());
                    // Dekod skaknotation for træk til felt indeks

                    break;
                case "help":
                    help(board);
                    break;
                case "go":
                    go(board);
                    break;
                case "accepted":
                case "xboard":
                case "random":
                case "level":
                case "post":
                case "hard":
                case "time":
                case "otim":
                    break;
                default:
//                    System.out.println("#Ukendt kommando: " + command);
                    if (isAMove(command))
                        writer.write("White: " + command);
                        move(command, board);
                        writer.write("Black: " + board.getLastMove());
            }

            writer.println();
            sc.nextLine(); // Smid resten af linjen væk
        }
    }

    private static void help(Board board) {
        List<List<Move>> allPossibleMoves = new ArrayList<>();

        for (int i = board.field.length; i > 0; i--) {
            if (Square.isValid(i) && (board.getPlayerColor() == board.field[i].getColor())) {
                final Rules rules = new Rules();
                final Square startSquare = Square.getSquare(i);

                if (board.getFieldState(startSquare) == FieldState.EMPTY) continue;

                allPossibleMoves.add(rules.getLegalMoves(board, startSquare, board.field[i].getColor()));
            }
        }

        System.out.println("#" + allPossibleMoves);
    }

    private static void move(String moveStr, Board board) {
        int pieceIndex = Square.getSquare(moveStr.substring(0, 2)).getValue();
        int destinationIndex = Square.getSquare(moveStr.substring(2, 4)).getValue();

        if (isPawnPromotion(moveStr))
            board.movePawnPromotion(pieceIndex, destinationIndex, moveStr.substring(4,5));
        else
            board.moveAlgebraic(pieceIndex, destinationIndex);

        go(board);
    }

    private static void go(Board board) {
        MoveAlgorithm alphaBetaAlgorithm = new AlphaBetaAlgorithm(5);
        alphaBetaAlgorithm.aiPlay(board);
        System.out.println("move " + board.getLastMove());
    }

    private static boolean isAMove(String message) {
        final String regex = "[a-h][1-8][a-h][1-8][qrbnQRBN]?";
        return message.matches(regex);
    }

    private static boolean isPawnPromotion(String message) {
        final String regex = "[a-h][1-8][a-h][1-8][qrbnQRBN]";
        return message.matches(regex);
    }

}