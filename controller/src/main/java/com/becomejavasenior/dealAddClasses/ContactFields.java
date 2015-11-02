package com.becomejavasenior.dealAddClasses;

import com.becomejavasenior.*;
import com.becomejavasenior.impl.ContactImpl;
import com.becomejavasenior.impl.PhoneImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Created by oleg on 10/3/15.
 */
public class ContactFields implements Cloneable {
    private String contactId;
    private String contactName;
    private String companyId;
    private String companyName;
    private String jobPosition;
    private String phoneTypeId;
    private String phoneTypeName;
    private String phoneNumber;
    private String email;
    private String skype;

    public ContactFields() {

    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    public void setPhoneTypeName(String phoneTypeName) {
        this.phoneTypeName = phoneTypeName;
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

    public String getCompanyName() {
        return this.companyName;
    }

    public String getCompanyId() {
        return this.companyId;
    }

    public String getJobPosition() {
        return this.jobPosition;
    }

    public String getPhoneTypeName() {
        return this.phoneTypeName;
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

    public void clear() {
        contactName = "";
        contactId = "";
        companyName = "";
        companyId = "";
        jobPosition = "";
        phoneTypeName = "";
        phoneTypeId = "";;
        phoneNumber = "";;
        email = "";
        skype = "";
    }

    public Object clone() {
        try {
            return super.clone();
        } catch(CloneNotSupportedException ex) {
            ex.printStackTrace(System.out);
        }
        return null;
    }

}
