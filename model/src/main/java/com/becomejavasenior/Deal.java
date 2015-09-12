package com.becomejavasenior;

import java.math.BigDecimal;
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

    public boolean isDeleted();

    public Set<Tag> getTags();

    public Set<File> getFiles();

    public Set<Comment> getComments();

    public Set<Contact> getContacts();

    public Set<Company> getCompanies();

    public void setResponsibleUser(User responsibleUser);

    public void setDealStatus(DealStatus dealStatus);

    public void setName(String name);

    public void setBudget(BigDecimal budget);

    public void setCreated(Date created);

    public void setUpdated(Date updated);

    public void setIsDeleted(boolean isDeleted);

    public void setTags(Set<Tag> tags);

    public void setFiles(Set<File> files);

    public void setComments(Set<Comment> comments);

    public void setContacts(Set<Contact> contacts);

    public void setCompanies(Set<Company> companies);

}
