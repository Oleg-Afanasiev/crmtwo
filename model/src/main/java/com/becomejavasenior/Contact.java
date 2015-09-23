package com.becomejavasenior;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

/**
 * Created by Dmytro Tsapko on 9/6/2015.
 */
public interface Contact extends Identity {

    public String getName();

    public User getResponsibleUser();

    public Collection<Phone> getPhones();

    public String getJobPosition();

    public Company getCompany();

    public String getEmail();

    public String getSkype();

    public Date getCreated();

    public Date getUpdated();

    public Collection<File> getFiles();

    public Collection<Comment> getComments();

    public Collection<Tag> getTags();

    public Collection<Deal> getDeals();

    public void setName(String name);

    public void setResponsibleUser(User responsibleUser);

    public void setPhones(Collection<Phone> phones);

    public void setJobPosition(String jobPosition);

    public void setCompany(Company company);

    public void setEmail(String email);

    public void setSkype(String skype);

    public void setCreated(Date created);

    public void setUpdated(Date updated);

    public void setFiles(Collection<File> files);

    public void setComments(Collection<Comment> comments);

    public void setTags(Collection<Tag> tags);

    public void setDeals(Collection<Deal> deals);

}
