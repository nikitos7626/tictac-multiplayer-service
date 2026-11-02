package space.tictac.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import space.tictac.datasource.mapper.GameMapper;
import space.tictac.datasource.mapper.UserMapper;
import space.tictac.datasource.repository.GameRepository;
import space.tictac.datasource.repository.UserRepository;
import space.tictac.domain.service.*;
import space.tictac.web.mapper.BoardDtoMapper;
import space.tictac.web.mapper.GameDtoMapper;
import space.tictac.web.mapper.UserDtoMapper;
import space.tictac.web.security.AuthFilter;

@Configuration
public class GameConfig {

    @Bean
    public GameMapper mapper(){
        return new GameMapper();
    }

    @Bean
    public RulesControl move(){
        return new GameRules();
    }

    @Bean
    public GameDtoMapper gameDtoMapper(){
        return new GameDtoMapper();
    }

    @Bean
    public GameService gameService(GameRepository repository, GameMapper mapper, RulesControl move){
        return new GameService(mapper, repository, move);
    }

    @Bean
    public UserDtoMapper userDtoMapper() {
        return new UserDtoMapper();
    }


    @Bean
    public UserMapper usermapper(){
        return new UserMapper();
    }

    @Bean
    public UserControl userService(UserRepository repository,UserMapper mapper){
        return new UserService(repository, mapper);
    }

    @Bean
    public BoardDtoMapper boardDtoMapper(){
        return new BoardDtoMapper();
    }
    @Bean
    public AuthControl authService(UserControl userService){
        return new AuthRules(userService);
    }

    @Bean
    public AuthFilter authFilter(AuthControl authservice){
        return  new AuthFilter(authservice);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthFilter authFilter) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}
