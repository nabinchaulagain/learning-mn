package rltw.todo.model;

import javax.persistence.*;

@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue
    private long id;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    public User(){
        super();
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
