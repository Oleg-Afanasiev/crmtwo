package com.becomejavasenior;

import com.becomejavasenior.impl.*;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.swing.plaf.synth.SynthTextAreaUI;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;

import org.apache.log4j.Logger;


/**
 * Created by oleg on 9/17/15.
 */

@WebServlet(name = "DealAddServlet", urlPatterns = "/dealadd", loadOnStartup = 0)
@MultipartConfig
public class DealAddServlet extends PersistServlet {
    private DaoManager daoManager;
    private Logger logger = Logger.getLogger(DealAddServlet.class);
    private DealInputError dealInputError;
    private DealInputValues dealInputValues;

    @Override
    public void init() throws ServletException {
        super.init();

        dealInputError = new DealInputError();
        dealInputValues = new DealInputValues();
    }

    @Override
    protected void doPostInPersistentCtx(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        daoManager = DaoManager.getInstance();

        request.setCharacterEncoding("UTF-8");

        String nameInput = request.getParameter("name");
        String tagsInput = request.getParameter("tags");
        String responsibleUserIdInput = request.getParameter("responsibleUser");
        String budgetInput = request.getParameter("budget");
        String statusIdInput = request.getParameter("status");
        String commentsInput = request.getParameter("comments");
        String companyIdInput = request.getParameter("company");
        Collection<String> contactsIdInput = getContactsIdInput(request);

        System.out.println("contactsIdInput: " + contactsIdInput);

        dealInputError.reset();
        dealInputValues.reset();

        String name = getName(nameInput);
        Collection<Tag> tags = getTags(tagsInput);
        Long responsibleUserId = getResponsibleUserId(responsibleUserIdInput);
        BigDecimal budget = getBudget(budgetInput);
        Long statusId = getStatusId(statusIdInput);
        Collection<Comment> comments = getComments(commentsInput, name);
        Collection<Company> newNonDealCompanies = getNewNonDealCompanies(request, companyIdInput, responsibleUserId); // before working ajax
        Collection<Company> dealCompanies = getDealCompanies(request, companyIdInput, responsibleUserId);
        Collection<Contact> newNonDealContacts = getNewNonDealContacts(request, contactsIdInput, responsibleUserId); // before working ajax
        Collection<Contact> dealContacts = getDealContacts(request, contactsIdInput, responsibleUserId);
        Collection<File> files = getFilesFromRequest(request);

        Date date = new Date();

        CompanyDAO companyDAO = daoManager.getCompanyDAO();
        ContactDAO contactDAO = daoManager.getContactDAO();
        DealDAO dealDAO = daoManager.getDealDAO();
        User user;
        DealStatus dealStatus;
        Deal newDeal;

        dealInputValues.setName(nameInput);
        dealInputValues.setTags(tagsInput);
        dealInputValues.setResponsibleUserId(responsibleUserId);
        dealInputValues.setBudget(budget);
        dealInputValues.setStatusId(statusId);
        dealInputValues.setComments(commentsInput);
        //dealInputValues.setDealContacts(dealContacts); contacts data was set into "getDealContacts" and "getNewNonDealContacts"

        if (!dealInputError.isStatusError()){
            user = daoManager.getUserDAO().getById(responsibleUserId);
            dealStatus = daoManager.getDealStatusDAO().getById(statusId);

            newDeal = new DealImpl();
            newDeal.setName(name);
            newDeal.setTags(tags);
            newDeal.setBudget(budget);
            newDeal.setResponsibleUser(user);
            newDeal.setDealStatus(dealStatus);
            newDeal.setComments(comments);
            newDeal.setCompanies(dealCompanies);
            newDeal.setContacts(dealContacts);
            newDeal.setFiles(files);
            newDeal.setCreated(date);
            newDeal.setUpdated(date);

            dealDAO.insertOrUpdate(newDeal);

            for (Company newCompany : newNonDealCompanies) {
                companyDAO.insertOrUpdate(newCompany);
            }

            for (Contact newContact : newNonDealContacts) {
                contactDAO.insertOrUpdate(newContact);
            }

            dealInputValues.reset();
            response.sendRedirect("/deallist");
        }
        else
            response.sendRedirect("/dealadd");
    }

