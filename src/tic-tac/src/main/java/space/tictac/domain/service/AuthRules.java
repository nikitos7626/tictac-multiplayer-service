package space.tictac.domain.service;

import space.tictac.domain.model.User;
import space.tictac.web.model.SignUpRequest;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;
import java.util.UUID;

public class AuthRules implements AuthControl{

    private final UserControl userService;

    public AuthRules(UserControl userService){
        this.userService = userService;
    }

    private String[] decodeCredentials(String authHeader) {
        String cleanBase64 = authHeader.replace("Basic ", "").trim();
        byte[] bytes = Base64.getDecoder().decode(cleanBase64);
        String text = new String(bytes, StandardCharsets.UTF_8);
        String[] parts = text.split(":",2);
        return parts;
    }


    @Override
    public boolean register(SignUpRequest request) {
        if(userService.findByLogin(request.getLogin()).isPresent()){
            return false;
        }
        User user = new User(UUID.randomUUID(),request.getLogin(),request.getPassword());
        userService.save(user);
        return true;
    }

    @Override
    public UUID auth(String base64Auth) {

        String[]parts = decodeCredentials(base64Auth);
        if (parts.length != 2) {
            return null;
        }

        String login = parts[0];
        String password = parts[1];
        Optional<User> curUser = userService.findByLogin(login);

        if(curUser.isPresent()){
            User user = curUser.get();
            if(user.getPassword().equals(password)&&user.getLogin().equals(login)){
                return user.getUuid();
            }
        }
        return null;
    }
}
