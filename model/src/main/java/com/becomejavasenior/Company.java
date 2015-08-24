package com.becomejavasenior;

import com.becomejavasenior.deal.Deal;

import java.util.Date;
import java.util.Set;

/**
 * JavaBean class describes Company model
 *
 * @author  Andrey Radionov <andyomsk@gmail.com>
 * @version 0.1
 */

public class Company extends Identity {

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

    public Company() {
    }

    public User getResponsibleUser() {
        return responsibleUser;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getWebAdress() {
        return webAdress;
    }

    public String getAdress() {
        return adress;
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

    public Set<Phone> getPhones() {
        return phones;
    }

    public Set<Deal> getDeals() {
        return deals;
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

    public void setResponsibleUser(User responsibleUser) {
        this.responsibleUser = responsibleUser;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setWebAdress(String webAdress) {
        this.webAdress = webAdress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
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

    public void setPhones(Set<Phone> phones) {
        this.phones = phones;
    }

    public void setDeals(Set<Deal> deals) {
        this.deals = deals;
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
}
