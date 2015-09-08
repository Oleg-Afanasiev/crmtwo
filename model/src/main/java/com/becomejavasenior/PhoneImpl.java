package com.becomejavasenior;

/**
 * JavaBean class describes PhoneImpl model
 *
 * @author  Andrey Radionov <andyomsk@gmail.com>
 * @version 0.2
 */

public class PhoneImpl extends IdentityImpl implements Identity, Phone  {

    private static final long serialVersionUID = 1L;

    private int phoneType;

    private String number;

    public PhoneImpl() {
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

    @Override
    public String toString() {
        return "PhoneImpl{" +
                "id=" + super.getId() +
                ", phoneType=" + phoneType +
                ", number='" + number + '\'' +
                '}';
    }
}
