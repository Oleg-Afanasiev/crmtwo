package com.becomejavasenior.task;

import com.becomejavasenior.*;
import com.becomejavasenior.deal.DealImpl;

import java.util.Date;

/**
 * JavaBean class describes TaskImpl model
 *
 * @author  Andrey Radionov <andyomsk@gmail.com>
 * @version 0.1
 */

public class TaskImpl extends IdentityImpl implements Identity, Task  {

    private static final long serialVersionUID = 1L;

    private TaskTypeImpl taskType;

    private UserImpl responsibleUserId;

    private Company company;

    private DealImpl deal;

    private ContactImpl contact;

    private TaskPeriodImpl taskPeriod;

    private Date dueDate;

    private CommentImpl comments;

    private Date created;

    private Date updated;

    private boolean isDeleted;

    public TaskImpl() {
    }

    public TaskTypeImpl getTaskType() {
        return taskType;
    }

    public UserImpl getResponsibleUserId() {
        return responsibleUserId;
    }

    public Company getCompany() {
        return company;
    }

    public DealImpl getDeal() {
        return deal;
    }

    public ContactImpl getContact() {
        return contact;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public CommentImpl getComments() {
        return comments;
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

    public void setTaskType(TaskTypeImpl taskType) {
        this.taskType = taskType;
    }

    public void setResponsibleUserId(UserImpl responsibleUserId) {
        this.responsibleUserId = responsibleUserId;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public void setDeal(DealImpl deal) {
        this.deal = deal;
    }

    public void setContact(ContactImpl contact) {
        this.contact = contact;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public void setComments(CommentImpl comments) {
        this.comments = comments;
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
}
