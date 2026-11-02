package space.tictac.domain.service;

import org.antlr.v4.runtime.atn.LookaheadEventInfo;
import space.tictac.datasource.mapper.GameMapper;
import space.tictac.datasource.model.GameEntity;
import space.tictac.datasource.repository.GameRepository;
import space.tictac.domain.model.Board;
import space.tictac.domain.model.Game;

import java.util.List;
import java.util.UUID;

public class GameService implements GameControl,RulesControl {
    private final GameRepository repository;
    private final GameMapper mapper;
    private final RulesControl move;

    public GameService(GameMapper mapper, GameRepository repository, RulesControl move){
        this.mapper=mapper;
        this.repository=repository;
        this.move=move;
    }

    public void save(Game game){
     GameEntity gameEntity = mapper.toEntity(game);
     repository.save(gameEntity);
    }

    public Game getGame(UUID uuid) {
       return repository.findById(uuid).map(mapper::toGame).orElse(null);
    }

    @Override
    public boolean isOver(Game currentGame) {
        return move.isOver(currentGame);
    }

    @Override
    public boolean checkWin(int[][] arr, int player) {
        return move.checkWin(arr, player);
    }

    @Override
    public boolean checkBoard(Game currentGame, Board newBoard, java.util.UUID playerId) {
        return move.checkBoard(currentGame, newBoard, playerId);
    }

    @Override
    public int[] getNextMove(Game game) {
       return move.getNextMove(game);
    }

    @Override
    public Game createGame(UUID player1, boolean vsComputer) {
        Game game = new Game(player1,vsComputer);
        save(game);
        return game;
    }

    @Override
    public Game joinGame(UUID gameId, UUID player2) {
        Game game = getGame(gameId);

        if(game==null||!game.isWaiting()){
            throw new IllegalArgumentException("Игра не найдена или к ней нельзя присоединиться");
        }
        if(game.getPlayer1().equals(player2)){
            throw new IllegalArgumentException("Нельзя присоединиться к самому себе");
        }
        game.joinGame(player2);
        save(game);
        return game;
    }

    @Override
    public Game makeMove(UUID gameId, UUID playerId, Board newBoard) {
        Game game = getGame(gameId);
        if(game==null||game.isWaiting()){
            throw new IllegalArgumentException("Игра не найдена или находиться в ожидании");
        }
        char mark =game.getMark(playerId);
        if(!game.isPlayerTurn(playerId)){
            throw new IllegalArgumentException("Сейчас не твой ход");
        }

        if(!move.checkBoard(game, newBoard, playerId)){
            throw  new IllegalArgumentException("Недопустимый ход");
        }
        game.setBoard(newBoard);

        if (isOver(game)) {
            if (checkWin(game.getBoard().getArr(), 1)) {
                game.setWin(game.getPlayer1());
            } else if (checkWin(game.getBoard().getArr(), 2)) {
                game.setWin(game.getPlayer2());
            } else {
                game.setDraw();
            }
        } else if (game.isVsComputer()) {
            int[] botMove = move.getNextMove(game);
            if (botMove[0] != -100) {
                game.getBoard().getArr()[botMove[0]][botMove[1]] = 2;
                if (isOver(game)) {
                    if (checkWin(game.getBoard().getArr(), 2)) {
                        game.setWin(game.getPlayer2());
                    } else {
                        game.setDraw();
                    }
                } else {
                    game.setTurn(game.getPlayer1());
                }
            } else {
                game.setTurn(game.getPlayer1());
            }
        } else {
            UUID nextPlayer = playerId.equals(game.getPlayer1()) ? game.getPlayer2() : game.getPlayer1();
            game.setTurn(nextPlayer);
        }

        save(game);
        return game;
    }


    @Override
    public List<Game> getEmptyLobby() {
        return repository.findByState(Game.STATE_WAITING).stream().map(mapper::toGame).toList();
    }
}
