package space.tictac.domain.service;

import space.tictac.domain.model.Board;
import space.tictac.domain.model.Game;

public interface RulesControl {
    boolean isOver(Game currentGame);

    boolean checkWin(int[][]arr,int player);

    boolean checkBoard(Game currentGame, Board newBoard, java.util.UUID playerId);

    int[] getNextMove(Game game);
}
