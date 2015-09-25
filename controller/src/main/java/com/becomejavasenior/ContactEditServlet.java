package com.becomejavasenior;

import com.becomejavasenior.impl.CommentImpl;
import com.becomejavasenior.impl.FileImpl;
import com.becomejavasenior.impl.PhoneImpl;
import com.becomejavasenior.impl.TagImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * ContactEdit servlet
 *
 * @author  Andrey Radionov <andyomsk@gmail.com>
 * @version 0.3
 */

@WebServlet(name = "ContactEditServlet", urlPatterns = "/contactedit", loadOnStartup = 0)
public class ContactEditServlet extends PersistServlet {

    @Override
    protected void doGetInPersistentCtx(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(req.getParameter("id"));

        DaoManager daoManager = DaoManager.getInstance();

        ContactDAO contactDAO = daoManager.getContactDAO();
        Contact contact = contactDAO.getById(id);
        req.setAttribute("contact", contact);

        UserDAO userDAO = daoManager.getUserDAO();
        Collection<User> users = userDAO.getRange(0, 100);
        req.setAttribute("users", users);

        CompanyDAO companyDAO = daoManager.getCompanyDAO();
        Collection<Company> companies = companyDAO.getRange(0, 100);
        req.setAttribute("companies", companies);

        StringBuilder tags = new StringBuilder("");
        Collection<Tag> tagCollection = contact.getTags();
        if (tagCollection != null) {
            for (Tag t : tagCollection) {
                tags.append(t.getName());
                tags.append(" ");
            }
        }

        req.setAttribute("tags", tags.toString());
        req.getRequestDispatcher("contactEdit.jsp").forward(req, resp);
    }

    @Override
    protected void doPostInPersistentCtx(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        DaoManager daoManager = DaoManager.getInstance();
        ContactDAO contactDAO = daoManager.getContactDAO();
        Long id = Long.parseLong(req.getParameter("id"));

        if (req.getParameter("delete") != null) {
            contactDAO.delete(id);
        }
        else {
            Date date = new Date();

            String name = req.getParameter("contactName");
            String jobPosition = req.getParameter("jobPosition");
            String email = req.getParameter("email");
            String skype = req.getParameter("skype");

            Contact contact = contactDAO.getById(id);

            contact.setName(name);
            contact.setJobPosition(jobPosition);
            contact.setEmail(email);
            contact.setSkype(skype);
            contact.setUpdated(date);

            String companyIdInput = req.getParameter("companyId");
            if (!companyIdInput.equals("") ) {
                int companyId = Integer.parseInt(companyIdInput);
                if(contact.getCompany().getId() != companyId) {
                    CompanyDAO companyDAO = daoManager.getCompanyDAO();
                    Company company = companyDAO.getById(companyId);
                    contact.setCompany(company);
                }
            }

            int userId = Integer.parseInt(req.getParameter("responsibleUserId"));
            if (contact.getResponsibleUser().getId() != userId) {
                UserDAO userDAO = daoManager.getUserDAO();
                User user = userDAO.getById(userId);
                contact.setResponsibleUser(user);
            }

            String tagsInput = req.getParameter("tags");
            if (!tagsInput.equals("") && tagsInput.substring(0,1).equals("#")) {
                List<String> tagStringList = new ArrayList<>(Arrays.asList(tagsInput.split("\\s+")));
                contact.getTags().stream()
                        .filter(t -> tagStringList.contains(t.getName()))
                        .forEach(t -> tagStringList.remove(tagStringList.indexOf(t.getName())));
                for (String s : tagStringList) {
                    Tag tag = new TagImpl();
                    tag.setName(s);
                    contact.getTags().add(tag);
                }
            }

            String phoneNumberInput = req.getParameter("phoneNumber");
            if (!phoneNumberInput.equals("")) {
                int phoneType = Integer.parseInt(req.getParameter("phoneType"));
                Phone oldPhone = ((List<Phone>)contact.getPhones()).get(0);
                if ((oldPhone.getPhoneType() != phoneType) || (!oldPhone.getNumber().equals(phoneNumberInput))) {
                    oldPhone.setPhoneType(phoneType);
                    oldPhone.setNumber(phoneNumberInput);
                }
            }

            String commentInput = req.getParameter("contactComment");
            if (!commentInput.equals("")) {
                Comment oldComment = ((List<Comment>)contact.getComments()).get(0);
                if (!oldComment.getComment().equals(commentInput)) {
                    oldComment.setComment(commentInput);
                    oldComment.setUpdated(date);
                }
            }

            /*String filesInput = req.getParameter("files");
            if (filesInput != null) {
                File file = new FileImpl();
                Path path = Paths.get(filesInput);
                String mimeType = Files.probeContentType(path);
                if (mimeType == null) {
                    mimeType = "unknown/"+filesInput.substring(filesInput.lastIndexOf('.'), filesInput.length());
                }
                //TODO replace dummy
                file.setPath("c:\\files\\" + path);
                file.setMimeType(mimeType);
                file.setCreated(updateDate);
                file.setUpdated(updateDate);
                Collection<File> fileCollection = new ArrayList<>();
                fileCollection.add(file);
                contact.setFiles(fileCollection);
            }*/

            contactDAO.insertOrUpdate(contact);

        }

        resp.sendRedirect("contactlist");
    }

}
