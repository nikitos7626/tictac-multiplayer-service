package space.tictac.datasource.mapper;

import space.tictac.datasource.model.UserEntity;
import space.tictac.domain.model.User;

public class UserMapper {
    public UserEntity toEntity(User user){
        if(user==null){
            return null;
        }
        return new UserEntity(user.getUuid(),user.getLogin(),user.getPassword());
    }

    public  User toUser(UserEntity entity){
        if(entity==null){
            return null;
        }
        return new User(entity.getUuid(),entity.getLogin(),entity.getPassword());
    }
}
