package com.becomejavasenior.impl;


import com.becomejavasenior.*;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

/**
 * JavaBean class describes Company model
 *
 * @author  Andrey Radionov <andyomsk@gmail.com>
 * @version 0.3
 */

public class CompanyImpl extends IdentityImpl implements Company {

    private static final long serialVersionUID = 1L;

    private User responsibleUser;

    private String name;

    private String email;

    private String webAddress;

    private String adress;

    private Date created;

    private Date updated;

    private Collection<Phone> phones;

    private Collection<Deal> deals;

    private Collection<Tag> tags;

    private Collection<File> files;

    private Collection<Comment> comments;

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
    public String getWebAddress() {
        return webAddress;
    }

    @Override
    public String getAddress() {
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
    public Collection<Phone> getPhones() {
        return phones;
    }

    @Override
    public Collection<Deal> getDeals() {
        return deals;
    }

    @Override
    public Collection<Tag> getTags() {
        return tags;
    }

    @Override
    public Collection<File> getFiles() {
        return files;
    }

    @Override
    public Collection<Comment> getComments() {
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
    public void setWebAddress(String webAddress) {
        this.webAddress = webAddress;
    }

    @Override
    public void setAddress(String address) {
        this.adress = address;
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
    public void setPhones(Collection<Phone> phones) {
        this.phones = phones;
    }

    @Override
    public void setDeals(Collection<Deal> deals) {
        this.deals = deals;
    }

    @Override
    public void setTags(Collection<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public void setFiles(Collection<File> files) {
        this.files = files;
    }

    @Override
    public void setComments(Collection<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + super.getId() +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", webAddress='" + webAddress + '\'' +
                ", adress='" + adress + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}
