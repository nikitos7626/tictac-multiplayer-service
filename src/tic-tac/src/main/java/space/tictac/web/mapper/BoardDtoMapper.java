package space.tictac.web.mapper;

import space.tictac.domain.model.Board;
import space.tictac.web.model.BoardDto;

public class BoardDtoMapper {

    public Board toBoard(BoardDto dto){
        return  new Board(dto.getArr());
    }

    public BoardDto toDto(Board board){
        return new BoardDto(board.getArr());
    }

}
