package com.becomejavasenior.deal;

import com.becomejavasenior.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

/**
 * JavaBean class describes Deal model
 *
 * @author  Andrey Radionov <andyomsk@gmail.com>
 * @version 0.1
 */

public class Deal extends Identity {

    private static final long serialVersionUID = 1L;

    private User responsibleUserId;

    private DealStatus dealStatus;

    private String name;

    private BigDecimal bugget;

    private Date created;

    private Date updated;

    private boolean isDeleted;

    private Set<Tag> tags;

    private Set<File> files;

    private Set<Comment> comments;

    private Set<Contact> contacts;

    public Deal() {
    }


    public User getResponsibleUserId() {
        return responsibleUserId;
    }

    public DealStatus getDealStatus() {
        return dealStatus;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getBugget() {
        return bugget;
    }

    public Date getCreated() {
        return created;
    }

    public Date getUpdated() {
        return updated;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public Set<File> getFiles() {
        return files;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public Set<Contact> getContacts() {
        return contacts;
    }

    public void setResponsibleUserId(User responsibleUserId) {
        this.responsibleUserId = responsibleUserId;
    }

    public void setDealStatus(DealStatus dealStatus) {
        this.dealStatus = dealStatus;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBugget(BigDecimal bugget) {
        this.bugget = bugget;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public void setFiles(Set<File> files) {
        this.files = files;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
    }
}
