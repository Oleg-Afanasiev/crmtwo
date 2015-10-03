package com.becomejavasenior.impl;

import com.becomejavasenior.*;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

/**
 * JavaBean class describes TaskImpl model
 *
 * @author  Andrey Radionov <andyomsk@gmail.com>
 * @version 0.3
 */

public class TaskImpl extends IdentityImpl implements Identity, Task {

    private static final long serialVersionUID = 1L;

    private TaskType taskType;

    private String description;

    private User responsibleUser;

    private Company company;

    private Deal deal;

    private Contact contact;

    private TaskPeriod taskPeriod;

    private Date dueDate;

    private Collection<Comment> comments;

    private Date created;

    private Date updated;

    public TaskImpl() {
    }

    public TaskPeriod getTaskPeriod() {
        return taskPeriod;
    }

    public String getDescription() {
        return description;
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

    public Collection<Comment> getComments() {
        return comments;
    }

    public Date getCreated() {
        return created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setTaskPeriod(TaskPeriod taskPeriod) {
        this.taskPeriod = taskPeriod;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public void setComments(Collection<Comment> comments) {
        this.comments = comments;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        return "TaskImpl{" +
                "id=" + super.getId() +
                ", taskType=" + taskType +
                ", description='" + description + '\'' +
                ", responsibleUser=" + responsibleUser +
                ", taskPeriod=" + taskPeriod +
                ", dueDate=" + dueDate +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}
