package com.becomejavasenior.impl;

import com.becomejavasenior.*;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

/**
 * JavaBean class describes ContactImpl model
 *
 * @author  Andrey Radionov <andyomsk@gmail.com>
 * @version 0.3
 */

public class ContactImpl extends IdentityImpl implements Identity, Contact {

    private static final long serialVersionUID = 1L;

    private Company company;

    private User responsibleUser;

    private String  name;

    private String jobPosition;

    private String email;

    private String skype;

    private Date created;

    private Date updated;

    private Collection<File> files;

    private Collection<Comment> comments;

    private Collection<Tag> tags;

    private Collection<Phone> phones;

    private Collection<Deal> deals;

    public ContactImpl() {
    }


    public String getName() {
        return name;
    }

    public User getResponsibleUser() {
        return responsibleUser;
    }

    public Collection<Phone> getPhones() {
        return phones;
    }

    public String getJobPosition() {
        return jobPosition;
    }

    public Company getCompany() {
        return company;
    }

    public String getEmail() {
        return email;
    }

    public String getSkype() {
        return skype;
    }

    public Date getCreated() {
        return created;
    }

    public Date getUpdated() {
        return updated;
    }

    public Collection<File> getFiles() {
        return files;
    }

    public Collection<Comment> getComments() {
        return comments;
    }

    public Collection<Tag> getTags() {
        return tags;
    }

    public Collection<Deal> getDeals() {
        return deals;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setResponsibleUser(User responsibleUser) {
        this.responsibleUser = responsibleUser;
    }

    public void setPhones(Collection<Phone> phones) {
        this.phones = phones;
    }

    public void setJobPosition(String jobPosition) {
        this.jobPosition = jobPosition;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public void setFiles(Collection<File> files) {
        this.files = files;
    }

    public void setComments(Collection<Comment> comments) {
        this.comments = comments;
    }

    public void setTags(Collection<Tag> tags) {
        this.tags = tags;
    }

    public void setDeals(Collection<Deal> deals) {
        this.deals = deals;
    }

    @Override
    public String toString() {
        return "ContactImpl{" +
                "id=" + super.getId() +
                ", name='" + name + '\'' +
                ", jobPosition='" + jobPosition + '\'' +
                ", email='" + email + '\'' +
                ", skype='" + skype + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}
