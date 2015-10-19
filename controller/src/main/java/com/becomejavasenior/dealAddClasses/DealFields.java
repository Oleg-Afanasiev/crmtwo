package com.becomejavasenior.dealAddClasses;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by oleg on 10/2/15.
 */
public class DealFields {
    private String name;
    private String tags;
    private String responsibleUserId;
    private String statusId;
    private String budget;
    private String comments;
    private Collection<DealAttachEntity> companies;
    private Collection<DealAttachEntity> contacts;

    public DealFields() {
        companies = new ArrayList<>();
        contacts = new ArrayList<>();

        reset();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public void setResponsibleUserId(String responsibleUserId) {
        this.responsibleUserId = responsibleUserId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setCompany(String id, String name) {

        if (id == null || name == null)
            return;

        if (id.equals("") || name.equals(""))
            return;

        DealAttachEntity company = new DealAttachEntity();
        company.setId(id);
        company.setName(name);

        this.companies.clear();
        this.companies.add(company);
    }

    public void setContact(String id, String name) {
        DealAttachEntity newContact = new DealAttachEntity();
        newContact.setId(id);
        newContact.setName(name);
        this.contacts.add(newContact);
    }

    public String getName() {
        return name;
    }

    public String getTags() {
        return tags;
    }

    public String getResponsibleUserId() {
        return responsibleUserId.toString();
    }

    public String getBudget() {
            return budget;
    }

    public String getStatusId() {
        return statusId;
    }

    public String getComments() {
        return comments;
    }

    public String getCompanyId() {
        if (this.companies.size() == 0)
            return "";
        else
            return this.companies.iterator().next().getId();
    }

    public Collection<DealAttachEntity> getCompanies() {
        return companies;
    }

    public Collection<DealAttachEntity> getContacts() {
        return this.contacts;
    }

    public Collection<String> getContactsId() {
        Collection<String> contactsId = new ArrayList<>();

        for (DealAttachEntity contact : this.contacts) {
            contactsId.add(contact.getId());
        }

        return contactsId;
    }

    public void reset() {
        name = "";
        tags = "";
        responsibleUserId = "";
        statusId = "";
        budget = "";
        comments = "";
        companies.clear();
        contacts.clear();
    }
}