    @Override
    protected void doGetInPersistentCtx(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            daoManager = DaoManager.getInstance();
            request.setAttribute("users", getRangeUsers(0, 50));
            request.setAttribute("dealStatuses", getRangeDealStatuses(0, 50));
            request.setAttribute("contacts", getRangeContacts(0, 50));
            request.setAttribute("companies", getRangeCompanies(0, 50));
            request.setAttribute("phoneTypes", getPhoneTypes());

            request.setAttribute("nameInput", dealInputValues.getName());
            request.setAttribute("tagsInput", dealInputValues.getTags());
            request.setAttribute("budget", dealInputValues.getBudget());
            request.setAttribute("responsibleUserIdInput", dealInputValues.getResponsibleUserId());
            request.setAttribute("statusId", dealInputValues.getStatusId());
            request.setAttribute("comments", dealInputValues.getComments());

            request.setAttribute("dealContactsFields", dealInputValues.getDealContactsFields());
            request.setAttribute("newContactsFields", dealInputValues.getNewContactsFields());

            request.setAttribute("dealCompanyFields", dealInputValues.getDealCompanyFields());
            request.setAttribute("newCompaniesFields", dealInputValues.getNewCompaniesFields());

            request.setAttribute("dealInputError", dealInputError);

        } catch(DAOException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("dealAdd.jsp").forward(request, response);
    }

