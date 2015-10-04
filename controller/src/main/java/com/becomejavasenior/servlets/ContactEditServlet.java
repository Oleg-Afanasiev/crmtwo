package com.becomejavasenior.servlets;

import com.becomejavasenior.*;
import com.becomejavasenior.impl.CommentImpl;
import com.becomejavasenior.impl.FileImpl;
import com.becomejavasenior.impl.PhoneImpl;
import com.becomejavasenior.impl.TagImpl;
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
import java.util.*;

/**
 * ContactEdit servlet
 *
 * @author  Andrey Radionov <andyomsk@gmail.com>
 * @version 0.3
 */

@WebServlet(name = "ContactEditServlet", urlPatterns = "/crm/contactedit", loadOnStartup = 0)
@MultipartConfig(maxFileSize = 102400)
public class ContactEditServlet extends PersistServlet {
    protected static final Logger logger = LoggerFactory.getLogger(ContactEditServlet.class);

    @Override
    protected void doGetInPersistentCtx(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("forward to contactAdd.jsp");
        User user = (User) req.getSession().getAttribute("activeUser");
        if (!user.getRole().getName().equals("admin")) {
            req.getRequestDispatcher("/jsp/loginError.jsp").forward(req, resp);
        } else {
            req.setCharacterEncoding("UTF-8");
            int id = Integer.parseInt(req.getParameter("id"));

            DaoManager daoManager = DaoManager.getInstance();

            ContactDAO contactDAO = daoManager.getContactDAO();
            Contact contact;
            try {
                contact = contactDAO.getById(id);
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
                req.getRequestDispatcher("/jsp/contactEdit.jsp").forward(req, resp);
            } catch (DAOException e) {
                logger.debug(e.getMessage() + "forward to contactList.jsp");
                resp.sendRedirect("/crm/contactlist");
            }

        }
    }

    @Override
    protected void doPostInPersistentCtx(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        DaoManager daoManager = DaoManager.getInstance();
        ContactDAO contactDAO = daoManager.getContactDAO();
        Long id = Long.parseLong(req.getParameter("contact-id"));

        if (req.getParameter("contact-delete") != null) {
            logger.debug("delete contact with id= " + id);
            contactDAO.delete(id);
        } else if (req.getParameter("file-delete") != null) {
            Long fileId = Long.parseLong(req.getParameter("file-id"));
            logger.debug("delete file with id= " + id);
            Contact contact = contactDAO.getById(id);
            Collection<File> fileContactCollection = contact.getFiles();
            Iterator<File> fileContactIterator = fileContactCollection.iterator();
            while (fileContactIterator.hasNext()) {
                File f = fileContactIterator.next();
                if (f.getId().equals(fileId)) {
                    fileContactIterator.remove();
                }
            }

            contactDAO.insertOrUpdate(contact);
            resp.sendRedirect(req.getRequestURI() + "?id=" + id);
            return;
        }
        else {
            logger.debug("editing contact with id= " + id);
            Date newDate = new Date();

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

            Contact contact = contactDAO.getById(id);

            contact.setName(name);
            contact.setJobPosition(jobPosition);
            contact.setEmail(email);
            contact.setSkype(skype);
            contact.setUpdated(newDate);

            String companyIdInput = req.getParameter("company-id");
            if ((!companyIdInput.equals("") || !companyIdInput.isEmpty()) && companyIdInput.matches("[0-9]+")) {
                int companyId = Integer.parseInt(companyIdInput);
                if (contact.getCompany() == null || contact.getCompany().getId() != companyId) {
                    CompanyDAO companyDAO = daoManager.getCompanyDAO();
                    Company company = companyDAO.getById(companyId);
                    contact.setCompany(company);
                }
            }

            int userId = Integer.parseInt(req.getParameter("responsible-user-id"));
            if (contact.getResponsibleUser().getId() != userId) {
                UserDAO userDAO = daoManager.getUserDAO();
                User user = userDAO.getById(userId);
                contact.setResponsibleUser(user);
            }

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
                int phoneType = Integer.parseInt(req.getParameter("phone-type"));
                if (contact.getPhones() == null) {
                    Phone phone = new PhoneImpl();
                    phone.setNumber(phoneNumberInput);
                    phone.setPhoneType(phoneType);
                    Collection<Phone> phoneCollection = new ArrayList<>();
                    phoneCollection.add(phone);
                    contact.setPhones(phoneCollection);
                } else {
                    Phone oldPhone = ((List<Phone>) contact.getPhones()).get(0);
                    if ((oldPhone.getPhoneType() != phoneType) || (!oldPhone.getNumber().equals(phoneNumberInput))) {
                        oldPhone.setPhoneType(phoneType);
                        oldPhone.setNumber(phoneNumberInput);
                    }
                }
            }

            String commentInput = req.getParameter("contact-comment");
            if (!commentInput.equals("") || !commentInput.isEmpty()) {
                if (contact.getComments() == null) {
                    Comment comment = new CommentImpl();
                    comment.setName("Contact: " + contact.getName() + " create comment");
                    comment.setComment(commentInput);
                    comment.setCreated(newDate);
                    comment.setUpdated(newDate);
                    List<Comment> commentList = new ArrayList<>();
                    commentList.add(comment);
                    contact.setComments(commentList);
                } else {
                    Comment oldComment = ((List<Comment>) contact.getComments()).get(0);
                    if (!oldComment.getComment().equals(commentInput)) {
                        oldComment.setComment(commentInput);
                        oldComment.setUpdated(newDate);
                    }
                }

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

                    newFile.setName(getFileName(filePart));
                    newFile.setMimeType(filePart.getContentType());
                    newFile.setCreated(newDate);
                    newFile.setUpdated(newDate);
                    newFile.setContent(buffer.toByteArray());
                    fileList.add(newFile);
                }
            }
            if (fileList.size() > 0) {
                if (contact.getFiles() == null) {
                    contact.setFiles(fileList);
                } else {
                    contact.getFiles().addAll(fileList);
                }
            }

            contactDAO.insertOrUpdate(contact);

        }

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
