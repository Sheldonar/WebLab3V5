package Entity;

import javax.persistence.*;

@Entity
public class UserHash {

    @Id
    @GeneratedValue
    private long id;
    private String login;
    private String hash;

    public UserHash(){}
    public UserHash(String newLogin, String newHash){
        this.hash = newHash;
        this.login = newLogin;
    }

    public Long getId(){
        return this.id;
    }

    public String getLogin() {
        return this.login;
    }

    public String getHash() {
        return this.hash;
    }

    public void setLogin(String newLogin) {
        this.login = newLogin;
    }

    public void setHash(String newHash) {
        this.hash = newHash;
    }
}
