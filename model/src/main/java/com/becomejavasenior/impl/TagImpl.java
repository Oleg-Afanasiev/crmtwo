package com.becomejavasenior.impl;

import com.becomejavasenior.Identity;
import com.becomejavasenior.Tag;

/**
 * JavaBean class describes TagImpl model
 *
 * @author  Andrey Radionov <andyomsk@gmail.com>
 * @version 0.2
 */

public class TagImpl extends IdentityImpl implements Identity, Tag {

    private static final long serialVersionUID = 1L;

    private String name;

    public TagImpl() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TagImpl{" +
                "id=" + super.getId() +
                ", name='" + name + '\'' +
                '}';
    }
}
