package com.becomejavasenior;

/**
 * JavaBean class describes Role model
 *
 * @author  Andrey Radionov <andyomsk@gmail.com>
 * @version 0.4
 */

public interface Role extends Identity {

    public String getName();

    public boolean isDeleted();

    public void setName(String name);

    public void setIsDeleted(boolean isDeleted);

}