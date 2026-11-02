package space.tictac.web.mapper;

import space.tictac.domain.model.User;
import space.tictac.web.model.UserDto;

public class UserDtoMapper {

    public UserDto toDto(User user){
        return new UserDto(user.getUuid(),user.getLogin(),user.getPassword());
    }

    public User toUser(UserDto userDto){
        return new User(userDto.getUuid(),userDto.getLogin(), userDto.getPassword());
    }

}
