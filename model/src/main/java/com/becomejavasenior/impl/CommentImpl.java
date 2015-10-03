package com.becomejavasenior.impl;

import com.becomejavasenior.Comment;
import com.becomejavasenior.Identity;

import java.util.Date;

/**
 * JavaBean class describes CommentImpl model
 *
 * @author  Andrey Radionov <andyomsk@gmail.com>
 * @version 0.2
 */

public class CommentImpl extends IdentityImpl implements Identity, Comment {

    private static final long serialVersionUID = 1L;

    private String name;

    private String comment;

    private Date created;

    private Date updated;

    public CommentImpl() {
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

    @Override
    public String toString() {
        return "CommentImpl{" +
                "id=" + super.getId() +
                ", name='" + name + '\'' +
                ", comment='" + comment + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}
