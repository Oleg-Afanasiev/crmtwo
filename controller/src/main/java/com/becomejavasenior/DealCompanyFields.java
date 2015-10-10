package com.becomejavasenior;

import com.becomejavasenior.impl.CompanyImpl;
import com.becomejavasenior.impl.PhoneImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Created by oleg on 10/3/15.
 */
public class DealCompanyFields {
    private String phoneTypeId;
    private String phoneNumber;
    private String companyName;
    public String companyId;
    private String email;
    private String webAddress;
    private String address;
    private Company company;

    public DealCompanyFields() {
        company = new CompanyImpl();
    }

    public void setPhoneTypeId(String phoneTypeId) {
        this.phoneTypeId = phoneTypeId;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setWebAddress(String webAddress) {
        this.webAddress = webAddress;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneTypeId() {
        return this.phoneTypeId;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public String getCompanyId() {
        return this.companyId;
    }

    public String getEmail() {
        return this.email;
    }

    public String getWebAddress() {
        return this.webAddress;
    }

    public String getAddress() {
        return this.address;
    }

    public boolean checkFields() {

        try {
            Integer.parseInt(this.phoneTypeId);
            Integer.parseInt(this.phoneNumber);
        }
        catch(Exception ex) {
            ex.printStackTrace(System.out);
            return false;
        }

        if (this.email.indexOf('@') == -1)
            return false;

        if (this.companyName == "")
            return false;

        if (this.address == "")
            return false;

        if (this.webAddress == "")
            return false;

        return true;
    }

    public Company createNewCompany(DaoManager daoManager, long responsibleUserId) {

        Collection<Phone> phones = new ArrayList<>();
        Phone phone = new PhoneImpl();
        Date date = new Date();
        User user= daoManager.getUserDAO().getById(responsibleUserId);

        phone.setPhoneType(Integer.parseInt(this.phoneTypeId));
        phone.setNumber(this.phoneNumber);
        phones.add(phone);

        company.setName(this.companyName);
        company.setEmail(this.email);
        company.setAddress(this.address);
        company.setWebAddress(this.webAddress);
        company.setPhones(phones);
        company.setResponsibleUser(user);

        company.setCreated(date);
        company.setUpdated(date);

        return company;
    }
 }
