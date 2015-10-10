package com.becomejavasenior;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by oleg on 10/2/15.
 */
public class DealInputValues {
    private String name;
    private String tags;
    private Long responsibleUserId;
    private Long statusId;
    private BigDecimal budget;
    private String comments;
    private Collection<DealContactFields> dealContactsFields;
    private Collection<DealContactFields> newContactsFields;
    private DealCompanyFields dealCompanyFields;
    private Collection<DealCompanyFields> newCompaniesFields;

    public DealInputValues() {
        dealContactsFields = new ArrayList<>();
        newContactsFields = new ArrayList<>();
        newCompaniesFields = new ArrayList<>();

        reset();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public void setResponsibleUserId(Long responsibleUserId) {
        this.responsibleUserId = responsibleUserId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setDealContactFields(DealContactFields dealContactFields) {
        this.dealContactsFields.add(dealContactFields);
    }

    public void setDealContactFields(Contact dealContact) {
        DealContactFields dealContactFields = new DealContactFields();

        dealContactFields.setContactId(dealContact.getId().toString());
        dealContactFields.setContactName(dealContact.getName());
        dealContactsFields.add(dealContactFields);
    }

    public void setNewContactsFields(DealContactFields newContactFields) {
        this.newContactsFields.add(newContactFields);
    }

    public void setDealCompanyFields(DealCompanyFields dealCompanyFields) {
        this.dealCompanyFields = dealCompanyFields;
    }

    public void setDealCompanyFields(Company dealCompany) {

        this.dealCompanyFields.setCompanyName(dealCompany.getName());
        this.dealCompanyFields.setCompanyId(dealCompany.getId().toString());
    }

    public void setNewCompaniesFields(DealCompanyFields newCompaniesFields) {
        this.newCompaniesFields.add(newCompaniesFields);
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
        if (budget.doubleValue() == 0)
            return "";
        else
            return budget.toString();
    }

    public String getStatusId() {
        return statusId.toString();
    }

    public String getComments() {
        return comments;
    }

    public Collection<DealContactFields> getDealContactsFields() {
        return dealContactsFields;
    }

    public Collection<DealContactFields> getNewContactsFields() {
        return newContactsFields;
    }

    public DealCompanyFields getDealCompanyFields() {
        return dealCompanyFields;
    }

    public Collection<DealCompanyFields> getNewCompaniesFields() {
        return newCompaniesFields;
    }

    public DealCompanyFields getCompanyFieldsById(String companyId) {

        if (dealCompanyFields.getCompanyId().equals(companyId))
            return dealCompanyFields;

        for (DealCompanyFields newCompanyFields : newCompaniesFields)
            if(newCompanyFields.getCompanyId().equals(companyId))
                return newCompanyFields;

        return null;
    }

    public void reset() {
        name = "";
        tags = "";
        responsibleUserId = 0L;
        statusId = 0L;
        budget = new BigDecimal("0");
        budget.setScale(2, BigDecimal.ROUND_HALF_UP);
        comments = "";

        dealContactsFields.clear();
        newContactsFields.clear();
        newCompaniesFields.clear();
        dealCompanyFields = new DealCompanyFields();
    }
}
