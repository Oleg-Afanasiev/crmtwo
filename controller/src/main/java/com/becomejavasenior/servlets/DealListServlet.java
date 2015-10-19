package com.becomejavasenior.servlets;

import com.becomejavasenior.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by oleg on 9/17/15.
 */
@WebServlet(name="DealListServlet", urlPatterns = "/crm/deallist", loadOnStartup = 0)
public class DealListServlet extends PersistServlet {
    protected static final Logger logger = LoggerFactory.getLogger(DealListServlet.class);

    @Override
    protected void doPostInPersistentCtx(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGetInPersistentCtx(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        logger.debug("deal list forward");
        DaoManager daoManager = DaoManager.getInstance();
        DealDAO dealDAO = daoManager.getDealDAO();
        ContactDAO contactDAO = daoManager.getContactDAO();
        Collection<Deal> deals = dealDAO.getRange(0, 1000);
        Deal deal = dealDAO.getById(1);
        deal.getContacts();
        Contact contact = contactDAO.getById(1);

        req.setAttribute("deals", deals);
        //req.setAttribute("baseContact", );

        req.getRequestDispatcher("/jsp/dealList.jsp").forward(req, response);
    }
}
