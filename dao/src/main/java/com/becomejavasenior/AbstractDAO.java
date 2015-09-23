package com.becomejavasenior;

import java.util.Collection;

/**
 * Created by Dmytro Tsapko on 8/26/2015.
 */
public interface AbstractDAO<T> {

    void insertOrUpdate(T entity);

    T getById(long id);

    void delete(Long id);

    void deleteAll(Collection<? extends T> entities);

    /**
     *
     * @param from row number, min value 0
     * @param size count of rows to select shall be grater then 0
     * @return Collection of Entities
     */

    Collection<T> getRange(long from, long size);

}
