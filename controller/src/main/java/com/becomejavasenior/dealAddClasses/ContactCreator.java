package com.becomejavasenior.dealAddClasses;
import com.becomejavasenior.*;
import com.becomejavasenior.impl.ContactImpl;

import java.util.Collection;
import java.util.Date;

import static com.becomejavasenior.dealAddClasses.InstanceParcer.*;

/**
 * Created by oleg on 11/2/15.
 */
public class ContactCreator {
    public ContactCreator() {

    }

    public Contact createNewContact(DealFields dealFields, ContactFields contactFields) {
        Long responsibleUserId = parseId(dealFields.getResponsibleUserId());
        String contactName = contactFields.getContactName();
        Long companyId = parseId(contactFields.getCompanyId());
        String jobPosition = contactFields.getJobPosition();
        Collection<Phone> phones = parsePhones(contactFields.getPhoneTypeId(), contactFields.getPhoneNumber());
        String email = contactFields.getEmail();
        String skype = contactFields.getSkype();

        DaoManager daoManager = DaoManager.getInstance();
        Company company = daoManager.getCompanyDAO().getById(companyId);
        User user = daoManager.getUserDAO().getById(responsibleUserId);
        Date date = new Date();

        Contact newContact = new ContactImpl();

        newContact.setName(contactName);
        newContact.setCompany(company);
        newContact.setJobPosition(jobPosition);
        newContact.setPhones(phones);
        newContact.setEmail(email);
        newContact.setSkype(skype);
        newContact.setResponsibleUser(user);
        newContact.setCreated(date);
        newContact.setUpdated(date);

        return newContact;
    }
}
