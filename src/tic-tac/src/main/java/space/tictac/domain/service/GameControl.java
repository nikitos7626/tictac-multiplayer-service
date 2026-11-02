package space.tictac.domain.service;

import space.tictac.datasource.mapper.BoardMapper;
import space.tictac.domain.model.Board;
import space.tictac.domain.model.Game;

import java.util.List;
import java.util.UUID;

public interface GameControl {
    Game createGame(UUID player1,boolean vsComputer);
    Game joinGame(UUID gameId,UUID player2);

    Game makeMove(UUID gameId, UUID playerId, Board newBoard);
    List<Game> getEmptyLobby();
}
