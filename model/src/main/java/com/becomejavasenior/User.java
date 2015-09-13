package com.becomejavasenior;

import java.util.Date;

/**
 * Created by Dmytro Tsapko on 9/6/2015.
 */
public interface User extends Identity {
    public String getUserName();

    public String getLastName();

    public String getFirstName();

    public String getEmail();

    public Date getCreated();

    public Date getUpdated();

    public void setUserName(String userName);

    public void setLastName(String lastName);

    public void setEmail(String email);

    public void setCreated(Date created);

    public void setUpdated(Date updated);

    public Role getRole();

    public void setRole(Role role);

    public void setFirstName(String firstName);
}
