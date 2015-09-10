package com.becomejavasenior;

import com.becomejavasenior.jdbc.deal.Deal;

import java.util.Date;
import java.util.Set;

/**
 * Created by Dmytro Tsapko on 9/6/2015.
 */
public interface Contact extends Identity {

    public String getName();

    public User getResponsibleUser();

    public Set<Phone> getPhones();

    public String getJobPosition();

    public Company getCompany();

    public String getEmail();

    public String getSkype();

    public Date getCreated();

    public Date getUpdated();

    public boolean isDeleted();

    public Set<File> getFiles();

    public Set<Comment> getComments();

    public Set<Tag> getTags();

    public Set<Deal> getDeals();

    public void setName(String name);

    public void setResponsibleUser(User responsibleUser);

    public void setPhones(Set<Phone> phones);

    public void setJobPosition(String jobPosition);

    public void setCompany(Company company);

    public void setEmail(String email);

    public void setSkype(String skype);

    public void setCreated(Date created);

    public void setUpdated(Date updated);

    public void setIsDeleted(boolean isDeleted);

    public void setFiles(Set<File> files);

    public void setComments(Set<Comment> comments);

    public void setTags(Set<Tag> tags);

    public void setDeals(Set<Deal> deals);

}
