package com.becomejavasenior.task;

import com.becomejavasenior.Identity;

/**
 * JavaBean class describes TaskType model
 *
 * @author  Andrey Radionov <andyomsk@gmail.com>
 * @version 0.2
 */

public class TaskType extends Identity {

    private static final long serialVersionUID = 1L;

    private String name;

    public TaskType() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TaskType{" +
                "task_type_id='" + this.getId() + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
