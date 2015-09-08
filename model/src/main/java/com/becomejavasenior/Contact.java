package com.becomejavasenior;

import com.becomejavasenior.deal.DealImpl;

import java.util.Date;
import java.util.Set;

/**
 * Created by Dmytro Tsapko on 9/6/2015.
 */
public interface Contact extends Identity {

    public String getName();

    public UserImpl getResponsibleUser();

    public Set<PhoneImpl> getPhones();

    public String getJobPosition();

    public Company getCompany();

    public String getEmail();

    public String getSkype();

    public Date getCreated();

    public Date getUpdated();

    public boolean isDeleted();

    public Set<FileImpl> getFiles();

    public Set<CommentImpl> getComments();

    public Set<TagImpl> getTags();

    public Set<DealImpl> getDeals();

    public void setName(String name);

    public void setResponsibleUser(UserImpl responsibleUser);

    public void setPhones(Set<PhoneImpl> phones);

    public void setJobPosition(String jobPosition);

    public void setCompany(Company company);

    public void setEmail(String email);

    public void setSkype(String skype);

    public void setCreated(Date created);

    public void setUpdated(Date updated);

    public void setIsDeleted(boolean isDeleted);

    public void setFiles(Set<FileImpl> files);

    public void setComments(Set<CommentImpl> comments);

    public void setTags(Set<TagImpl> tags);

    public void setDeals(Set<DealImpl> deals);

}
