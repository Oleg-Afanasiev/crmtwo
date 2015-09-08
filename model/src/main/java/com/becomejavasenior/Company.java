package com.becomejavasenior;

import com.becomejavasenior.deal.Deal;
import com.becomejavasenior.deal.DealImpl;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * Created by user on 9/6/2015.
 */
public interface Company extends Identity {

    User getResponsibleUser();

    String getName();

    String getEmail();

    String getWebAdress();

    String getAdress();

    Date getCreated();

    Date getUpdated();

    boolean isDeleted();

    Set<Phone> getPhones();

    Set<Deal> getDeals();

    Set<Tag> getTags();

    Set<File> getFiles();

    Set<Comment> getComments();

    void setResponsibleUser(User responsibleUser);

    void setName(String name);

    void setEmail(String email);

    void setWebAdress(String webAdress);

    void setAdress(String adress);

    void setCreated(Date created);

    void setUpdated(Date updated);

    void setIsDeleted(boolean isDeleted);

    void setPhones(Set<Phone> phones);

    void setDeals(Set<Deal> deals);

    void setTags(Set<Tag> tags);

    void setFiles(Set<File> files);

    void setComments(Set<Comment> comments);
}