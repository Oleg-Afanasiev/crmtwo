package com.becomejavasenior.dealAddClasses;
import com.becomejavasenior.Company;
import com.becomejavasenior.DaoManager;
import com.becomejavasenior.Phone;
import com.becomejavasenior.User;
import com.becomejavasenior.impl.CompanyImpl;

import java.util.Collection;
import java.util.Date;

import static com.becomejavasenior.dealAddClasses.InstanceParcer.*;

/**
 * Created by oleg on 11/2/15.
 */
public class CompanyCreator {
    public CompanyCreator() {

    }

    public Company createNewCompany(DealFields dealFields, CompanyFields companyFields) {
        Long responsibleUserId = parseId(dealFields.getResponsibleUserId());
        String companyName = companyFields.getCompanyName();
        Collection<Phone> phones = parsePhones(companyFields.getPhoneTypeId(), companyFields.getPhoneNumber());
        String email = companyFields.getEmail();
        String url = companyFields.getWebAddress();
        String address = companyFields.getAddress();
        Date date = new Date();

        DaoManager daoManager = DaoManager.getInstance();
        User user;
        Company newCompany = new CompanyImpl();

        user = daoManager.getUserDAO().getById(responsibleUserId);
        newCompany.setName(companyName);
        newCompany.setPhones(phones);
        newCompany.setEmail(email);
        newCompany.setAddress(address);
        newCompany.setWebAddress(url);
        newCompany.setResponsibleUser(user);
        newCompany.setCreated(date);
        newCompany.setUpdated(date);

        return newCompany;
    }
}
