package space.tictac.web.model;

public class SignUpRequest {

    private String login;

    private String password;


    public String getLogin(){
        return this.login;
    }

    public String getPassword(){
        return this.password;
    }

    public void setLogin(String login){
        this.login=login;
    }

    public void setPassword(String password){
        this.password=password;
    }

}
