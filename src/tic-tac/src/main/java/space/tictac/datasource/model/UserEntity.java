package space.tictac.datasource.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name="users")
public class UserEntity {
    @Id
    private UUID uuid;

    private String login;

    private String password;


    public UserEntity(UUID uuid,String login,String password){
       this.uuid = uuid;
       this.login = login;
       this.password=password;
    }
    public UserEntity(){

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

