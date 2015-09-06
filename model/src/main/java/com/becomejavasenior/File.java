package com.becomejavasenior;


import java.util.Date;

/**
 * JavaBean class describes File model
 *
 * @author  Andrey Radionov <andyomsk@gmail.com>
 * @version 0.3
 */

public class File extends Identity {

    private static final long serialVersionUID = 1L;

    private String path;

    private String mimeType;

    private Date created;

    private Date updated;

    public File() {
    }

    public String getPath() {
        return path;
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

    public void setPath(String path) {
        this.path = path;
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
    public String toString() {
        return "File{" +
                "file_id='" + this.getId() + '\'' +
                ", path='" + path + '\'' +
                ", mimeType='" + mimeType + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}