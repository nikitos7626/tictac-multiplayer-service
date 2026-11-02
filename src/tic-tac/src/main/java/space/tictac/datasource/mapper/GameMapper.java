package space.tictac.datasource.mapper;

import space.tictac.datasource.model.BoardEntity;
import space.tictac.datasource.model.GameEntity;
import space.tictac.domain.model.Board;
import space.tictac.domain.model.Game;

public class GameMapper {
    BoardMapper boardMapper;

    public GameMapper(){
        this.boardMapper= new BoardMapper();
    }

    public GameMapper(BoardMapper mapper){
        this.boardMapper=mapper;
    }

    public GameEntity toEntity(Game game) {
        return new GameEntity(
                game.getUUid(),
                boardMapper.toEntity(game.getBoard()),
                game.getPlayer1(),
                game.getPlayer2(),
                game.isVsComputer(),
                game.getState()
        );
    }
    public Game toGame(GameEntity game){
        return new Game(game.getUUid(),
                boardMapper.toBoard(game.getBoard()),
                    game.getPlayer1(),
                    game.getPlayer2(),
                game.isVsComputer(),
                game.getState()
                );
    }

}
