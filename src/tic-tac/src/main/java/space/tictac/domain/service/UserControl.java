package space.tictac.domain.service;


import space.tictac.domain.model.User;

import java.util.Optional;

public interface UserControl {

    void save(User user);

    Optional<User>findByLogin(String login);
    Optional<User> findById(java.util.UUID uuid);
}
