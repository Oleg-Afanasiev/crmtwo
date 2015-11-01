package com.becomejavasenior.servlets;

import com.becomejavasenior.*;
import com.becomejavasenior.dealAddClasses.PhoneTypeCollect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

/**
 * Created by oleg on 10/21/15.
 */
@WebServlet(name = "DealEditServlet", urlPatterns = "/crm/dealedit", loadOnStartup = 0)
@MultipartConfig(maxFileSize = 102400)
public class DealEditServlet extends PersistServlet {
    protected static final Logger logger = LoggerFactory.getLogger(ContactEditServlet.class);
    final private PhoneTypeCollect phoneTypeCollect = new PhoneTypeCollect();

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doPostInPersistentCtx(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGetInPersistentCtx(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.debug("forward to contactAdd.jsp");
        User user = (User) request.getSession().getAttribute("activeUser");
        if (!user.getRole().getName().equals("admin")) {
            request.getRequestDispatcher("/jsp/loginError.jsp").forward(request, response);
        } else {
            request.setCharacterEncoding("UTF-8");
            int id = Integer.parseInt(request.getParameter("id"));

            DaoManager daoManager = DaoManager.getInstance();

            DealDAO dealDAO = daoManager.getDealDAO();
            UserDAO userDAO = daoManager.getUserDAO();
            DealStatusDAO dealStatusDAO = daoManager.getDealStatusDAO();
            CompanyDAO companyDAO = daoManager.getCompanyDAO();
            ContactDAO contactDAO = daoManager.getContactDAO();

            Deal deal;
            Collection<Company> dealCompanies;
            Collection<Company> companies;
            Collection<Contact> dealContacts;

            Company dealCompany;

            try {
                deal = dealDAO.getById(id);
                Collection<User> users = userDAO.getRange(0, 100);
                Collection<DealStatus> dealStatuses = dealStatusDAO.getRange(0, 100);
                dealCompanies = deal.getCompanies();
                companies = companyDAO.getRange(0, 100);

                StringBuilder dealTags = new StringBuilder("");
                Collection<Tag> dealTagsCollection = deal.getTags();
                if (dealTagsCollection != null) {
                    for (Tag t : dealTagsCollection) {
                        dealTags.append(t.getName());
                        dealTags.append(" ");
                    }
                }

                dealCompany = dealCompanies.iterator().next();
                dealContacts = deal.getContacts();

                request.setAttribute("deal", deal);
                request.setAttribute("users", users);
                request.setAttribute("dealStatuses", dealStatuses);
                request.setAttribute("phoneTypes", phoneTypeCollect.getPhoneTypes());
                request.setAttribute("dealTags", dealTags.toString());
                request.setAttribute("dealCompany", dealCompany);
                request.setAttribute("companies", companies);
                request.setAttribute("dealContacts", dealContacts);

                request.getRequestDispatcher("/jsp/dealEdit.jsp").forward(request, response);
            } catch (DAOException e) {
                logger.debug(e.getMessage() + "forward to dealList.jsp");
                response.sendRedirect("/crm/deallist");
            }
        }
    }
}
