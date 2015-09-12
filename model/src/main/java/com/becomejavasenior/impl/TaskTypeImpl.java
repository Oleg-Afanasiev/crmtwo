package com.becomejavasenior.impl;


import com.becomejavasenior.Identity;
import com.becomejavasenior.TaskType;

/**
 * JavaBean class describes TaskTypeImpl model
 *
 * @author  Andrey Radionov <andyomsk@gmail.com>
 * @version 0.1
 */

public class TaskTypeImpl extends IdentityImpl implements Identity, TaskType {

    private static final long serialVersionUID = 1L;

    private String name;

    public TaskTypeImpl() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
