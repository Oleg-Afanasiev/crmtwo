package com.becomejavasenior;

import com.becomejavasenior.impl.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * ContactAdd servlet
 *
 * @author  Andrey Radionov <andyomsk@gmail.com>
 * @version 0.3
 */

@WebServlet(name = "ContactAddServlet", urlPatterns = "/contactadd", loadOnStartup = 0)
public class ContactAddServlet extends PersistServlet {

    @Override
    protected void doGetInPersistentCtx(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DaoManager daoManager = DaoManager.getInstance();

        UserDAO userDAO = daoManager.getUserDAO();
        Collection<User> users = userDAO.getRange(0, 100);
        req.setAttribute("users", users);

        CompanyDAO companyDAO = daoManager.getCompanyDAO();
        Collection<Company> companies = companyDAO.getRange(0, 100);
        req.setAttribute("companies", companies);

        req.getRequestDispatcher("contactAdd.jsp").forward(req, resp);
    }

    @Override
    protected void doPostInPersistentCtx(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Date createDate = new Date();

        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("contactName");
        String jobPosition = req.getParameter("jobPosition");
        String email = req.getParameter("email");
        String skype = req.getParameter("skype");

        Contact contact = new ContactImpl();
        contact.setName(name);
        contact.setJobPosition(jobPosition);
        contact.setEmail(email);
        contact.setSkype(skype);
        contact.setCreated(createDate);
        contact.setUpdated(createDate);

        DaoManager daoManager = DaoManager.getInstance();

        String companyIdInput = req.getParameter("companyId");
        if (!companyIdInput.equals("")) {
            CompanyDAO companyDAO = daoManager.getCompanyDAO();
            Company company = companyDAO.getById(Integer.parseInt(companyIdInput));
            contact.setCompany(company);
        }

        UserDAO userDAO = daoManager.getUserDAO();
        User user = userDAO.getById(Integer.parseInt(req.getParameter("responsibleUserId")));
        contact.setResponsibleUser(user);

        String tagsInput = req.getParameter("tags");
        if (!tagsInput.equals("")) {
            String[] tagStringArray = tagsInput.split("\\s+");
            Collection<Tag> tagCollection = new ArrayList<>();
            for (String s : tagStringArray) {
                if (s.substring(0,1).equals("#")){
                    Tag tag = new TagImpl();
                    tag.setName(s);
                    tagCollection.add(tag);
                }
            }
            contact.setTags(tagCollection);
        }

        String phoneNumberInput = req.getParameter("phoneNumber");
        if (!phoneNumberInput.equals("")) {
            Phone phone = new PhoneImpl();
            phone.setNumber(phoneNumberInput);
            phone.setPhoneType(Integer.parseInt(req.getParameter("phoneType")));
            Collection<Phone> phoneCollection = new ArrayList<>();
            phoneCollection.add(phone);
            contact.setPhones(phoneCollection);
        }

        String commentInput = req.getParameter("contactComment");
        if (!commentInput.equals("")) {
            Comment comment = new CommentImpl();
            comment.setName("Contact: " + contact.getName() + " creating comment");
            comment.setComment(commentInput);
            comment.setCreated(createDate);
            comment.setUpdated(createDate);
            Collection<Comment> commentCollection = new ArrayList<>();
            commentCollection.add(comment);
            contact.setComments(commentCollection);
        }

        String filesInput = req.getParameter("files");
        if (filesInput != null) {
            File file = new FileImpl();
            Path path = Paths.get(filesInput);
            String mimeType = Files.probeContentType(path);
            //TODO replace dummy - can't recognise pdf
            if (mimeType == null) {
                mimeType = "application/octet-stream";
            }
            //TODO replace dummy - need to save somewhere file and get it's path
            file.setPath("c:\\files\\"+path);
            file.setMimeType(mimeType);
            file.setCreated(createDate);
            file.setUpdated(createDate);
            Collection<File> fileCollection = new ArrayList<>();
            fileCollection.add(file);
            contact.setFiles(fileCollection);
        }

        ContactDAO contactDAO = daoManager.getContactDAO();
        contactDAO.insertOrUpdate(contact);

        resp.sendRedirect("contactlist");
    }
}
