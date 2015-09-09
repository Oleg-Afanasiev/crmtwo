package com.becomejavasenior.task;

import com.becomejavasenior.*;
import com.becomejavasenior.deal.Deal;
import com.becomejavasenior.deal.DealImpl;

import java.util.Date;
import java.util.Set;

/**
 * JavaBean class describes TaskImpl model
 *
 * @author  Andrey Radionov <andyomsk@gmail.com>
 * @version 0.1
 */

public class TaskImpl extends IdentityImpl implements Identity, Task  {

    private static final long serialVersionUID = 1L;

    private TaskType taskType;

    private User responsibleUser;

    private Company company;

    private Deal deal;

    private Contact contact;

    private TaskPeriod taskPeriod;

    private Date dueDate;

    private Set<Comment> comments;

    private Date created;

    private Date updated;

    private boolean isDeleted;

    public TaskImpl() {
    }

    @Override
    public TaskPeriod getTaskPeriod() {
        return taskPeriod;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public User getResponsibleUser() {
        return responsibleUser;
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

    public Set<Comment> getComments() {
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

    @Override
    public void setTaskPeriod(TaskPeriod taskPeriod) {
        this.taskPeriod = taskPeriod;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    public void setResponsibleUser(User responsibleUser) {
        this.responsibleUser = responsibleUser;
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

    public void setComments(Set<Comment> comments) {
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
