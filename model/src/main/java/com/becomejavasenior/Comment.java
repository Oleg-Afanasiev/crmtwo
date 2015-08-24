package com.becomejavasenior;

import java.util.Date;

/**
 * JavaBean class describes Comment model
 *
 * @author  Andrey Radionov <andyomsk@gmail.com>
 * @version 0.2
 */

public class Comment extends Identity {

    private static final long serialVersionUID = 1L;

    private String name;

    private String comment;

    private Date created;

    private Date updated;

    public Comment() {
    }

    public String getName() {
        return name;
    }

    public String getComment() {
        return comment;
    }

    public Date getCreated() {
        return created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}
