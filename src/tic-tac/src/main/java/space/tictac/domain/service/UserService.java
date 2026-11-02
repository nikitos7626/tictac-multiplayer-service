package space.tictac.domain.service;

import space.tictac.datasource.mapper.UserMapper;
import space.tictac.datasource.model.UserEntity;
import space.tictac.datasource.repository.UserRepository;
import space.tictac.domain.model.User;

import java.util.Optional;
import java.util.UUID;

public class UserService implements UserControl{
    UserRepository repository;
    UserMapper mapper;

    public UserService(UserRepository repository, UserMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public void save(User user) {
        UserEntity entity = mapper.toEntity(user);
        repository.save(entity);
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return repository.findByLogin(login).map(mapper::toUser);
    }

    @Override
    public Optional<User> findById(UUID uuid) {
        return repository.findById(uuid).map(mapper::toUser);
    }
}
