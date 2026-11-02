package space.tictac.web.model;

import java.util.UUID;

public class GameDto {

    private  UUID uuid;
    private BoardDto board;
    private UUID player1;
    private UUID player2;
    private String state;
    private boolean isVsComputer;
    public GameDto(){
    }

    public GameDto(UUID uuid, BoardDto board, UUID player1, UUID player2,boolean isVsComputer, String state) {
        this.uuid = uuid;
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;
        this.isVsComputer =isVsComputer;
        this.state = state;
    }

    public UUID getUuid() {
        return uuid;
    }
    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public BoardDto getBoard() {
        return board;
    }

    public void setBoard(BoardDto board) {
        this.board = board;
    }

    public UUID getPlayer1() {
        return player1;
    }

    public void setPlayer1(UUID player1) {
        this.player1 = player1;
    }

    public UUID getPlayer2() {
        return player2;
    }

    public void setPlayer2(UUID player2) {
        this.player2 = player2;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isVsComputer() {
        return isVsComputer;
    }

    public void setVsComputer(boolean vsComputer) {
        isVsComputer = vsComputer;
    }
}
