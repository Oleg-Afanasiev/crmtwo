package com.becomejavasenior;

import com.becomejavasenior.dealAddClasses.ContactFields;
import com.becomejavasenior.dealAddClasses.DealFields;

import javax.accessibility.Accessible;
import java.util.Set;

class abcd {
    public static void main(String[] args) {

        //int i = Integer.MAX_VALUE;
        //float f = i;
       // double d = i;
        double x = 2.0 -1.1;
        double f = 0.0;

        for (int i = 1; i <= 10; i++) {
            f += 0.1;
        }

        //System.out.println("i = " + i);
        System.out.println("f = " + f);
        //System.out.println("d = " + d);
        System.out.println("x = " + x);

//        DaoManager daoManager = DaoManager.getInstance();
//        DealDAO dealDAO = daoManager.getDealDAO();
//        UserDAO userDAO = daoManager.getUserDAO();
//        DealStatusDAO dealStatusDAO = daoManager.getDealStatusDAO();
//        CompanyDAO companyDAO = daoManager.getCompanyDAO();
//        ContactDAO contactDAO = daoManager.getContactDAO();
//
//        Collection<Company> companies = new ArrayList<>();
//        Collection<Contact> contacts = new ArrayList<>();
//
//        Deal newDeal = new DealImpl();
//        User user = userDAO.getById(1);
//        Contact contact = contactDAO.getById(1);
//        Company company = companyDAO.getById(1);
//        DealStatus dealStatus = dealStatusDAO.getById(1);
//
//        //companies.add(company);
//        //contacts.add(contact);
//
//        BigDecimal bg = new BigDecimal("200000");
//        Date date = new Date();
//
//        Company newCompany = new CompanyImpl();
//
//        newCompany.setAddress("address of company");
//        newCompany.setCreated(date);
//        newCompany.setUpdated(date);
//        newCompany.setEmail("email");
//        newCompany.setName("name of company 5");
//        newCompany.setResponsibleUser(user);
//        newCompany.setWebAddress("http://newcompany.com");
//
//        Contact newContact = new ContactImpl();
//
//        newContact.setCompany(newCompany);
//        newContact.setCreated(date);
//        newContact.setEmail("email@mail.ru");
//        newContact.setJobPosition("jobPosition");
//        newContact.setName("new contact name in abcd 5");
//        newContact.setResponsibleUser(user);
//        newContact.setSkype("skype");
//        newContact.setUpdated(date);
//
//        companies.add(newCompany);
//        contacts.add(newContact);
//
//        newDeal.setName("test new deal 5");
//        newDeal.setBudget(bg);
//        newDeal.setCompanies(companies);
//        newDeal.setContacts(contacts);
//        newDeal.setResponsibleUser(user);
//        newDeal.setDealStatus(dealStatus);
//        newDeal.setCreated(date);
//        newDeal.setUpdated(date);
//
//        dealDAO.insertOrUpdate(newDeal);
//
//        daoManager.closeConnection();
    }
}