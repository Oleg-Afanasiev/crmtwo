package com.becomejavasenior;

import java.util.Date;

/**
 * Created by Dmytro Tsapko on 9/6/2015.
 */
public interface Comment extends Identity {

    public String getName();

    public String getComment();

    public Date getCreated();

    public Date getUpdated();

    public void setName(String name);

    public void setComment(String comment);

    public void setCreated(Date created);

    public void setUpdated(Date updated);

}
