package com.becomejavasenior.task;

import com.becomejavasenior.*;
import com.becomejavasenior.deal.DealImpl;

import java.util.Date;

/**
 * Created by Dmytro Tsapko on 9/7/2015.
 */
public interface Task extends Identity {

    public TaskTypeImpl getTaskType();

    public UserImpl getResponsibleUserId();

    public Company getCompany();

    public DealImpl getDeal();

    public ContactImpl getContact();

    public Date getDueDate();

    public CommentImpl getComments();

    public Date getCreated();

    public Date getUpdated();

    public boolean isDeleted();

    public void setTaskType(TaskTypeImpl taskType);

    public void setResponsibleUserId(UserImpl responsibleUserId);

    public void setCompany(Company company);

    public void setDeal(DealImpl deal);

    public void setContact(ContactImpl contact);

    public void setDueDate(Date dueDate);

    public void setComments(CommentImpl comments);

    public void setCreated(Date created);

    public void setUpdated(Date updated);

    public void setIsDeleted(boolean isDeleted);

}
