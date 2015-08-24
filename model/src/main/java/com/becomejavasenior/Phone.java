package com.becomejavasenior;

/**
 * JavaBean class describes Phone model
 *
 * @author  Andrey Radionov <andyomsk@gmail.com>
 * @version 0.2
 */

public class Phone extends Identity {

    private static final long serialVersionUID = 1L;

    private int phoneType;

    private String number;

    public Phone() {
    }

    public int getPhoneType() {
        return phoneType;
    }

    public String getNumber() {
        return number;
    }

    public void setPhoneType(int phoneType) {
        this.phoneType = phoneType;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
