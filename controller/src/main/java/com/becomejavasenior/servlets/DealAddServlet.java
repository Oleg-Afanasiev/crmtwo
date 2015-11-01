package com.becomejavasenior.servlets;

import com.becomejavasenior.*;
import com.becomejavasenior.dealAddClasses.*;
import com.becomejavasenior.impl.*;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;

import org.slf4j.LoggerFactory;


/**
 * Created by oleg on 9/17/15.
 */
@WebServlet(name = "DealAddServlet", urlPatterns = "/crm/dealadd", loadOnStartup = 0)
@MultipartConfig
public class DealAddServlet extends PersistServlet {
    private DaoManager daoManager;
    private DealInputError dealInputError;
    private DealFields dealFields;
    private ContactFields contactFields;
    private CompanyFields companyFields;
    private Collection<ContactFields> savedContacts;
    final private PhoneTypeCollect phoneTypeCollect = new PhoneTypeCollect();

    protected static final org.slf4j.Logger logger = LoggerFactory.getLogger(DealAddServlet.class);

    @Override
    public void init() throws ServletException {
        super.init();

        dealInputError = new DealInputError();
        dealFields = new DealFields();
        contactFields = new ContactFields();
        companyFields = new CompanyFields();
        savedContacts = new ArrayList<>();
    }

    @Override
    protected void doPostInPersistentCtx(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        daoManager = DaoManager.getInstance();
        request.setCharacterEncoding("utf-8");

        dealInputError.reset();
        dealFields.reset();
        contactFields.reset();
        companyFields.reset();
        savedContacts.clear();

        readDealInputedFields(request, dealFields);
        readCompanyInputedFields(request, companyFields);
        readContactInputedFields(request, contactFields);

        if (request.getParameter("saveDeal") != null) {
            doPostSaveDeal(request, response);
        } else if(request.getParameter("saveContact") != null) {
            doPostSaveContact(request, response);
        } else if (request.getParameter("saveCompany") != null) {
            doPostSaveCompany(request, response);
        }
    }

    @Override
    protected void doGetInPersistentCtx(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("forward to dealAdd.jsp");
        User user = (User) req.getSession().getAttribute("activeUser");

        if (!user.getRole().getName().equals("admin")) {
            req.getRequestDispatcher("/jsp/loginError.jsp").forward(req, resp);
        } else {
            daoManager = DaoManager.getInstance();
            req.setAttribute("users", getRangeUsers(0, 100));
            req.setAttribute("dealStatuses", getRangeDealStatuses(0, 100));
            req.setAttribute("contacts", getRangeContacts(0, 100));
            req.setAttribute("companies", getRangeCompanies(0, 100));
            req.setAttribute("phoneTypes", phoneTypeCollect.getPhoneTypes());

            req.setAttribute("dealFields", dealFields);
            req.setAttribute("contactFields", contactFields);
            req.setAttribute("companyFields", companyFields);
            req.setAttribute("dealInputError", dealInputError);
            req.setAttribute("savedContacts", savedContacts);
        }

        req.getRequestDispatcher("/jsp/dealAdd.jsp").forward(req, resp);
        dealFields.reset();
        contactFields.reset();
        companyFields.reset();
        dealInputError.reset();
        savedContacts.clear();
    }

    private void readDealInputedFields(HttpServletRequest request, DealFields dealFields) {
        dealFields.setName(request.getParameter("name"));
        dealFields.setTags(request.getParameter("tags"));
        dealFields.setResponsibleUserId(request.getParameter("responsibleUser"));
        dealFields.setBudget(request.getParameter("budget"));
        dealFields.setStatusId(request.getParameter("status"));
        dealFields.setComments(request.getParameter("comments"));
        dealFields.setCompany(request.getParameter("attached_company_id"), request.getParameter("attached_company_name"));

        String n = request.getParameter("n_attached_contacts");

        int nContacts = (n == null ? 0 : Integer.parseInt(n));

        for (int i = 1; i <= nContacts; i++)
            dealFields.setContact(request.getParameter("attached_contact_id_" + i), request.getParameter("attached_contact_name_" + i));
    }

