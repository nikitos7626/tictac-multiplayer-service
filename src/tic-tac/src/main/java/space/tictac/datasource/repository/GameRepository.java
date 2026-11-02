package space.tictac.datasource.repository;

import org.springframework.data.repository.CrudRepository;
import space.tictac.datasource.model.GameEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GameRepository extends CrudRepository<GameEntity,UUID> {

    List<GameEntity>findByState(String state);

}
