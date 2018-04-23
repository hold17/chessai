import board.Board;
import util.Color;
import util.Square;
import board.Algorithm;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

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
            switch (command){
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
                    System.out.println("feature ping=1 done=1");
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
                    writer.write(moveStr);
                    move(moveStr, board);
                    // Dekod skaknotation for træk til felt indeks

                    break;
                case "go":
                    go(board);
                    break;
                case "accepted": case "xboard": case "random": case "level": case "post": case "hard": case "time": case "otim": break;
                default:
//                    System.out.println("#Ukendt kommando: " + command);
                    if (isAMove(command)) {
                        move(command, board);
                    }
            }

            writer.println();
            sc.nextLine(); // Smid resten af linjen væk
        }
    }

    private static void move(String moveStr, Board board) {
        char[] chars = moveStr.toCharArray();
        int pieceIndex = Square.getSquare(moveStr.substring(0,2)).getValue();
        int destinationIndex = Square.getSquare(moveStr.substring(2,4)).getValue();
//        System.out.println("from: " + pieceIndex + "  to: " + destinationIndex);
        board.move(pieceIndex, destinationIndex);

        go(board);
    }

    private static void go(Board board) {
        Algorithm algorithm = new Algorithm();
        algorithm.aiPlay(board,5);
        System.out.println("move" + board.getLastMove());
    }


    private static boolean isAMove(String message) {
        final String regex = "[a-h][1-8][a-h][1-8]";

        return message.matches(regex);
    }
}



