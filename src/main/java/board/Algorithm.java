package board;

import util.Color;
import util.Square;

import java.util.List;

public class Algorithm {

    public void aiPlay(Board board, int maxply) {
        int alpha = -100;
        int beta = 100; // Bruges som uendelig
        alphaBetaPruning(board,0,alpha,beta,0, maxply);

    }

    private int alphaBetaPruning(Board board, int score, int alpha, int beta, int ply, int maxply) {
        if(ply++ == maxply || board.gameOver) {
            return score; // fortegn???
        }
        if(board.player == Color.WHITE) {
            return getMax(board, alpha, beta, ply, maxply);
        }else {
            return getMin(board, alpha, beta, ply, maxply);
        }
    }

    private int getMax(Board board, int alpha, int beta, int ply, int maxply) {
        int indexOfBestPiece = -1;
        int indexOfBestMove = -1;
        for (int i = 0; i < board.field.length; i++) {
            boolean validSquare = (i & 0x88) == 0;
            if (validSquare && (board.getMachineColor() == board.field[i].getColor())){
//                Løb igennem samtlige træk og find det bedste
                Rules rules = new Rules();
                Square startSquare = Square.getSquare(i);
                List<Move> moveList = rules.getMoves(board, startSquare);
                for (int j = 0; j < moveList.size(); j++) {
                    Square endSquare = moveList.get(j).getEndSquare();
                    int score = moveList.get(j).getScore();
                    Board modifiedBoard = board.getDeepCopy();
                    modifiedBoard.move(startSquare.getValue(), endSquare.getValue());
                    score = alphaBetaPruning(modifiedBoard, score, alpha, beta, ply,maxply);
                    if (score > alpha) {
                        alpha = score;
                        indexOfBestPiece = startSquare.getValue();
                        indexOfBestMove = endSquare.getValue();
                    }                // Pruning.
                    if (alpha >= beta) {
                        break;
                    }
                }
            }
        }
        if (indexOfBestMove != -1) {
            board.move(indexOfBestPiece,indexOfBestMove);
        }
        return alpha;
    }
    // Fra gamle Kryds og Bolle
    private int getMin(Board board, int alpha, int beta, int ply, int maxply) {
        int indexOfBestPiece = -1;
        int indexOfBestMove = -1;
        for (int i = 0; i < board.field.length; i++) {
            boolean validSquare = (i & 0x88) == 0;
            if (validSquare && (board.getMachineColor() == board.field[i].getColor())){
//                Løb igennem samtlige træk og find det bedste
                Rules rules = new Rules();
                Square startSquare = Square.getSquare(i);
                List<Move> moveList = rules.getMoves(board, startSquare);
                for (int j = 0; j < moveList.size(); j++) {
                    Square endSquare = moveList.get(j).getEndSquare();
                    int score = -moveList.get(j).getScore();
                    Board modifiedBoard = board.getDeepCopy();
                    modifiedBoard.move(startSquare.getValue(), endSquare.getValue());
                    score = alphaBetaPruning(modifiedBoard, score, alpha, beta, ply,maxply);
                    if (score < beta) {
                        beta = score;
                        indexOfBestPiece = startSquare.getValue();
                        indexOfBestMove = endSquare.getValue();
                    }                // Pruning.
                    if (alpha >= beta) {
                        break;
                    }
                }
            }
        }
        if (indexOfBestMove != -1) {
            board.move(indexOfBestPiece,indexOfBestMove);
        }
        return beta;
    }





}