package com.becomejavasenior;

/**
 * Created by Dmytro Tsapko on 9/6/2015.
 */
public interface Phone extends Identity {

    public int getPhoneType();

    public String getNumber();

    public void setPhoneType(int phoneType);

    public void setNumber(String number);


}
