package ru.job4j.accident.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "authority")
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "authority")
    private String authority;

    public Authority() {
    }

    public Authority(String authority) {
        this.authority = authority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
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
        Authority authority1 = (Authority) o;
        return id == authority1.id && Objects.equals(authority, authority1.authority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, authority);
    }

    @Override
    public String toString() {
        return "Authority { "
                + "id = " + id
                + ", authority = '" + authority + '\''
                + '}';
    }
}