    private void readContactInputedFields(HttpServletRequest request, ContactFields contactFields) {
        contactFields.setContactName(request.getParameter("contact_name"));
        contactFields.setCompanyId(request.getParameter("contact_company_id"));
        contactFields.setPhoneTypeId(request.getParameter("contact_phone_type_id"));
        contactFields.setPhoneNumber(request.getParameter("contact_phone_number"));
        contactFields.setJobPosition(request.getParameter("contact_job_position"));
        contactFields.setEmail(request.getParameter("contact_email"));
        contactFields.setSkype(request.getParameter("contact_skype"));

        readSavedContacts(request, savedContacts);
    }

    private void readSavedContacts(HttpServletRequest request, Collection<ContactFields> savedContacts) {
        String nSavedContactsReq = request.getParameter("n_saved_contacts");

        if (nSavedContactsReq == null)
            return;

        if (nSavedContactsReq.equals(""))
            return;

        int nSavedContacts = Integer.parseInt(nSavedContactsReq);
        ContactFields savedContact = new ContactFields();

        for (int i = 1; i <= nSavedContacts; i++) {
            savedContact.setContactName(request.getParameter("saved_contact_name_" + i));
            savedContact.setCompanyName(request.getParameter("saved_contact_companyName_" + i));
            savedContact.setCompanyId(request.getParameter("saved_contact_companyId_" + i));
            savedContact.setPhoneTypeId(request.getParameter("saved_contact_phoneTypeId_" + i));
            savedContact.setPhoneTypeName(request.getParameter("saved_contact_phoneTypeName_" + i));
            savedContact.setPhoneNumber(request.getParameter("saved_contact_phoneNumber_" + i));
            savedContact.setJobPosition(request.getParameter("saved_contact_jobPosition_" + i));
            savedContact.setEmail(request.getParameter("saved_contact_email_" + i));
            savedContact.setSkype(request.getParameter("saved_contact_skype_" + i));

            savedContacts.add(savedContact);
        }
    }

    private void readCompanyInputedFields(HttpServletRequest request, CompanyFields companyFields) {
        companyFields.setCompanyName(request.getParameter("company_name"));
        companyFields.setPhoneTypeId(request.getParameter("company_phone_type_id"));
        companyFields.setPhoneNumber(request.getParameter("company_phone_number"));
        companyFields.setEmail(request.getParameter("company_email"));
        companyFields.setAddress(request.getParameter("company_address"));
        companyFields.setWebAddress(request.getParameter("company_web_address"));
    }

    private void doPostSaveDeal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String dealName = getDealName(dealFields.getName());
        Collection<Tag> tags = getTags(dealFields.getTags());
        Long responsibleUserId = getResponsibleUserId(dealFields.getResponsibleUserId());
        BigDecimal budget = getBudget(dealFields.getBudget());
        Long statusId = getStatusId(dealFields.getStatusId());
        Collection<Comment> comments = getComments(dealFields.getComments(), dealName);
        Collection<Company> dealCompanies = getDealCompanies(dealFields.getCompanyId());
        Collection<Contact> dealContacts = getDealContacts(dealFields.getContactsId());
        Collection<File> files = getFilesFromRequest(request);
        Date date = new Date();

        DealDAO dealDAO = daoManager.getDealDAO();
        User user;
        DealStatus dealStatus;
        Deal newDeal;

