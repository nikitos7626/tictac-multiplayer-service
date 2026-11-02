package space.tictac.datasource.mapper;

import space.tictac.datasource.model.BoardEntity;
import space.tictac.domain.model.Board;

public class BoardMapper {
    public Board toBoard(BoardEntity entity){
        return new Board(entity.getArr());
    }

    public BoardEntity toEntity(Board board){
        return new BoardEntity(board.getArr());
    }
}
