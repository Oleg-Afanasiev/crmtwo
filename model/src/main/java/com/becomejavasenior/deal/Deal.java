package com.becomejavasenior.deal;

import com.becomejavasenior.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

/**
 * Created by Dmytro Tsapko on 9/7/2015.
 */
public interface Deal extends Identity {

    public UserImpl getResponsibleUserId();

    public DealStatusImpl getDealStatus();

    public String getName();

    public BigDecimal getBugget();

    public Date getCreated();

    public Date getUpdated();

    public boolean isDeleted();

    public Set<TagImpl> getTags();

    public Set<FileImpl> getFiles();

    public Set<CommentImpl> getComments();

    public Set<ContactImpl> getContacts();

    public void setResponsibleUserId(UserImpl responsibleUserId);

    public void setDealStatus(DealStatusImpl dealStatus);

    public void setName(String name);

    public void setBugget(BigDecimal bugget);

    public void setCreated(Date created);

    public void setUpdated(Date updated);

    public void setIsDeleted(boolean isDeleted);

    public void setTags(Set<TagImpl> tags);

    public void setFiles(Set<FileImpl> files);

    public void setComments(Set<CommentImpl> comments);

    public void setContacts(Set<ContactImpl> contacts);

}
