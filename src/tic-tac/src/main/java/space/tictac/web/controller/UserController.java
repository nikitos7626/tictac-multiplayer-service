package space.tictac.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import space.tictac.domain.model.User;
import space.tictac.domain.service.UserControl;
import space.tictac.web.mapper.UserDtoMapper;
import space.tictac.web.model.UserDto;

import java.util.Optional;
import java.util.UUID;

@RestController
public class UserController {
    private final UserControl userService;
    private final UserDtoMapper mapper;

    public UserController(UserControl userService, UserDtoMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @GetMapping("/user/{uuid}")
    public ResponseEntity<UserDto> getUser(@PathVariable UUID uuid) {
        Optional<User> user = userService.findById(uuid);
        return user.map(u -> ResponseEntity.ok(mapper.toDto(u)))
                .orElse(ResponseEntity.notFound().build());
    }
}