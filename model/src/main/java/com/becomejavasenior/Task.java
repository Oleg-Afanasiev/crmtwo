package com.becomejavasenior;


import java.util.Collection;
import java.util.Date;
import java.util.Set;

/**
 * Created by Dmytro Tsapko on 9/7/2015.
 */
public interface Task extends Identity {

    public TaskPeriod getTaskPeriod();

    public String getDescription();

    public TaskType getTaskType();

    public User getResponsibleUser();

    public Company getCompany();

    public Deal getDeal();

    public Contact getContact();

    public Date getDueDate();

    public Collection<Comment> getComments();

    public Date getCreated();

    public Date getUpdated();

    public void setTaskPeriod(TaskPeriod taskPeriod);

    public void setDescription(String description);

    public void setTaskType(TaskType taskType);

    public void setResponsibleUser(User responsibleUser);

    public void setCompany(Company company);

    public void setDeal(Deal deal);

    public void setContact(Contact contact);

    public void setDueDate(Date dueDate);

    public void setComments(Collection<Comment> comments);

    public void setCreated(Date created);

    public void setUpdated(Date updated);

}