    private String getName(String name) {

        if (name == "")
            dealInputError.setError("name");

        return name;
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

    private Collection<Tag> getTags(String tagsString) {
        String arrTags[] = tagsString.split(" ");
        Collection<Tag> tagList = new ArrayList<>();

        if (tagsString == "") {
            dealInputError.setError("tags empty");
            return tagList;
        }


        for (int i = 0; i < arrTags.length; i++) {
            if (arrTags[i].charAt(0) == '#') {
                Tag currentTag = new TagImpl();
                currentTag.setName(arrTags[i]);

                try {
                    tagList.add(currentTag);
                } catch (Exception e) {
                    e.printStackTrace(System.out);
                }
            }
            else {
               dealInputError.setError("tags #");
                break;
            }
        } // for

        return tagList;
    }

    private Long getResponsibleUserId(String idInput) {

        try {
            return Long.parseLong(idInput);
        }
        catch (Exception ex) {
            dealInputError.setError("responsible user");
            ex.printStackTrace(System.out);
            return 0L;
        }
    }
    
    private BigDecimal getBudget(String budgetInput) {

        BigDecimal budget;

        if (budgetInput == "") {
            budget = new BigDecimal(0);
            dealInputError.setError("budget empty");
        }
        else {
            try {
                budget = new BigDecimal(budgetInput);
            }
            catch (Exception ex) {
                ex.printStackTrace(System.out);
                budget = new BigDecimal(0);
                dealInputError.setError("budget format");
            }
        }

        return budget;
    }

    private Long getStatusId(String statusInputId) {
        Long statusId;

        try {
            statusId = Long.parseLong(statusInputId);
        }
        catch (Exception ex) {
            ex.printStackTrace(System.out);
            dealInputError.setError("status");
            statusId = 0L;
        }

        return statusId;
    }

    private Collection<Comment> getComments(String commentsString, String dealName) {
        Collection<Comment> commentList = new ArrayList<>();
        Comment comment = new CommentImpl();

        Date date = new Date();

        comment.setName("comment for deal \"" + dealName + "\"");
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

    private Collection<Company> getNewNonDealCompanies(HttpServletRequest request, String companyIdInput, long responsibleUserId) {
        Collection<Company> companies = new ArrayList<>();
        DealCompanyFields companyFields;

        int count = Integer.parseInt(request.getParameter("count_new_companies"));
        String pseudoId;

        Company company;

        for (int i = 1; i <= count; i++) {
            pseudoId = request.getParameter("company_pseudoId_" + i);

            companyFields = new DealCompanyFields();

            companyFields.setCompanyName(request.getParameter("company_name_" + i));
            companyFields.setCompanyId(pseudoId);
            companyFields.setPhoneTypeId(request.getParameter("company_phoneTypeId_" + i));
            companyFields.setPhoneNumber(request.getParameter("company_phoneNumber_" + i));
            companyFields.setEmail(request.getParameter("company_email_" + i));
            companyFields.setWebAddress(request.getParameter("company_webAddress_" + i));
            companyFields.setAddress(request.getParameter("company_address_" + i));

            dealInputValues.setNewCompaniesFields(companyFields);

            if (pseudoId.equals(companyIdInput))
                continue;

            if (companyFields.checkFields()) {
                company = companyFields.createNewCompany(daoManager, responsibleUserId);
                companies.add(company);
            }
        }
        return companies;
    }

    private Collection<Company> getDealCompanies(HttpServletRequest request, String companyIdInput, long responsibleUserId) {
        Collection<Company> companies = new ArrayList<>();
        Long companyId = Long.parseLong(companyIdInput);
        DealCompanyFields companyFields = new DealCompanyFields();
        int count;
        String pseudoId;
        Company company;

        if (companyId > 0){
            company = daoManager.getCompanyDAO().getById(companyId);
            companies.add(company);
            dealInputValues.setDealCompanyFields(company);
        }
        else if(companyId < 0){
            count = Integer.parseInt(request.getParameter("count_new_companies"));

            for (int i = 1; i <= count; i++) {
                pseudoId = request.getParameter("company_pseudoId_" + i);
                if (pseudoId.equals(companyIdInput)) {
                    companyFields.setCompanyName(request.getParameter("company_name_" + i));
                    companyFields.setCompanyId(pseudoId);
                    companyFields.setPhoneTypeId(request.getParameter("company_phoneTypeId_" + i));
                    companyFields.setPhoneNumber(request.getParameter("company_phoneNumber_" + i));
                    companyFields.setEmail(request.getParameter("company_email_" + i));
                    companyFields.setWebAddress(request.getParameter("company_webAddress_" + i));
                    companyFields.setAddress(request.getParameter("company_address_" + i));

                    dealInputValues.setDealCompanyFields(companyFields);

                    if (companyFields.checkFields()) {
                        company = companyFields.createNewCompany(daoManager, responsibleUserId);
                        companies.add(company);
                    }

                    break;
                } // if
            } // for
        } // else

        return companies;
    }

    private Collection<String> getContactsIdInput(HttpServletRequest request) {
        Collection<String> contactsId = new ArrayList<>();

        int count = Integer.parseInt(request.getParameter("count_dock_contacts"));

        for (int i = 1; i <= count; i++) {
            contactsId.add(request.getParameter("dock_contact_" + i));
        }

        return contactsId;
    }

    private Collection<Contact> getNewNonDealContacts(HttpServletRequest request, Collection<String> contactsIdInput, long responsibleUserId) {
        Collection<Contact> contacts = new ArrayList<>();
        DealContactFields contactFields;

        int count = Integer.parseInt(request.getParameter("count_new_contacts"));
        String pseudoId;
        Contact contact;

        for (int i = 1; i <= count; i++) {
            pseudoId = request.getParameter("contact_pseudoId_" + i);

            contactFields = new DealContactFields();

            contactFields.setCompanyId(request.getParameter("contact_companyId_" + i));
            contactFields.setContactName(request.getParameter("contact_name_" + i));
            contactFields.setContactId(pseudoId);
            contactFields.setEmail(request.getParameter("contact_email_" + i));
            contactFields.setJobPosition(request.getParameter("contact_jobPosition_" + i));
            contactFields.setPhoneNumber(request.getParameter("contact_phoneNumber_" + i));
            contactFields.setPhoneTypeId(request.getParameter("contact_phoneTypeId_" + i));
            contactFields.setSkype(request.getParameter("contact_skype_" + i));

            dealInputValues.setNewContactsFields(contactFields);

            if (contactsIdInput.contains(pseudoId))
                continue;

            if (contactFields.checkFields()) {
                contact = contactFields.createNewContact(daoManager, responsibleUserId, dealInputValues);
                contacts.add(contact);
            }
        }

        return contacts;
    }

    private Collection<Contact> getDealContacts(HttpServletRequest request, Collection<String> contactsIdInput, long responsibleUserId) {
        Collection<Contact> contacts = new ArrayList<>();
        Long contactId;
        DealContactFields contactFields;
        int count;
        String pseudoId;
        Contact contact;

        for (String contactIdInput : contactsIdInput) {
            contactId = Long.parseLong(contactIdInput);

            if (contactId > 0) {
                contact = daoManager.getContactDAO().getById(contactId);
                contacts.add(contact);
                dealInputValues.setDealContactFields(contact);
            }
            else if (contactId < 0) {
                count = Integer.parseInt(request.getParameter("count_new_contacts"));

                for (int i = 1; i <= count; i++) {
                    pseudoId = request.getParameter("contact_pseudoId_" + i);
                    if (pseudoId.equals(contactIdInput)) {
                        contactFields = new DealContactFields();

                        contactFields.setCompanyId(request.getParameter("contact_companyId_" + i));
                        contactFields.setContactName(request.getParameter("contact_name_" + i));
                        contactFields.setContactId(pseudoId);
                        contactFields.setEmail(request.getParameter("contact_email_" + i));
                        contactFields.setJobPosition(request.getParameter("contact_jobPosition_" + i));
                        contactFields.setPhoneNumber(request.getParameter("contact_phoneNumber_" + i));
                        contactFields.setPhoneTypeId(request.getParameter("contact_phoneTypeId_" + i));
                        contactFields.setSkype(request.getParameter("contact_skype_" + i));

                        dealInputValues.setDealContactFields(contactFields);

                        if (contactFields.checkFields()) {
                            contact = contactFields.createNewContact(daoManager, responsibleUserId, dealInputValues);
                            contacts.add(contact);

                            break;
                        } // end if
                    } // end if
                } // end for
            } // end else
        } // end for

        return contacts;
    }

    private String getFileName(Part part) {

        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    private File getFile(Part filePart) throws ServletException, IOException {
        File file = new FileImpl();
        Date date = new Date();

        String fileName = getFileName(filePart);
        MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();
        String mimeType = mimeTypesMap.getContentType(fileName);

        InputStream fileContent = filePart.getInputStream();
        byte[] content;
        ArrayList<Byte> data = new ArrayList<>();
        int c;

        while ((c = fileContent.read()) > -1) {
            data.add((byte)c);
        }

        content = new byte[data.size()];

        for (int i = 0; i < content.length; i++) {
            content[i] = data.get(i);
        }

        file.setMimeType(mimeType);
        file.setName("c:\\files\\" + fileName);
        file.setContent(content);
        file.setCreated(date);
        file.setUpdated(date);

        return file;
    }

    private Collection<File> getFilesFromRequest(HttpServletRequest request) throws ServletException, IOException {
        Collection<File> fileList = new ArrayList<>();
        File file;
        int countFiles = Integer.parseInt(request.getParameter("count_uploaded_files"));

        for (int i = 1; i <= countFiles; i++) {
            file = getFile(request.getPart("file_input_" + i));

            try {
                fileList.add(file);
            } catch (Exception e) {
                e.printStackTrace(System.out);
            }
        }

        return fileList;
    }
}
