package com.becomejavasenior.impl;


import com.becomejavasenior.DealStatus;
import com.becomejavasenior.Identity;

/**
 * JavaBean class describes DealStatusImpl model
 *
 * @author  Andrey Radionov <andyomsk@gmail.com>
 * @version 0.1
 */

public class DealStatusImpl extends IdentityImpl  implements Identity, DealStatus {

    private static final long serialVersionUID = 1L;

    private String name;

    public DealStatusImpl() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
