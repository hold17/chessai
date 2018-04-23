package board;

import util.Color;
import util.FieldState;
import util.Square;
import java.util.List;

public class Algorithm {
    public Algorithm() {}

    public void aiPlay(Board board, int maxPly) {
        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE; // Bruges som uendelig
        alphaBetaPruning(board,0,alpha,beta,0, maxPly);
    }

    private int alphaBetaPruning(Board board, int score, int alpha, int beta, int ply, int maxPly) {
        if(ply++ == maxPly || board.gameOver) {
            return score; // fortegn???
        }

        if(board.player == Color.WHITE) {
            return getMax(board, alpha, beta, ply, maxPly);
        } else {
            return getMin(board, alpha, beta, ply, maxPly);
        }
    }

    private int getMax(Board board, int alpha, int beta, int ply, int maxPly) {
        int indexOfBestPiece = -1;
        int indexOfBestMove = -1;

        for (int i = 0; i < board.field.length; i++) {
            if (Square.isValid(i) && (board.getMachineColor() == board.field[i].getColor())){
//                Løb igennem samtlige træk og find det bedste
                Rules rules = new Rules();
                Square startSquare = Square.getSquare(i);

                if (board.getFieldState(startSquare) == FieldState.EMPTY) continue;

                List<Move> moveList = rules.getMoves(board, startSquare);

                for (Move aMoveList : moveList) {
                    Square endSquare = aMoveList.getEndSquare();
                    int score = aMoveList.getScore();
                    Board modifiedBoard = board.getDeepCopy();

                    modifiedBoard.move(startSquare.getValue(), endSquare.getValue());
                    score = alphaBetaPruning(modifiedBoard, score, alpha, beta, ply, maxPly);

                    if (score > alpha) {
                        alpha = score;
                        indexOfBestPiece = startSquare.getValue();
                        indexOfBestMove = endSquare.getValue();
                    }

                    if (alpha >= beta) break;
                }
            }
        }
        if (indexOfBestMove != -1) {
            board.move(indexOfBestPiece,indexOfBestMove);
        }

        return alpha;
    }
    // Fra gamle Kryds og Bolle
    private int getMin(Board board, int alpha, int beta, int ply, int maxPly) {
        int indexOfBestPiece = -1;
        int indexOfBestMove = -1;

        for (int i = 0; i < board.field.length; i++) {
            if (Square.isValid(i) && (board.getMachineColor() == board.field[i].getColor())){
//                Løb igennem samtlige træk og find det bedste
                Rules rules = new Rules();
                Square startSquare = Square.getSquare(i);

                if (board.getFieldState(startSquare) == FieldState.EMPTY) continue;

                List<Move> moveList = rules.getMoves(board, startSquare);

                for (Move aMoveList : moveList) {
                    Square endSquare = aMoveList.getEndSquare();
                    int score = -aMoveList.getScore();
                    Board modifiedBoard = board.getDeepCopy();

                    modifiedBoard.move(startSquare.getValue(), endSquare.getValue());
                    score = alphaBetaPruning(modifiedBoard, score, alpha, beta, ply, maxPly);

                    if (score < beta) {
                        beta = score;
                        indexOfBestPiece = startSquare.getValue();
                        indexOfBestMove = endSquare.getValue();
                    }
                    // Pruning.
                    if (alpha >= beta) break;
                }
            }
        }
        if (indexOfBestMove != -1) {
            board.move(indexOfBestPiece,indexOfBestMove);
        }

        return beta;
    }




}