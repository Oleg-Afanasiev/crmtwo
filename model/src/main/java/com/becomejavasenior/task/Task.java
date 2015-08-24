package com.becomejavasenior.task;

import com.becomejavasenior.*;
import com.becomejavasenior.deal.Deal;

import java.util.Date;

/**
 * JavaBean class describes Task model
 *
 * @author  Andrey Radionov <andyomsk@gmail.com>
 * @version 0.1
 */

public class Task extends Identity {

    private static final long serialVersionUID = 1L;

    private TaskType taskType;

    private User responsibleUserId;

    private Company company;

    private Deal deal;

    private Contact contact;

    private TaskPeriod taskPeriod;

    private Date dueDate;

    private Comment comments;

    private Date created;

    private Date updated;

    private boolean isDeleted;

    public Task() {
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public User getResponsibleUserId() {
        return responsibleUserId;
    }

    public Company getCompany() {
        return company;
    }

    public Deal getDeal() {
        return deal;
    }

    public Contact getContact() {
        return contact;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public Comment getComments() {
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

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    public void setResponsibleUserId(User responsibleUserId) {
        this.responsibleUserId = responsibleUserId;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public void setDeal(Deal deal) {
        this.deal = deal;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public void setComments(Comment comments) {
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
