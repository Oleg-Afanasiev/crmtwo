package com.becomejavasenior;

import com.becomejavasenior.impl.DealImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import org.apache.log4j.Logger;

class abcd {
    public static void main(String[] args) {
        Logger log = Logger.getLogger(abcd.class);
        DaoManager daoManager = DaoManager.getInstance();
        DealDAO dealDAO = daoManager.getDealDAO();
        ContactDAO contactDAO = daoManager.getContactDAO();
        TagDAO tagDAO = daoManager.getTagDAO();
        CommentDAO commentDAO = daoManager.getCommentDAO();
        FileDAO fileDAO = daoManager.getFileDAO();
        CompanyDAO companyDAO = daoManager.getCompanyDAO();
        //DealStatusDAO dealStatusDAO = daoManager.getDealStatusDAO();
        //UserDAO userDAO = daoManager.getUserDAO();

        Deal deal = dealDAO.getById(7);

        Collection<Contact> listContact = contactDAO.getRange(1, 2);
        Collection<Tag> tags= tagDAO.getRange(1, 3);
        Collection<Comment> comments = commentDAO.getRange(1, 3);
        Collection<File> files = fileDAO.getRange(1, 3);
        Collection<Company> companies = companyDAO.getRange(1, 3);
//        Collection<Tag> tags = new ArrayList<>();
//        Tag tag = tagDAO.getById(1);
//        tags.add(tag);
       // listContact.add(contact);
       // DealStatus dealStatus = dealStatusDAO.getById(2);
        //User user = userDAO.getById(7);

        //dealDAO.insertOrUpdate();
//        BigDecimal bd = new BigDecimal(750000);
//        long nowTime = System.currentTimeMillis();
//        Date nowDate = new Date(nowTime);
//        Deal newDeal = new DealImpl();

//        newDeal.setBudget(bd);
//        newDeal.setCreated(nowDate);
//        newDeal.setUpdated(nowDate);
//        newDeal.setName("Новая сделка");
//        newDeal.setResponsibleUser(user);
//
//        newDeal.setDealStatus(dealStatus);

        deal.setContacts(listContact);
        deal.setTags(tags);
        deal.setComments(comments);
        deal.setFiles(files);
        deal.setCompanies(companies);

        //dealDAO.insertOrUpdate(deal);

//
//        System.out.println(deal);
       // System.out.println(newDeal);
       // System.out.println(dealStatus);
        log.info("companies.size(): " + companies.size());
        log.info("listContact.size(): " + listContact.size());
        daoManager.closeConnection();
    }
}