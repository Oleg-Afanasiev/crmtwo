package com.becomejavasenior;

import com.becomejavasenior.impl.CompanyImpl;
import com.becomejavasenior.impl.ContactImpl;
import com.becomejavasenior.impl.PhoneImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Created by oleg on 10/3/15.
 */
public class DealContactFields {
    private String contactName;
    private String contactId;
    private String companyId;
    private String jobPosition;
    private String phoneTypeId;
    private String phoneNumber;
    private String email;
    private String skype;

    public DealContactFields() {

    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public void setJobPosition(String jobPosition) {
        this.jobPosition = jobPosition;
    }

    public void setPhoneTypeId(String phoneTypeId) {
        this.phoneTypeId = phoneTypeId;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getContactName() {
        return this.contactName;
    }

    public String getContactId() {
        return this.contactId;
    }

    public String getCompanyId() {
        return this.companyId;
    }

    public String getJobPosition() {
        return this.jobPosition;
    }

    public String getPhoneTypeId() {
        return this.phoneTypeId;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getEmail() {
        return this.email;
    }

    public String getSkype() {
        return this.skype;
    }

    public boolean checkFields() {
        try {
            Long.parseLong(companyId);
            Integer.parseInt(this.phoneTypeId);
            Integer.parseInt(this.phoneNumber);
        }
        catch(Exception ex) {
            ex.printStackTrace(System.out);
            return false;
        }

        if (contactName == "")
            return false;

        if (jobPosition == "")
            return false;

        if (email == "")
            return false;

        if (skype == "")
            return false;

        return true;
    }

    public Contact createNewContact(DaoManager daoManager, long responsibleUserId, DealInputValues dealInputValues) {
        Contact contact = new ContactImpl();
        Long companyId = Long.parseLong(this.companyId);
        Company company;
        DealCompanyFields dealCompanyFields;

        User user = daoManager.getUserDAO().getById(responsibleUserId);
        Date date = new Date();
        Collection<Phone> contactPhones = new ArrayList<>();

        Phone phone = new PhoneImpl();

        phone.setPhoneType(Integer.parseInt(this.phoneTypeId));
        phone.setNumber(this.phoneNumber);
        contactPhones.add(phone);

        if (companyId > 0)
            company = daoManager.getCompanyDAO().getById(companyId);
        else {
            dealCompanyFields = dealInputValues.getCompanyFieldsById(companyId.toString());
            company = dealCompanyFields.createNewCompany(daoManager, responsibleUserId);
        }

        contact.setCompany(company);
        contact.setResponsibleUser(user);
        contact.setPhones(contactPhones);
        contact.setName(this.contactName);
        contact.setJobPosition(this.jobPosition);
        contact.setEmail(this.email);
        contact.setSkype(this.skype);
        contact.setCreated(date);
        contact.setUpdated(date);

        return contact;
    }
}
