package com.becomejavasenior.servlets;

import com.becomejavasenior.Contact;
import com.becomejavasenior.ContactDAO;
import com.becomejavasenior.DaoManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

/**
 * ContactList servlet
 *
 * @author  Andrey Radionov <andyomsk@gmail.com>
 * @version 0.3
 */

@WebServlet(name = "ContactListServlet", urlPatterns = "/crm/contactlist", loadOnStartup = 0)
public class ContactListServlet extends PersistServlet {
    protected static final Logger logger = LoggerFactory.getLogger(ContactListServlet.class);

    @Override
    protected void doGetInPersistentCtx(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("contact list forward");
        DaoManager daoManager = DaoManager.getInstance();
        ContactDAO contactDAO = daoManager.getContactDAO();
        Collection<Contact> contacts = contactDAO.getRange(0, 1000);
        req.setAttribute("contacts", contacts);
        req.getRequestDispatcher("/jsp/contactList.jsp").forward(req, resp);
    }
}
