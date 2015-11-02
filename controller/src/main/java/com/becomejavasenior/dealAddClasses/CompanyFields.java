package com.becomejavasenior.dealAddClasses;

import com.becomejavasenior.Company;
import com.becomejavasenior.DaoManager;
import com.becomejavasenior.Phone;
import com.becomejavasenior.User;
import com.becomejavasenior.impl.CompanyImpl;
import com.becomejavasenior.impl.PhoneImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Created by oleg on 10/3/15.
 */
public class CompanyFields {
    private String phoneTypeId;
    private String phoneNumber;
    private String companyName;
    public String companyId;
    private String email;
    private String webAddress;
    private String address;

    public CompanyFields() {
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

    public void clear() {
        phoneTypeId = "";
        phoneNumber = "";
        companyName = "";
        companyId = "";
        email = "";
        webAddress = "";
        address = "";
    }
 }
