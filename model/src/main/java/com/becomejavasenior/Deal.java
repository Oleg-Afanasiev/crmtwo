package com.becomejavasenior;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

/**
 * Created by Dmytro Tsapko on 9/7/2015.
 */
public interface Deal extends Identity {

    public User getResponsibleUser();

    public DealStatus getDealStatus();

    public String getName();

    public BigDecimal getBudget();

    public Date getCreated();

    public Date getUpdated();

    public Collection<Tag> getTags();

    public Collection<File> getFiles();

    public Collection<Comment> getComments();

    public Collection<Contact> getContacts();

    public Collection<Company> getCompanies();

    public void setResponsibleUser(User responsibleUser);

    public void setDealStatus(DealStatus dealStatus);

    public void setName(String name);

    public void setBudget(BigDecimal budget);

    public void setCreated(Date created);

    public void setUpdated(Date updated);

    public void setTags(Collection<Tag> tags);

    public void setFiles(Collection<File> files);

    public void setComments(Collection<Comment> comments);

    public void setContacts(Collection<Contact> contacts);

    public void setCompanies(Collection<Company> companies);

}
