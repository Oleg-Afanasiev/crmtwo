package com.becomejavasenior;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

/**
 * Created by user on 9/6/2015.
 */
public interface Company extends Identity {

    User getResponsibleUser();

    String getName();

    String getEmail();

    String getWebAddress();

    String getAddress();

    Date getCreated();

    Date getUpdated();

    Collection<Phone> getPhones();

    Collection<Deal> getDeals();

    Collection<Tag> getTags();

    Collection<File> getFiles();

    Collection<Comment> getComments();

    void setResponsibleUser(User responsibleUser);

    void setName(String name);

    void setEmail(String email);

    void setWebAddress(String webAdress);

    void setAddress(String adress);

    void setCreated(Date created);

    void setUpdated(Date updated);

    void setPhones(Collection<Phone> phones);

    void setDeals(Collection<Deal> deals);

    void setTags(Collection<Tag> tags);

    void setFiles(Collection<File> files);

    void setComments(Collection<Comment> comments);
}