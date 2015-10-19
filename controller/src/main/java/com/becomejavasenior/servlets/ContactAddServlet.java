package com.becomejavasenior.servlets;

import com.becomejavasenior.*;
import com.becomejavasenior.impl.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * ContactAdd servlet
 *
 * @author  Andrey Radionov <andyomsk@gmail.com>
 * @version 0.5
 */

@WebServlet(name = "ContactAddServlet", urlPatterns = "/crm/contactadd", loadOnStartup = 0)
@MultipartConfig(maxFileSize = 102400)
public class ContactAddServlet extends PersistServlet {
    protected static final Logger logger = LoggerFactory.getLogger(ContactAddServlet.class);

    @Override
    protected void doGetInPersistentCtx(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("forward to contactAdd.jsp");
        User user = (User) req.getSession().getAttribute("activeUser");
        if (!user.getRole().getName().equals("admin")) {
            req.getRequestDispatcher("/jsp/loginError.jsp").forward(req, resp);
        } else {
            DaoManager daoManager = DaoManager.getInstance();

            UserDAO userDAO = daoManager.getUserDAO();
            Collection<User> users = userDAO.getRange(0, 100);
            req.setAttribute("users", users);

            CompanyDAO companyDAO = daoManager.getCompanyDAO();
            Collection<Company> companies = companyDAO.getRange(0, 100);
            req.setAttribute("companies", companies);

            req.getRequestDispatcher("/jsp/contactAdd.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPostInPersistentCtx(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("creating new contact");
        Date newDate = new Date();

        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("contact-name");
        String jobPosition = req.getParameter("job-position");
        String email = req.getParameter("email");
        String skype = req.getParameter("skype");
        if (!name.matches("[\\p{L}\\s]{1,30}") ||
            !jobPosition.matches("[\\p{L}\\s]{1,30}") ||
            !email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9._-]+\\.[a-z]{2,3}$") ||
            !skype.matches("[A-Za-z._0-9]{1,30}")
                ) {
            resp.sendRedirect("/crm/contactlist");
            return;
        }

        Contact contact = new ContactImpl();
        contact.setName(name);
        contact.setJobPosition(jobPosition);
        contact.setEmail(email);
        contact.setSkype(skype);
        contact.setCreated(newDate);
        contact.setUpdated(newDate);

        DaoManager daoManager = DaoManager.getInstance();

        String companyIdInput = req.getParameter("company-id");
        if ((!companyIdInput.equals("") || !companyIdInput.isEmpty()) && companyIdInput.matches("[0-9]+")) {
            CompanyDAO companyDAO = daoManager.getCompanyDAO();
            Company company = companyDAO.getById(Integer.parseInt(companyIdInput));
            contact.setCompany(company);
        }

        UserDAO userDAO = daoManager.getUserDAO();
        User user = userDAO.getById(Integer.parseInt(req.getParameter("responsible-user-id")));
        contact.setResponsibleUser(user);

        String tagsInput = req.getParameter("tags");
        if ((!tagsInput.equals("") || !tagsInput.isEmpty()) && tagsInput.matches("(#[\\p{L}0-9]+\\s*)+")) {
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

        String phoneNumberInput = req.getParameter("phone-number");
        if ((!phoneNumberInput.equals("") || !phoneNumberInput.isEmpty()) && phoneNumberInput.matches("\\+[0-9]{12}")) {
            Phone phone = new PhoneImpl();
            phone.setNumber(phoneNumberInput);
            phone.setPhoneType(Integer.parseInt(req.getParameter("phone-type")));
            Collection<Phone> phoneCollection = new ArrayList<>();
            phoneCollection.add(phone);
            contact.setPhones(phoneCollection);
        }

        String commentInput = req.getParameter("contact-comment");
        if (!commentInput.equals("") || !commentInput.isEmpty()) {
            Comment comment = new CommentImpl();
            comment.setName("Contact: " + contact.getName() + " create comment");
            comment.setComment(commentInput);
            comment.setCreated(newDate);
            comment.setUpdated(newDate);
            List<Comment> commentList = new ArrayList<>();
            commentList.add(comment);
            contact.setComments(commentList);
        }

        InputStream inputStream = null;
        List<File> fileList = new ArrayList<>();
        for (Part filePart : req.getParts()) {
            if (filePart.getName().equals("files")) {
                File newFile = new FileImpl();
                inputStream = filePart.getInputStream();

                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                int read;
                byte[] data = new byte[102400];

                while ((read = inputStream.read(data, 0, data.length)) != -1) {
                    buffer.write(data, 0, read);
                }
                inputStream.close();

                //Servlet 3.1 has method(filePart.getSubmittedFileName());
                if (buffer.size()>0) {
                    newFile.setName(getFileName(filePart));
                    newFile.setMimeType(filePart.getContentType());
                    newFile.setCreated(newDate);
                    newFile.setUpdated(newDate);
                    newFile.setContent(buffer.toByteArray());
                    fileList.add(newFile);
                }
            }
        }
        if (fileList.size() > 0) {
            contact.setFiles(fileList);
        }

        ContactDAO contactDAO = daoManager.getContactDAO();
        contactDAO.insertOrUpdate(contact);

        resp.sendRedirect("/crm/contactlist");
    }

    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length()-1);
            }
        }
        return "";
    }
}
