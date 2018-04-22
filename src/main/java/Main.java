import board.Board;
import util.Color;
import util.Square;
import board.Algorithm;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in).useDelimiter("\\s+"); // Move skal bruge parametre, vi splitter derfor på mellemrum
        Board board = new Board();


        /**
         *  Til winboard
         */
        String command = "";
        while (!board.gameOver) {
            command = sc.next();

            switch (command){
                case "quit":
                    System.exit(0);
                    break;
                case "new":
                    System.out.println("New");
                    board.initializeBoard();
                    //Nulstil bræt
                    break;
                case "black":
                    board.setMachineColor(Color.BLACK);
                    break;
                case "white":
                    board.setMachineColor(Color.WHITE);
                    break;
                case "move":
                    command = sc.next();
                    // Dekod skaknotation for træk til felt indeks
                    byte[] chars = command.substring(0, 3).getBytes();
                    int pieceIndex = chars[0]-'a' + (chars[1]-'1')<<4; //Start index
                    int destinationIndex = chars[2]-'a' + (chars[3]-'1')<<4; // Slut index
                    board.move(pieceIndex, destinationIndex);
                    break;
                case "go":
                    Algorithm algorithm = new Algorithm();
                    algorithm.aiPlay(board,5);
                    System.out.println(board.getLastMove());
                    break;
                default:
                    System.out.println("Ukendt kommando: " + command);
            }

            sc.nextLine(); // Smid resten af linjen væk
        }

    }
}



