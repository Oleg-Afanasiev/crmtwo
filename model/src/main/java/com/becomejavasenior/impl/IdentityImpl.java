package com.becomejavasenior.impl;

import com.becomejavasenior.Identity;

/**
 * Created by Dmytro Tsapko on 9/6/2015.
 */
public class IdentityImpl implements Identity {

    private static final long serialVersionUID = 1L;

    private Long id;

    public IdentityImpl() {
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Identity identity = (Identity) o;

        return id == identity.getId();

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
