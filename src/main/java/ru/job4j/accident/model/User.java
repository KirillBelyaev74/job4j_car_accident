package ru.job4j.accident.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "password")
    private String password;

    @Column(name = "username")
    private String username;

    @Column(name = "enabled")
    private boolean enabled;

    @ManyToOne
    @JoinColumn(name = "authority_id")
    private Authority authority;

    public User() {
    }

    public User(String password, String username) {
        this.password = password;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id == user.id && enabled == user.enabled
                && Objects.equals(password, user.password)
                && Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, password, username, enabled);
    }

    @Override
    public String toString() {
        return "User { "
                + "id = " + id
                + ", password = '" + password + '\''
                + ", username = '" + username + '\''
                + ", enabled = " + enabled
                + '}';
    }
}