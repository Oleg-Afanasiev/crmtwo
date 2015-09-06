package com.becomejavasenior.deal;

import com.becomejavasenior.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

/**
 * JavaBean class describes Deal model
 *
 * @author  Andrey Radionov <andyomsk@gmail.com>
 * @version 0.2
 */

public class Deal extends Identity {

    private static final long serialVersionUID = 1L;

    private User responsibleUser;

    private DealStatus dealStatus;

    private String name;

    private BigDecimal budget;

    private Date created;

    private Date updated;

    private boolean isDeleted;

    private Set<Tag> tags;

    private Set<File> files;

    private Set<Comment> comments;

    private Set<Contact> contacts;

    public Deal() {
    }


    public User getResponsibleUser() {
        return responsibleUser;
    }

    public DealStatus getDealStatus() {
        return dealStatus;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getBudget() {
        return budget;
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

    public void setResponsibleUser(User responsibleUser) {
        this.responsibleUser = responsibleUser;
    }

    public void setDealStatus(DealStatus dealStatus) {
        this.dealStatus = dealStatus;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
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

    @Override
    public String toString() {
        return "Deal{" +
                "deal_id='" + this.getId() + '\'' +
                ", responsibleUserId=" + responsibleUser +
                ", dealStatus=" + dealStatus +
                ", name='" + name + '\'' +
                ", budget=" + budget +
                ", created=" + created +
                ", updated=" + updated +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
