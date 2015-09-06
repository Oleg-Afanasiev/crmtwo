package com.becomejavasenior.task;

import com.becomejavasenior.Identity;

/**
 * JavaBean class describes TaskPeriod model
 *
 * @author  Andrey Radionov <andyomsk@gmail.com>
 * @version 0.2
 */

public class TaskPeriod extends Identity {

    private static final long serialVersionUID = 1L;

    private String name;

    public TaskPeriod() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TaskPeriod{" +
                "period_id='" + this.getId() + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
