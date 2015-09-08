package com.becomejavasenior;

import com.becomejavasenior.deal.DealImpl;

import java.util.Date;
import java.util.Set;

/**
 * JavaBean class describes ContactImpl model
 *
 * @author  Andrey Radionov <andyomsk@gmail.com>
 * @version 0.2
 */

public class ContactImpl extends IdentityImpl implements Identity, Contact  {

    private static final long serialVersionUID = 1L;

    private Company company;

    private UserImpl responsibleUser;

    private String  name;

    private String jobPosition;

    private String email;

    private String skype;

    private Date created;

    private Date updated;

    private boolean isDeleted;

    private Set<FileImpl> files;

    private Set<CommentImpl> comments;

    private Set<TagImpl> tags;

    private Set<PhoneImpl> phones;

    private Set<DealImpl> deals;

    public ContactImpl() {
    }


    public String getName() {
        return name;
    }

    public UserImpl getResponsibleUser() {
        return responsibleUser;
    }

    public Set<PhoneImpl> getPhones() {
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

    public boolean isDeleted() {
        return isDeleted;
    }

    public Set<FileImpl> getFiles() {
        return files;
    }

    public Set<CommentImpl> getComments() {
        return comments;
    }

    public Set<TagImpl> getTags() {
        return tags;
    }

    public Set<DealImpl> getDeals() {
        return deals;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setResponsibleUser(UserImpl responsibleUser) {
        this.responsibleUser = responsibleUser;
    }

    public void setPhones(Set<PhoneImpl> phones) {
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

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public void setFiles(Set<FileImpl> files) {
        this.files = files;
    }

    public void setComments(Set<CommentImpl> comments) {
        this.comments = comments;
    }

    public void setTags(Set<TagImpl> tags) {
        this.tags = tags;
    }

    public void setDeals(Set<DealImpl> deals) {
        this.deals = deals;
    }
}
