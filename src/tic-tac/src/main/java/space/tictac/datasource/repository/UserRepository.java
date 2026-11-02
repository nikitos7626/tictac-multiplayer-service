package space.tictac.datasource.repository;

import org.springframework.data.repository.CrudRepository;
import space.tictac.datasource.model.UserEntity;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends CrudRepository<UserEntity,UUID> {

    Optional<UserEntity>findByLogin(String login);


}
