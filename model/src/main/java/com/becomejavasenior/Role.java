package com.becomejavasenior;

/**
 * JavaBean class describes Role model
 *
 * @author  Andrey Radionov <andyomsk@gmail.com>
 * @version 0.2
 */

public class Role extends Identity {

    private static final long serialVersionUID = 1L;

    private String name;

    private boolean isDeleted;

    public Role() {
    }

    public String getName() {
        return name;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "Role{" +
                "role_id='" + this.getId() + '\'' +
                ", name='" + name + '\'' +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
