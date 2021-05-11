package ru.job4j.accident.model;

import java.util.Objects;

public class Rule {

    private int id;

    private String name;

    public Rule(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Rule rule = (Rule) o;
        return id == rule.id && Objects.equals(name, rule.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Rule { "
                + "id = " + id
                + ", name = '" + name + '\''
                + '}';
    }
}