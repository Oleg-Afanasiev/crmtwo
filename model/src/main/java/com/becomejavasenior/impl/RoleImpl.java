package com.becomejavasenior.impl;

import com.becomejavasenior.Role;

/**
 * Created by Dmytro Tsapko on 9/13/2015.
 */
public class RoleImpl extends IdentityImpl implements Role {

    private static final long serialVersionUID = 1L;

    private String name;

    public RoleImpl() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Role{" +
                "role_id='" + super.getId() + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

}
