package space.tictac.web.mapper;

import space.tictac.domain.model.Game;
import space.tictac.web.model.GameDto;

public class GameDtoMapper {
    BoardDtoMapper mapper;

    public GameDtoMapper(){
        this.mapper= new BoardDtoMapper();
    }

    public GameDtoMapper(BoardDtoMapper mapper){
        this.mapper = mapper;
    }

    public Game toGame(GameDto dto) {
        return new Game(
                dto.getUuid(),
                mapper.toBoard(dto.getBoard()),
                dto.getPlayer1(),
                dto.getPlayer2(),
                dto.isVsComputer(),
                dto.getState()
        );
    }

    public GameDto toDto(Game game) {
        return new GameDto(
                game.getUUid(),
                mapper.toDto(game.getBoard()),
                game.getPlayer1(),
                game.getPlayer2(),
                game.isVsComputer(),
                game.getState()
        );
    }
}
