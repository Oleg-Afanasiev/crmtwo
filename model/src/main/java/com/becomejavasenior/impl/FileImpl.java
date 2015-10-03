package com.becomejavasenior.impl;


import com.becomejavasenior.File;
import com.becomejavasenior.Identity;

import java.util.Date;

/**
 * JavaBean class describes FileImpl model
 *
 * @author  Andrey Radionov <andyomsk@gmail.com>
 * @version 0.2
 */

public class FileImpl extends IdentityImpl implements Identity, File {

    private static final long serialVersionUID = 1L;

    private String name;

    private String mimeType;

    private Date created;

    private Date updated;

    private byte[] content;

    public FileImpl() {
    }

    public String getName() {
        return name;
    }

    public String getMimeType() {
        return mimeType;
    }

    public Date getCreated() {
        return created;
    }

    public Date getUpdated() {
        return updated;
    }

    @Override
    public byte[] getContent() {
        return content;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    @Override
    public void setContent(byte[] content) {
        this.content = content;
    }


    @Override
    public String toString() {
        return "FileImpl{" +
                "id=" + super.getId() +
                ", name='" + name + '\'' +
                ", mimeType='" + mimeType + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}
