package com.becomejavasenior.dealAddClasses;

import com.becomejavasenior.*;
import com.becomejavasenior.impl.DealImpl;
import com.becomejavasenior.impl.FileImpl;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Created by oleg on 11/2/15.
 */
public class DealCreator extends InstanceDBCreator {
    public DealCreator() {

    }

    public Deal createNewDeal(DealFields dealFields) {
        String dealName = dealFields.getName();
        Collection<Tag> tags = parseTags(dealFields.getTags());
        Long responsibleUserId = parseId(dealFields.getResponsibleUserId());
        BigDecimal budget = parseBudget(dealFields.getBudget());
        Long statusId = parseId(dealFields.getStatusId());
        Collection<Comment> comments = parseComments(dealFields.getComments(), "deal", dealName);
        Collection<Company> dealCompanies = parseCompanies(dealFields.getCompanyId());
        Collection<Contact> dealContacts = parseContacts(dealFields.getContactsId());
        Collection<File> files = parseFiles(dealFields.getPartFiles()); // from dealFields
        Date date = new Date();

        DaoManager daoManager = DaoManager.getInstance();
        User user;
        DealStatus dealStatus;
        Deal newDeal;

        user = daoManager.getUserDAO().getById(responsibleUserId);
        dealStatus = daoManager.getDealStatusDAO().getById(statusId);

        newDeal = new DealImpl();
        newDeal.setName(dealName);
        newDeal.setTags(tags);
        newDeal.setBudget(budget);
        newDeal.setResponsibleUser(user);
        newDeal.setDealStatus(dealStatus);
        newDeal.setComments(comments);
        newDeal.setCompanies(dealCompanies);
        newDeal.setContacts(dealContacts);
        newDeal.setFiles(files);
        newDeal.setCreated(date);
        newDeal.setUpdated(date);

        return newDeal;
    }
}
