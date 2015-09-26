package com.becomejavasenior;

import com.becomejavasenior.impl.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import org.apache.log4j.Logger;


/**
 * Created by oleg on 9/17/15.
 */

@WebServlet(name = "DealAddServlet", urlPatterns = "/dealadd", loadOnStartup = 0)
public class DealAddServlet extends PersistServlet {
    private DaoManager daoManager;
    private Logger logger = Logger.getLogger(DealAddServlet.class);

    @Override
    public void init() throws ServletException {
        super.init();
        daoManager = DaoManager.getInstance();
    }

    @Override
    protected void doPostInPersistentCtx(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String name = request.getParameter("name");

        Set<Tag> tagList = splitTag(request.getParameter("tags"));
        Long respUserId = Long.parseLong(request.getParameter("responsibleUser"));
        BigDecimal budget = new BigDecimal(request.getParameter("budget"));
        Long statusId = Long.parseLong(request.getParameter("status"));
        Long companyId = Long.parseLong(request.getParameter("company"));

        Set<Comment> commentList = splitComments(request.getParameter("comments"));
        Set<Company> companyList = getCompaniesById(companyId);
        Set<Contact> contactList = getContactsFromRequest(request);
        //Set<File> fileList = getFilesFromRequest(request);

        logger.info("name: " + name);
        logger.info("tags: " + tagList);
        logger.info("respUserId: " + respUserId);
        logger.info("budget: " + budget);
        logger.info("statusId: " + statusId);
        logger.info("companyId: " + companyId);

        DaoManager daoManager = DaoManager.getInstance();
        User user = daoManager.getUserDAO().getById(respUserId);
        DealStatus dealStatus = daoManager.getDealStatusDAO().getById(statusId);

        Date date = new Date();

        DealDAO dealDAO = daoManager.getDealDAO();
        Deal newDeal = new DealImpl();

        newDeal.setName(name);
        newDeal.setTags(tagList);
        newDeal.setBudget(budget);
        newDeal.setResponsibleUser(user);
        newDeal.setDealStatus(dealStatus);
        newDeal.setComments(commentList);
        newDeal.setCompanies(companyList);
        newDeal.setContacts(contactList);
        //newDeal.setFiles(fileList);
        newDeal.setCreated(date);
        newDeal.setUpdated(date);

        dealDAO.insertOrUpdate(newDeal);

        logger.info(newDeal);
        logger.info(contactList);
        logger.info(companyList);
        logger.info(commentList);
        logger.info(tagList);

       response.sendRedirect("/");
    }

    @Override
    protected void doGetInPersistentCtx(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setAttribute("users", getRangeUsers(0, 50));
            request.setAttribute("dealStatuses", getRangeDealStatuses(0, 50));
            request.setAttribute("contacts", getRangeContacts(0, 50));
            request.setAttribute("companies", getRangeCompanies(0, 50));
            request.setAttribute("phoneTypes", getPhoneTypes());

        } catch(DAOException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("dealAdd.jsp").forward(request, response);
    }

    private Collection<User> getRangeUsers(long from, long to) throws DAOException {
        UserDAO userDAO = daoManager.getUserDAO();
        Collection<User> users = userDAO.getRange(from, to);

        return users;
    }

    private Collection<DealStatus> getRangeDealStatuses(long from, long to) throws DAOException {
        DealStatusDAO dealStatusDAO = daoManager.getDealStatusDAO();
        Collection<DealStatus> dealStatuses = dealStatusDAO.getRange(from, to);

        return dealStatuses;
    }

    private Collection<Contact> getRangeContacts(long from, long to) throws DAOException {
        ContactDAO contactDAO = daoManager.getContactDAO();
        Collection<Contact> contacts = contactDAO.getRange(from, to);

        return contacts;
    }

    private Collection<Company> getRangeCompanies(long from, long to) throws DAOException {
        CompanyDAO companyDAO = daoManager.getCompanyDAO();
        Collection<Company> companies = companyDAO.getRange(from, to);

        return companies;
    }

    private Collection<phoneTypeTmp> getPhoneTypes() {
        Collection<phoneTypeTmp> phoneTypes = new ArrayList<phoneTypeTmp>();

        phoneTypes.add(new phoneTypeTmp("Рабочий", Integer.toUnsignedLong(1)));
        phoneTypes.add(new phoneTypeTmp("Раб.прямой", Integer.toUnsignedLong(2)));
        phoneTypes.add(new phoneTypeTmp("Мобильный", Integer.toUnsignedLong(3)));
        phoneTypes.add(new phoneTypeTmp("Факс", Integer.toUnsignedLong(4)));
        phoneTypes.add(new phoneTypeTmp("Домашний", Integer.toUnsignedLong(5)));
        phoneTypes.add(new phoneTypeTmp("Другой", Integer.toUnsignedLong(6)));

        return phoneTypes;
    }

    private Set<Company> getCompaniesById(long company_id) {
        Set<Company> companies = new HashSet<Company>();

        if (company_id > 0) {
            Company company = DaoManager.getInstance().getCompanyDAO().getById(company_id);
            companies.add(company);
        }

        return companies;
    }

    private Set<Tag> splitTag(String tagsString) {
        Set<Tag> tagList = new HashSet<>();
        Tag currentTag = new TagImpl();
        currentTag.setName(tagsString);

        try {
            tagList.add(currentTag);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

        return tagList;
    }

    private Set<Comment> splitComments(String commentsString) {
        Set<Comment> commentList = new HashSet<>();
        Comment comment = new CommentImpl();

        Date date = new Date();

        comment.setName("sameName");
        comment.setComment(commentsString);
        comment.setCreated(date);
        comment.setUpdated(date);

        try {
            commentList.add(comment);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

        return commentList;
    }

    private Set<Contact> getContactsFromRequest(HttpServletRequest request) {
        Set<Contact> contactList = new HashSet<Contact>();
        int count = Integer.parseInt(request.getParameter("count_dock_contacts"));
        long contact_id;

        for (int i = 1; i <= count; i++ ) {
            contact_id = Long.parseLong(request.getParameter("dock_contact_" + i));
            Contact contact = DaoManager.getInstance().getContactDAO().getById(contact_id);
            contactList.add(contact);
        }

        return contactList;
    }

    private Set<File> getFilesFromRequest(HttpServletRequest request) {
        Set<File> fileList = new HashSet<File>();
        File file = new FileImpl();
        Date date = new Date();

        file.setMimeType("mime");
        file.setName("path");
        file.setCreated(date);
        file.setUpdated(date);

        try {
            fileList.add(file);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

        return fileList;
    }

}
