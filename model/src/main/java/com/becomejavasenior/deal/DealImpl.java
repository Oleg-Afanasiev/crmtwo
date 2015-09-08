package com.becomejavasenior.deal;

import com.becomejavasenior.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

/**
 * JavaBean class describes DealImpl model
 *
 * @author  Andrey Radionov <andyomsk@gmail.com>
 * @version 0.1
 */

public class DealImpl extends IdentityImpl  implements Identity, Deal  {

    private static final long serialVersionUID = 1L;

    private UserImpl responsibleUserId;

    private DealStatusImpl dealStatus;

    private String name;

    private BigDecimal bugget;

    private Date created;

    private Date updated;

    private boolean isDeleted;

    private Set<TagImpl> tags;

    private Set<FileImpl> files;

    private Set<CommentImpl> comments;

    private Set<ContactImpl> contacts;

    public DealImpl() {
    }


    public UserImpl getResponsibleUserId() {
        return responsibleUserId;
    }

    public DealStatusImpl getDealStatus() {
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

    public Set<TagImpl> getTags() {
        return tags;
    }

    public Set<FileImpl> getFiles() {
        return files;
    }

    public Set<CommentImpl> getComments() {
        return comments;
    }

    public Set<ContactImpl> getContacts() {
        return contacts;
    }

    public void setResponsibleUserId(UserImpl responsibleUserId) {
        this.responsibleUserId = responsibleUserId;
    }

    public void setDealStatus(DealStatusImpl dealStatus) {
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

    public void setTags(Set<TagImpl> tags) {
        this.tags = tags;
    }

    public void setFiles(Set<FileImpl> files) {
        this.files = files;
    }

    public void setComments(Set<CommentImpl> comments) {
        this.comments = comments;
    }

    public void setContacts(Set<ContactImpl> contacts) {
        this.contacts = contacts;
    }
}
