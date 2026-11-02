package space.tictac.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import space.tictac.domain.model.Board;
import space.tictac.domain.model.Game;
import space.tictac.domain.service.GameService;
import space.tictac.web.mapper.BoardDtoMapper;
import space.tictac.web.mapper.GameDtoMapper;
import space.tictac.web.model.CreateGameRequest;
import space.tictac.web.model.GameDto;
import space.tictac.web.model.MoveRequest;

import java.util.List;
import java.util.UUID;

@RestController
public class GameController {
    private final GameService gameService;
    private final GameDtoMapper mapper;
    private final BoardDtoMapper boardMapper;

    public GameController(GameService gameService, GameDtoMapper mapper, BoardDtoMapper boardMapper){
        this.gameService=gameService;
        this.mapper=mapper;
        this.boardMapper = boardMapper;
    }

    @PostMapping("game/{uuid}")
    public ResponseEntity<?> makeMove(@PathVariable UUID uuid, @RequestBody MoveRequest request) {
        UUID playerId = (UUID) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Game savedGame = gameService.getGame(uuid);
        if (savedGame == null) {
            return ResponseEntity.badRequest().body("Игра не найдена");
        }
        Board newBoard = boardMapper.toBoard(request.getBoard());
        try {
            Game updatedGame = gameService.makeMove(uuid, playerId, newBoard);
            return ResponseEntity.ok(mapper.toDto(updatedGame));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/game")
    public ResponseEntity<GameDto> createGame(@RequestBody CreateGameRequest request) {
        UUID player1 = (UUID) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Game game = gameService.createGame(player1, request.isVsComputer());
        return ResponseEntity.ok(mapper.toDto(game));
    }

    @GetMapping("/game")
    public ResponseEntity<List<GameDto>> getLobby() {
        return ResponseEntity.ok(gameService.getEmptyLobby().stream().map(mapper::toDto).toList());
    }


    @PostMapping("/game/{uuid}/join")
    public ResponseEntity<GameDto> joinGame(@PathVariable UUID uuid) {

        UUID player2 = (UUID) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Game game = gameService.joinGame(uuid, player2);
        return ResponseEntity.ok(mapper.toDto(game));
    }

    @GetMapping("/game/{uuid}")
    public ResponseEntity<GameDto> getGame(@PathVariable UUID uuid) {
        Game game = gameService.getGame(uuid);
        if (game == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(mapper.toDto(game));
    }

}
