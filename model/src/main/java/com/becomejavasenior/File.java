package com.becomejavasenior;

import java.util.Date;

/**
 * Created by Dmytro Tsapko on 9/6/2015.
 */
public interface File extends Identity {

    public String getPath();

    public String getMimeType();

    public Date getCreated();

    public Date getUpdated();

    public void setPath(String path);

    public void setMimeType(String mimeType);

    public void setCreated(Date created);

    public void setUpdated(Date updated);

}
