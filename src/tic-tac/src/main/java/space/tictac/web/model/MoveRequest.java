package space.tictac.web.model;


public class MoveRequest {
    private BoardDto board;
    public MoveRequest() {}
    public BoardDto getBoard() { return board; }
    public void setBoard(BoardDto board) { this.board = board; }
}