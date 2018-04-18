package Board;

import util.Color;
import util.FieldState;

import java.util.Scanner;

public class Algorithm {

    public void StartGame() {
        Board board = new Board();
        Scanner sc = new Scanner(System.in);
        if(sc == null){
            System.exit(1);
        }
        printCurrentBoard(board);
        for (int i = 0; i < 9; i++){
            if(board.machine != board.player){
                requestUserPlay(sc, board);
            } else {
                aiPlay(board, 6);
            }
            printCurrentBoard(board);
            if(board.gameOver){
                switch (board.winner){
                    case WHITE:
                        System.out.println("Spillet er slut vinderen er: AI");
                        break;
                    case BLACK:
                        System.out.println("Spillet er slut vinderen er: Menneske");
                        break;
                    case NULL:
                        System.out.println("Spillet er slut vinderen er: Uafgjort");
                        break;
                }

                System.exit(0);
            }
        }

    }

    private static void aiPlay(Board board, int maxply) {
        int alpha = -100;
        int beta = 100; // Bruges som uendelig
        int score =  alphaBetaPruning(board,alpha,beta,0, maxply);

    }

    private static int alphaBetaPruning(Board board, int alpha, int beta, int ply, int maxply) {
        if(ply++ == maxply || board.gameOver) {
            return score(board);
        }
        if(board.player == Board.fState.O) {
            return getMax(board, alpha, beta, ply, maxply);
        }else {
            return getMin(board, alpha, beta, ply, maxply);
        }
    }

    private static int getMax(Board board, int alpha, int beta, int ply, int maxply) {
        int indexOfBestMove = -1;
        for (int i = 0; i < board.field.length; i++) {
            if(board.field[i] == Board.fState.EMPTY){
                Board modifiedBoard = board.getDeepCopy();
                modifiedBoard.move(i);
                int score = alphaBetaPruning(modifiedBoard,alpha, beta, ply,maxply);
                if (score > alpha) {
                    alpha = score;
                    indexOfBestMove = i;
                }                // Pruning.
                if (alpha >= beta) {
                    break;
                }
            }
        }
        if (indexOfBestMove != -1) {
            board.move(indexOfBestMove);
        }
        return (int)alpha;
    }

    private static int getMin(Board board, int alpha, int beta, int ply, int maxply) {
        int indexOfBestMove = -1;
        for (int i = 0; i < board.field.length; i++) {
            Board modifiedBoard = board.getDeepCopy();
            modifiedBoard.move(i);
            int score = alphaBetaPruning(modifiedBoard, alpha, beta,ply,maxply);
            if (score < beta) {
                beta = score;
                indexOfBestMove = i;
            }// Pruning.
            if (alpha >= beta) {
                break; }
        }
        if (indexOfBestMove != -1) {
            board.move(indexOfBestMove);
        }
        return (int)beta;
    }

    public static void printCurrentBoard(Board board){
        System.out.println("The boardValue looks like so: ");
        System.out.println("+-+-+-+");
        for (int i = 0; i< board.field.length; i++) {
            char f = (char) ('1'+i);
            if(board.field[i] == Board.fState.X){
                f = 'X';
            }else if (board.field[i] == Board.fState.O) {
                f = 'O';
            }
            System.out.print('|');
            System.out.print(f);
            if(i == 2 || i == 5 || i == 8) {
                System.out.println("|\n+-+-+-+");
            }
        }
    }
    public static void requestUserPlay(Scanner scanner, Board b) {
        boolean badMove = true;
        while (badMove) {
            System.out.println("Skriv nummeret på det felt hvor du vil placere din brik: ");
            int y = scanner.nextInt();
            if (y < 1 || y > 9) {
                System.out.println("Ikke lovligt felt");
            } else if (!b.move(y-1)) {
                System.out.println("Feltet er optaget");
            }else{
                badMove = false;
            }
        }
    }
    public static int score (Board board) {
        int[] boardValue = new int[] {3,2,3,2,4,2,3,2,3};
        if(board.isGameOver() && board.winner != Board.fState.EMPTY) {
            return (board.player == Board.fState.X) ? 10 : -10; // Spillet er kørt hvor der ikke bliver tjekket for vinder, og det resulterer i dårligt slutspil
        } else {
            int score = boardValue[board.currentField];
            return (board.player == Board.fState.X) ? score : -score;
        }
    }


}