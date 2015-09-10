package com.becomejavasenior;

import com.becomejavasenior.jdbc.deal.Deal;

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

    private User responsibleUser;

    private String  name;

    private String jobPosition;

    private String email;

    private String skype;

    private Date created;

    private Date updated;

    private boolean isDeleted;

    private Set<File> files;

    private Set<Comment> comments;

    private Set<Tag> tags;

    private Set<Phone> phones;

    private Set<Deal> deals;

    public ContactImpl() {
    }


    public String getName() {
        return name;
    }

    public User getResponsibleUser() {
        return responsibleUser;
    }

    public Set<Phone> getPhones() {
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

    public Set<File> getFiles() {
        return files;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public Set<Deal> getDeals() {
        return deals;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setResponsibleUser(User responsibleUser) {
        this.responsibleUser = responsibleUser;
    }

    public void setPhones(Set<Phone> phones) {
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

    public void setFiles(Set<File> files) {
        this.files = files;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public void setDeals(Set<Deal> deals) {
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
                ", isDeleted=" + isDeleted +
                '}';
    }
}
