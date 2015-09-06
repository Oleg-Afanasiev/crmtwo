package com.becomejavasenior.deal;

import com.becomejavasenior.Identity;

/**
 * JavaBean class describes DealStatus model
 *
 * @author  Andrey Radionov <andyomsk@gmail.com>
 * @version 0.2
 */

public class DealStatus extends Identity {

    private static final long serialVersionUID = 1L;

    private String name;

    public DealStatus() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DealStatus{" +
                "status_id='" + this.getId() + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
