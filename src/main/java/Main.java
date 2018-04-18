import Board.Board;
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
            if(command == "quit") System.exit(0);

            else if(command == "new") {
                // Nulstil brættet
            }
            else if(command == "move") {
                command = sc.next();
                // Board move
                // Ryk brik
            }
            else {
                System.out.println("Ukendt kommando: " + command);
        }
        sc.nextLine(); // Smid resten af linjen væk
        }
    }


}
