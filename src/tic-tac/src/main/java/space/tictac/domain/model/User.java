package space.tictac.domain.model;

import java.util.UUID;

public class User {
    private UUID uuid;

    private String login;

    private String password;

    public User(UUID uuid,String login,String password){
        this.uuid =uuid;
        this.login = login;
        this.password=password;
    }
    public User(){
    }


    public UUID getUuid() {
        return this.uuid;
    }
    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getLogin(){
        return this.login;
    }

    public  void setLogin(String login){
        this.login =login;
    }

    public String getPassword(){
        return this.password;
    }

    public  void setPassword(String password){
        this.password=password;
    }
}
