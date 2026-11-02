package space.tictac.domain.service;

import space.tictac.domain.model.Board;
import space.tictac.domain.model.Game;

public class GameRules implements RulesControl {

    @Override
    public boolean checkWin(int[][] arr, int player) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i][0] == player && arr[i][1] == player && arr[i][2] == player) return true;
            if (arr[0][i] == player && arr[1][i] == player && arr[2][i] == player) return true;
        }
        if (arr[0][0] == player && arr[1][1] == player && arr[2][2] == player) return true;
        if (arr[0][2] == player && arr[1][1] == player && arr[2][0] == player) return true;
        return false;
    }

    @Override
    public boolean checkBoard(Game currentGame, Board newBoard, java.util.UUID playerId) {
        int[][] oldArr = currentGame.getBoard().getArr();
        int[][] newArr = newBoard.getArr();
        int playerMark = currentGame.getMark(playerId) == 'X' ? 1 : 2;
        int changes = 0;
        for (int i = 0; i < oldArr.length; i++) {

            for (int j = 0; j < oldArr.length; j++) {

                if (oldArr[i][j] != 0) {
                    if (oldArr[i][j] != newArr[i][j]) return false;
                } else {
                    if (newArr[i][j] == playerMark) changes++;
                    else if (newArr[i][j] != 0) return false;
                }
            }
        }
        return changes == 1;
    }

    @Override
    public boolean isOver(Game currentGame) {
        int[][] arr = currentGame.getBoard().getArr();
        if (checkWin(arr, 1) || checkWin(arr, 2)) return true;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[i][j] == 0) return false;
            }
        }
        return true;
    }

    public boolean isFullField(int[][] arr) {
        for (int[] row : arr) {
            for (int cells : row) {
                if (cells == 0) return false;
            }
        }
        return true;
    }

    public int minimax(int[][] arr, boolean isBotTurn) {
        if (checkWin(arr, Board.Player2)) return 10;
        if (checkWin(arr, Board.Player1)) return -10;
        if (isFullField(arr)) return 0;
        if (isBotTurn) {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr.length; j++) {
                    if (arr[i][j] == Board.Empty) {
                        arr[i][j] = Board.Player2;
                        int score = minimax(arr, false);
                        arr[i][j] = Board.Empty;
                        bestScore = Math.max(bestScore, score);
                    }
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr.length; j++) {
                    if (arr[i][j] == Board.Empty) {
                        arr[i][j] = Board.Player1;
                        int score = minimax(arr, true);
                        arr[i][j] = Board.Empty;
                        bestScore = Math.min(bestScore, score);
                    }
                }
            }
            return bestScore;
        }
    }

    public int[] getNextMove(Game game) {
        int bestScore=Integer.MIN_VALUE;
        int row=-100;
        int col=-100;
        int[][] arr = game.getBoard().getArr();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if(arr[i][j]==Board.Empty){
                    arr[i][j]=Board.Player2;
                    int score = minimax(arr, false);
                    arr[i][j]=Board.Empty;
                    if(score>bestScore){
                        row=i;
                        col=j;
                    }
                    bestScore = Math.max(bestScore,score);
                }
            }
        }
        return new int[]{row,col};
    }

}
