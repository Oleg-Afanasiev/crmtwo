package com.becomejavasenior;

import java.util.Collection;

/**
 * Created by Dmytro Tsapko on 8/29/2015.
 */
public class GenericDAO<T extends Identity> implements AbstractDAO<T> {

    public boolean saveOrUpdate(T entity) {
        return false;
    }

    public T getById(long id) {
        return null;
    }

    public void delete(T entity) {

    }

    public void deleteAll(Collection<? extends T> entities) {

    }
}
