package com.becomejavasenior;

/**
 * JavaBean class describes Tag model
 *
 * @author  Andrey Radionov <andyomsk@gmail.com>
 * @version 0.3
 */

public class Tag extends Identity {

    private static final long serialVersionUID = 1L;

    private String name;

    public Tag() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "tag_id='" + this.getId() + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
