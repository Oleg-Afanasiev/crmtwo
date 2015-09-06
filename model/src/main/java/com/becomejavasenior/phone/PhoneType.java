package com.becomejavasenior.phone;

import com.becomejavasenior.Identity;

/**
 * JavaBean class describes PhoneType model
 *
 * @author  Andrey Radionov <andyomsk@gmail.com>
 * @version 0.2
 */

public class PhoneType extends Identity {

    private static final long serialVersionUID = 1L;

    private String name;

    public PhoneType() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PhoneType{" +
                "phone_type='" + this.getId() + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
