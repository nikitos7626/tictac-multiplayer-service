package space.tictac.domain.service;

import space.tictac.domain.model.User;
import space.tictac.web.model.SignUpRequest;

import java.util.Optional;
import java.util.UUID;

public interface AuthControl {

    boolean register(SignUpRequest request);

    UUID auth(String base64Auth);
}
