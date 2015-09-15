package com.becomejavasenior;

import javax.servlet.ServletException;
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

public class ContactListServlet extends PersistServlet {

    @Override
    protected void doGetInPersistentCtx(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DaoManager daoManager = DaoManager.getInstance();
        ContactDAO contactDAO = daoManager.getContactDAO();
        Collection<Contact> contacts = contactDAO.getRange(0, 1000);
        req.setAttribute("contacts", contacts);
        req.getRequestDispatcher("contactList.jsp").forward(req, resp);
    }
}
