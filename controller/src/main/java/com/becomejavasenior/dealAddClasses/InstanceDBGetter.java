package com.becomejavasenior.dealAddClasses;

import com.becomejavasenior.Deal;
import com.becomejavasenior.Contact;
import com.becomejavasenior.Company;

/**
 * Created by oleg on 11/2/15.
 */
public class InstanceDBGetter {
    private DealCreator dealCreator;
    private ContactCreator contactCreator;
    private CompanyCreator companyCreator;

    public InstanceDBGetter() {
        dealCreator = new DealCreator();
        companyCreator = new CompanyCreator();
        contactCreator = new ContactCreator();
    }

    public Deal getNewDeal(DealFields dealFields) {
        return dealCreator.createNewDeal(dealFields);
    }

    public Contact getNewContact(DealFields dealFields, ContactFields contactFields) {
        return contactCreator.createNewContact(dealFields, contactFields);
    }

    public Company getNewCompany(DealFields dealFields, CompanyFields companyFields) {
        return companyCreator.createNewCompany(dealFields, companyFields);
    }
}
