package space.tictac.domain.model;

import java.util.UUID;

public class Game {

    public static final String STATE_WAITING = "WAITING_FOR_PLAYERS";
    public static final String STATE_DRAW = "DRAW";
    public static final String PREFIX_TURN = "TURN_";
    public static final String PREFIX_WIN = "WIN_";
    private final char player1Mark = 'X';
    private final char player2Mark = 'O';

    private UUID uuid;
    private Board board;


    private UUID player1;
    private UUID player2;


    private boolean vsComputer;
    private String state;

    public Game(){
    }


    public Game(UUID uuid, Board board, UUID player1, UUID player2, boolean vsComputer, String state) {
        this.uuid = uuid;
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;
        this.vsComputer = vsComputer;
        this.state = state;
    }

    public Game(UUID player1,Boolean vsComputer){
        this.uuid=UUID.randomUUID();
        this.board= new Board();
        this.player1=player1;
        this.vsComputer=vsComputer;
        this.player2=null;
        if(vsComputer){
            setTurn(player1);
        }
        else{
            setWaiting();
        }
    }

    public void joinGame(UUID player2){
        this.player2=player2;
        setTurn(this.player1);
    }

    public char getMark(UUID playerUuid) {
        if (playerUuid.equals(this.player1)) {
            return this.player1Mark;
        }
        if (playerUuid.equals(this.player2)) {
            return this.player2Mark;
        }
        throw new IllegalArgumentException("Игрок не участвует в этой игре");
    }

    public void setWaiting() {
        this.state = STATE_WAITING;
    }
    public void setTurn(UUID playerUuid) {
        this.state = PREFIX_TURN + playerUuid;
    }
    public void setWin(UUID winnerUuid) {
        this.state = PREFIX_WIN + winnerUuid;
    }
    public void setDraw() {
        this.state = STATE_DRAW;
    }

    public boolean isWaiting() {
        return STATE_WAITING.equals(this.state);
    }
    public boolean isPlayerTurn(UUID playerUuid) {
        return (PREFIX_TURN + playerUuid).equals(this.state);
    }

    public Board getBoard(){
        return this.board;
    }

    public void setBoard(Board newBoard){
        this.board=newBoard;
    }


    public UUID getUUid(){
        return this.uuid;
    }

    public void setUuid(UUID newId){
        this.uuid=newId;
    }

    public UUID getPlayer1() {
        return this.player1;
    }
    public void setPlayer1(UUID player1) {
        this.player1 = player1;
    }
    public UUID getPlayer2() {
        return this.player2;
    }
    public void setPlayer2(UUID player2) {
        this.player2 = player2;
    }
    public boolean isVsComputer() {
        return this.vsComputer;
    }
    public void setVsComputer(boolean vsComputer) {
        this.vsComputer = vsComputer;
    }
    public String getState() {
        return this.state;
    }
    public void setState(String state) {
        this.state = state;
    }


    public char getPlayer1Mark() {
        return player1Mark;
    }

    public char getPlayer2Mark() {
        return player2Mark;
    }
}
