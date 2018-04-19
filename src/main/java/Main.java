import Board.Board;
import util.Color;
import util.Square;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");

        System.out.println(Square.A1.getValue());
        System.out.println(Square.B3.getValue());
        System.out.println(Square.A7.getValue());
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
                    board.initializeBoard();
                    //Nulstil bræt
                    break;
                case "black":
                    board.setMachine(Color.BLACK);
                    break;
                case "white":
                    board.setMachine(Color.WHITE);
                    break;
                case "move":
                    command = sc.next();
                    // Dekod skaknotation for træk til felt indeks
                    byte[] chars = command.substring(0, 3).getBytes();
                    int pieceIndex = chars[0]-'a' + (chars[1]-'1')<<4; //Start index
                    int destinationIndex = chars[2]-'a' + (chars[3]-'1')<<4; // Slut index

                    board.move(pieceIndex, destinationIndex);
                    // Board move
                    // Ryk brik
                    break;
                    case "go":
                        break;
                default:
                    System.out.println("Ukendt kommando: " + command);
            }

            sc.nextLine(); // Smid resten af linjen væk
        }

    }
}


}