        if (!dealInputError.isStatusError()){
            user = daoManager.getUserDAO().getById(responsibleUserId);
            dealStatus = daoManager.getDealStatusDAO().getById(statusId);

            newDeal = new DealImpl();
            newDeal.setName(dealName);
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

            response.sendRedirect("/crm/deallist");
            dealFields.reset();
        }
        else
            response.sendRedirect("/crm/dealadd");
    }

    private void doPostSaveContact(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long responsibleUserId = getResponsibleUserId(dealFields.getResponsibleUserId());
        String contactName = getContactName(contactFields.getContactName());
        Long companyId = getCompanyId(contactFields.getCompanyId());
        String jobPosition = getContactJobPosition(contactFields.getJobPosition());
        Collection<Phone> phones = getContactPhones(contactFields.getPhoneTypeId(), contactFields.getPhoneNumber());
        String email = getContactEmail(contactFields.getEmail());
        String skype = getContactSkype(contactFields.getSkype());

        ContactDAO contactDAO = daoManager.getContactDAO();
        Contact newContact = new ContactImpl();
        Company company;
        User user;
        Date date = new Date();

        ContactFields savedContact;
        int phoneTypeId;

        if (!dealInputError.isStatusError()) {
            company = daoManager.getCompanyDAO().getById(companyId);
            user = daoManager.getUserDAO().getById(responsibleUserId);
            newContact.setName(contactName);
            newContact.setCompany(company);
            newContact.setJobPosition(jobPosition);
            newContact.setPhones(phones);
            newContact.setEmail(email);
            newContact.setSkype(skype);
            newContact.setResponsibleUser(user);
            newContact.setCreated(date);
            newContact.setUpdated(date);

            contactDAO.insertOrUpdate(newContact);

            savedContact = (ContactFields) contactFields.clone();
            phoneTypeId = phones.iterator().next().getPhoneType();
            savedContact.setCompanyName(company.getName());
            savedContact.setPhoneTypeName(phoneTypeCollect.getPhoneTypeNameById(phoneTypeId));
            savedContacts.add(savedContact);
            contactFields.reset();
        }

        response.sendRedirect("/crm/dealadd");
    }

    private void doPostSaveCompany(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Long responsibleUserId = getResponsibleUserId(dealFields.getResponsibleUserId());
        String companyName = getCompanyName(companyFields.getCompanyName());
        Collection<Phone> phones = getCompanyPhones(companyFields.getPhoneTypeId(), companyFields.getPhoneNumber());
        String email = getCompanyEmail(companyFields.getEmail());
        String url = getCompanyURL(companyFields.getWebAddress());
        String address = getCompanyAddress(companyFields.getAddress());

        CompanyDAO companyDAO = daoManager.getCompanyDAO();
        Company newCompany = new CompanyImpl();
        User user;
        Date date = new Date();

        if (!dealInputError.isStatusError()) {
            user = daoManager.getUserDAO().getById(responsibleUserId);
            newCompany.setName(companyName);
            newCompany.setPhones(phones);
            newCompany.setEmail(email);
            newCompany.setAddress(address);
            newCompany.setWebAddress(url);
            newCompany.setResponsibleUser(user);
            newCompany.setCreated(date);
            newCompany.setUpdated(date);

            companyDAO.insertOrUpdate(newCompany);
            companyFields.reset();
        }

        response.sendRedirect("/crm/dealadd");
    }

    //--------------------for deal------------------------------
    private String getDealName(String name) {

        if (name.equals(""))
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

    private Collection<Tag> getTags(String tagsString) {
        String arrTags[] = tagsString.split(" ");
        Collection<Tag> tagList = new ArrayList<>();

        if (tagsString.equals("")) {
            //dealInputError.setError("deal: tags is empty");
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
               dealInputError.setError("deal: tag format error");
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
            dealInputError.setError("deal: unknown responsible user");
            ex.printStackTrace(System.out);
            return 0L;
        }
    }
    
    private BigDecimal getBudget(String budgetInput) {

        BigDecimal budget;

        if (budgetInput.equals("")) {
            budget = new BigDecimal(0);
            dealInputError.setError("deal: budget is empty");
        }
        else {
            try {
                budget = new BigDecimal(budgetInput);
            }
            catch (Exception ex) {
                ex.printStackTrace(System.out);
                budget = new BigDecimal(0);
                dealInputError.setError("deal: budget format");
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
            dealInputError.setError("deal: unknown status");
            statusId = 0L;
        }

        return statusId;
    }

    private Collection<Comment> getComments(String commentsString, String dealName) {
        Collection<Comment> commentList = new ArrayList<>();

        if (commentsString.equals(""))
            return commentList;

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

    private Collection<Company> getDealCompanies(String companyIdInput) {
        Collection<Company> companies = new ArrayList<>();

        if (companyIdInput == null || companyIdInput.equals(""))
            return companies;

        Long companyId = Long.parseLong(companyIdInput);
        Company company;

        if (companyId > 0){
            company = daoManager.getCompanyDAO().getById(companyId);
            companies.add(company);
        }

        return companies;
    }

    private Collection<Contact> getDealContacts(Collection<String> contactsIdInput) {
        Collection<Contact> contacts = new ArrayList<>();
        Long contactId;
        Contact contact;

        for (String contactIdInput : contactsIdInput) {
            contactId = Long.parseLong(contactIdInput);

            if (contactId > 0) {
                contact = daoManager.getContactDAO().getById(contactId);
                contacts.add(contact);
            }
        }

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
        int countFiles = Integer.parseInt(request.getParameter("n_uploaded_files"));

        for (int i = 1; i <= countFiles; i++) {
            file = getFile(request.getPart("attached_file_" + i));

            try {
                fileList.add(file);
            } catch (Exception e) {
                e.printStackTrace(System.out);
            }
        }

        return fileList;
    }

    //------------------------for contacts-------------------
    private String getContactName(String nameInput) {
        if (nameInput.equals(""))
            dealInputError.setError("contact: name is empty");

        return nameInput;
    }

    private String getContactJobPosition(String jobPositionInput) {
        if (jobPositionInput.equals(""))
            dealInputError.setError("contact: job position is empty");

        return jobPositionInput;
    }

    private String getContactEmail(String emailInput) {
        if (emailInput.equals(""))
            dealInputError.setError("contact: email is empty");

        if(emailInput.indexOf('@') == -1)
            dealInputError.setError("contact: email is not valid");

        return emailInput;
    }

    private String getContactSkype(String skypeInput) {
        if(skypeInput.equals(""))
            dealInputError.setError("contact: skype is empty");

        return skypeInput;
    }

    private Long getCompanyId(String idInput) {
        Long id;
        try {
            id = Long.parseLong(idInput);
            if (id <= 0)
                dealInputError.setError("contact: company is not selected");
            return Long.parseLong(idInput);
        }
        catch (Exception ex) {
            dealInputError.setError("contact: company is not selected");
            ex.printStackTrace(System.out);
            return 0L;
        }
    }

    private Collection<Phone> getContactPhones(String phoneTypeId, String phoneNumber) {
        Collection<Phone> phones = new ArrayList<>();
        int typeId;
        Phone phone;

        if (phoneNumber.equals("")) {
            dealInputError.setError("contact: phone is empty");
        }
        try {
            typeId = Integer.parseInt(phoneTypeId);
            phone = new PhoneImpl();
            phone.setPhoneType(typeId);
            phone.setNumber(phoneNumber);
            phones.add(phone);
        }
        catch(Exception ex) {
            dealInputError.setError("contact: phone type is unknown");
            return phones;
        }

        return phones;
    }

    //--------------------------for company---------------------------
    private Collection<Phone> getCompanyPhones(String phoneTypeId, String phoneNumber) {
        Collection<Phone> phones = new ArrayList<>();
        int typeId;
        Phone phone;

        if (phoneNumber.equals("")) {
            dealInputError.setError("company: number is empty");
        }
        try {
            typeId = Integer.parseInt(phoneTypeId);
            phone = new PhoneImpl();
            phone.setPhoneType(typeId);
            phone.setNumber(phoneNumber);
        }
        catch(Exception ex) {
            dealInputError.setError("company: unknown phone type");
            return phones;
        }

        return phones;
    }

    private String getCompanyName (String nameInput) {
        if (nameInput.equals(""))
            dealInputError.setError("company: name is empty");

        return nameInput;
    }

    private String getCompanyURL (String urlInput) {
        if (urlInput.equals(""))
            dealInputError.setError("company: url is empty");

        return urlInput;
    }

    private String getCompanyEmail (String emailInput) {
        if (emailInput.equals(""))
            dealInputError.setError("company: email is empty");
        if(emailInput.indexOf('@') == -1)
            dealInputError.setError("company: email is not valid");

        return emailInput;
    }

    private String getCompanyAddress (String addressInput) {
        if (addressInput.equals(""))
            dealInputError.setError("company: address is empty");

        return addressInput;
    }
}
