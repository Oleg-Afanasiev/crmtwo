package com.becomejavasenior.impl;


import com.becomejavasenior.*;

import java.util.Date;
import java.util.Set;

/**
 * JavaBean class describes Company model
 *
 * @author  Andrey Radionov <andyomsk@gmail.com>
 * @version 0.1
 */

public class CompanyImpl extends IdentityImpl implements Company {

    private static final long serialVersionUID = 1L;

    private User responsibleUser;

    private String name;

    private String email;

    private String webAdress;

    private String adress;

    private Date created;

    private Date updated;

    private boolean isDeleted;

    private Set<Phone> phones;

    private Set<Deal> deals;

    private Set<Tag> tags;

    private Set<File> files;

    private Set<Comment> comments;

    public CompanyImpl() {
    }

    @Override
    public User getResponsibleUser() {
        return responsibleUser;
    }
    @Override
    public String getName() {
        return name;
    }
    @Override
    public String getEmail() {
        return email;
    }
    @Override
    public String getWebAdress() {
        return webAdress;
    }
    @Override
    public String getAdress() {
        return adress;
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
    public Set<Phone> getPhones() {
        return phones;
    }
    @Override
    public Set<Deal> getDeals() {
        return deals;
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
    public void setResponsibleUser(User responsibleUser) {
        this.responsibleUser = responsibleUser;
    }
    @Override
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public void setWebAdress(String webAdress) {
        this.webAdress = webAdress;
    }
    @Override
    public void setAdress(String adress) {
        this.adress = adress;
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
    public void setPhones(Set<Phone> phones) {
        this.phones = phones;
    }
    @Override
    public void setDeals(Set<Deal> deals) {
        this.deals = deals;
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
    public String toString() {
        return "Company{" +
                "id=" + super.getId() +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", webAdress='" + webAdress + '\'' +
                ", adress='" + adress + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
