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

    private Set<Company> companies;

    public DealImpl() {
    }

    @Override
    public User getResponsibleUser() {
        return responsibleUser;
    }

    @Override
    public DealStatus getDealStatus() {
        return dealStatus;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public BigDecimal getBudget() {
        return budget;
    }

    @Override
    public Date getCreated() {
        return created;
    }

    @Override
    public Date getUpdated() {
        return updated;
    }

    @Override
    public boolean isDeleted() {
        return isDeleted;
    }

    @Override
    public Set<Tag> getTags() {
        return tags;
    }

    @Override
    public Set<File> getFiles() {
        return files;
    }

    @Override
    public Set<Comment> getComments() {
        return comments;
    }

    @Override
    public Set<Contact> getContacts() {
        return contacts;
    }

    @Override
    public Set<Company> getCompanies() {
        return companies;
    }

    @Override
    public void setResponsibleUser(User responsibleUser) {
        this.responsibleUser = responsibleUser;
    }

    @Override
    public void setDealStatus(DealStatus dealStatus) {
        this.dealStatus = dealStatus;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    @Override
    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    @Override
    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public void setFiles(Set<File> files) {
        this.files = files;
    }

    @Override
    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
    }

    @Override
    public void setCompanies(Set<Company> companies) {
        this.companies = companies;
    }

    @Override
    public String toString() {
        return "DealImpl{" +
                "id=" + super.getId() +
                ", dealStatus=" + dealStatus +
                ", name='" + name + '\'' +
                ", budget=" + budget +
                ", created=" + created +
                ", updated=" + updated +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
