package com.becomejavasenior.servlets;

import com.becomejavasenior.*;
import com.becomejavasenior.dealAddClasses.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.slf4j.LoggerFactory;


/**
 * Created by oleg on 9/17/15.
 */
@WebServlet(name = "DealAddServlet", urlPatterns = "/crm/dealadd")
@MultipartConfig
public class DealAddServlet extends PersistServlet {

    final private PhoneTypeCollect phoneTypeCollect = new PhoneTypeCollect();

    private DataContainer dataContainer;
    private DataValidator dataValidator;
    private InstanceDBGetter instanceDBGetter;

    protected static final org.slf4j.Logger logger = LoggerFactory.getLogger(DealAddServlet.class);

    @Override
    public void init() throws ServletException {
        super.init();

        dataContainer = new DataContainer();
        dataValidator = new DataValidator();
        instanceDBGetter = new InstanceDBGetter();
    }

    @Override
    protected void doPostInPersistentCtx(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");

        dataValidator.reset();
        dataContainer.clearAll();
        dataContainer.readAllParameters(request);

        if (request.getParameter("saveDeal") != null) {
            doPostSaveDeal(response);
        } else if(request.getParameter("saveContact") != null) {
            doPostSaveContact(response);
        } else if (request.getParameter("saveCompany") != null) {
            doPostSaveCompany(response);
        }
    }

    @Override
    protected void doGetInPersistentCtx(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("forward to dealAdd.jsp");
        User user = (User) req.getSession().getAttribute("activeUser");

        if (!user.getRole().getName().equals("admin")) {
            req.getRequestDispatcher("/jsp/loginError.jsp").forward(req, resp);
        } else {
            DaoManager daoManager = DaoManager.getInstance();
            req.setAttribute("users", daoManager.getUserDAO().getRange(0, 100));
            req.setAttribute("dealStatuses", daoManager.getDealStatusDAO().getRange(0, 100));
            req.setAttribute("contacts", daoManager.getContactDAO().getRange(0, 100));
            req.setAttribute("companies", daoManager.getCompanyDAO().getRange(0, 100));
            req.setAttribute("phoneTypes", phoneTypeCollect.getPhoneTypes());
            req.setAttribute("dealFields", dataContainer.getDealFields());
            req.setAttribute("contactFields", dataContainer.getContactFields());
            req.setAttribute("companyFields", dataContainer.getCompanyFields());
            req.setAttribute("dealInputError", dataValidator.getErrorList());
            req.setAttribute("savedContacts", dataContainer.getSavedContacts());
            System.out.println(req.getParameter("tags"));
            req.setAttribute("testValue", req.getParameter("tags"));
        }

        req.getRequestDispatcher("/jsp/dealAdd.jsp").forward(req, resp);
        dataContainer.clearAll();
        dataValidator.reset();
    }

    private void doPostSaveDeal(HttpServletResponse response) throws ServletException, IOException {

        if (dataValidator.isValidDealFields(dataContainer.getDealFields())){
            Deal newDeal = instanceDBGetter.getNewDeal(dataContainer.getDealFields());
            DealDAO dealDAO = DaoManager.getInstance().getDealDAO();
            dealDAO.insertOrUpdate(newDeal);

            response.sendRedirect("/crm/deallist");
            dataContainer.dealFieldsClear();
        }
        else
            response.sendRedirect("/crm/dealadd");
    }

    private void doPostSaveContact(HttpServletResponse response) throws ServletException, IOException {

        if (dataValidator.isValidContactFields(dataContainer.getContactFields())) {
            ContactDAO contactDAO = DaoManager.getInstance().getContactDAO();
            Contact newContact = instanceDBGetter.getNewContact(dataContainer.getDealFields(), dataContainer.getContactFields());
            contactDAO.insertOrUpdate(newContact);
            dataContainer.addSavedContacts(newContact);
            dataContainer.contactFieldsClear();
        }

        response.sendRedirect("/crm/dealadd");
    }

    private void doPostSaveCompany(HttpServletResponse response) throws ServletException, IOException {

        if (dataValidator.isValidCompanyFields(dataContainer.getCompanyFields())) {
            CompanyDAO companyDAO = DaoManager.getInstance().getCompanyDAO();
            Company newCompany = instanceDBGetter.getNewCompany(dataContainer.getDealFields(), dataContainer.getCompanyFields());
            companyDAO.insertOrUpdate(newCompany);
            dataContainer.companyFieldsClear();
        }

        response.sendRedirect("/crm/dealadd");
    }
}
