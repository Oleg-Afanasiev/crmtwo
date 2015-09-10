package com.becomejavasenior.jdbc.task;


import com.becomejavasenior.IdentityImpl;
import com.becomejavasenior.Identity;

/**
 * JavaBean class describes TaskPeriodImpl model
 *
 * @author  Andrey Radionov <andyomsk@gmail.com>
 * @version 0.1
 */

public class TaskPeriodImpl extends IdentityImpl  implements Identity, TaskPeriod {

    private static final long serialVersionUID = 1L;

    private String name;

    public TaskPeriodImpl() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
