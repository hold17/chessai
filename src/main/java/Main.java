import board.Board;
import util.Color;
import util.Square;
import board.Algorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in).useDelimiter("\\s+"); // Move skal bruge parametre, vi splitter derfor på mellemrum
        Board board = new Board();
        File logfile = new File("C:\\Users\\Justin\\IdeaProjects\\chessai-java\\log.txt");
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
                    System.out.println("debug");
                    System.out.println("usermove");
                    board.initializeBoard();
                    //Nulstil bræt
                    break;
                case "black":
                    board.setMachineColor(Color.BLACK);
                    break;
                case "white":
                    board.setMachineColor(Color.WHITE);
                    break;
                case "usermove":
                case "move":
                    command = sc.next();
                    writer.write(" "+command);
                    // Dekod skaknotation for træk til felt indeks
                    char[] chars = command.toCharArray();
                    int pieceIndex = chars[0]-'a' + (chars[1]-'1')<<4; //Start index
                    int destinationIndex = chars[2]-'a' + (chars[3]-'1')<<4; // Slut index
                    System.out.println("#"+pieceIndex+" " + destinationIndex);
                    board.move(pieceIndex, destinationIndex);
                    break;
                case "go":
                    Algorithm algorithm = new Algorithm();
                    algorithm.aiPlay(board,5);
//                    System.out.println(board.getLastMove());
                    break;
                default:
                    System.out.println("#Ukendt kommando: " + command);
            }

            writer.println();
            sc.nextLine(); // Smid resten af linjen væk
        }

    }
}



