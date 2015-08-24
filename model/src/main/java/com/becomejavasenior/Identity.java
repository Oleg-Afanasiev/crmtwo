package com.becomejavasenior;

import java.io.Serializable;


/**
 * General class for all model's entity
 *
 * @author  Andrey Radionov <andyomsk@gmail.com>
 * @version 0.2
 */

public class Identity implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;

    public Identity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Identity identity = (Identity) o;

        return id == identity.id;

    }

    @Override
    public int hashCode() {
        return id;
    }
}
