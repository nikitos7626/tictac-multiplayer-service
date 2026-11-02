package space.tictac.datasource.model;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "GameEntity")
public class GameEntity {
    @Id
    private UUID uuid;
    @Embedded
    private BoardEntity board;

    private UUID player1;
    private UUID player2;

    private boolean vsComputer;
    private String state;


    public GameEntity(){
    }

    public GameEntity(UUID uuid, BoardEntity board, UUID player1, UUID player2, boolean vsComputer, String state) {
        this.uuid = uuid;
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;
        this.vsComputer = vsComputer;
        this.state = state;
    }

    public BoardEntity getBoard(){
        return this.board;
    }

    public void setBoard(BoardEntity newBoard){
        this.board=newBoard;
    }

    public UUID getUUid(){
        return this.uuid;
    }

    public void setUuid(UUID newId){
        this.uuid=newId;
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

    public boolean isVsComputer() {
        return vsComputer;
    }

    public void setVsComputer(boolean vsComputer) {
        this.vsComputer = vsComputer;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}