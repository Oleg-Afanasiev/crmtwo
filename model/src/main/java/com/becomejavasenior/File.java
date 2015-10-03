package com.becomejavasenior;

import java.util.Date;

/**
 * Created by Dmytro Tsapko on 9/6/2015.
 */
public interface File extends Identity {

    public String getName();

    public String getMimeType();

    public Date getCreated();

    public Date getUpdated();

    public byte[] getContent();

    public void setName(String path);

    public void setMimeType(String mimeType);

    public void setCreated(Date created);

    public void setUpdated(Date updated);

    public void setContent(byte[] content);

}
