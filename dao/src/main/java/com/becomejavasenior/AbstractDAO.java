package com.becomejavasenior;

import java.util.Collection;

/**
 * Created by Dmytro Tsapko on 8/26/2015.
 */
public interface AbstractDAO<T> {

    boolean saveOrUpdate(T entity);

    T getById(long id);

    void delete(T entity);

    void deleteAll(Collection<? extends T> entities);




}
