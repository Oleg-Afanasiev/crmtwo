package com.becomejavasenior.impl;

import com.becomejavasenior.Role;

/**
 * Created by Dmytro Tsapko on 9/13/2015.
 */
public class RoleImpl extends IdentityImpl implements Role {

    private static final long serialVersionUID = 1L;

    private String name;

    private boolean isDeleted;

    public RoleImpl() {
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
                "role_id='" + super.getId() + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

}
