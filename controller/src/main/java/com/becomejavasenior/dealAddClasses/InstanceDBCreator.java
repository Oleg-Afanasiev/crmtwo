package com.becomejavasenior.dealAddClasses;

import com.becomejavasenior.*;
import com.becomejavasenior.impl.CommentImpl;
import com.becomejavasenior.impl.FileImpl;
import com.becomejavasenior.impl.PhoneImpl;
import com.becomejavasenior.impl.TagImpl;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Created by oleg on 11/2/15.
 */
abstract class InstanceDBCreator {

    protected Collection<Tag> parseTags(String tagsString) {
        String arrTags[] = tagsString.split(" ");
        Collection<Tag> tagList = new ArrayList<>();

        if (tagsString.equals("")) {
            return tagList;
        }

        for (int i = 0; i < arrTags.length; i++) {
            Tag currentTag = new TagImpl();
            currentTag.setName(arrTags[i]);

            try {
                tagList.add(currentTag);
            } catch (Exception e) {
                e.printStackTrace(System.out);
            }
        } // for

        return tagList;
    }

    protected Long parseId(String idStr) {

        try {
            return Long.parseLong(idStr);
        }
        catch (Exception ex) {
            ex.printStackTrace(System.out);
            return 0L;
        }
    }

    protected BigDecimal parseBudget(String budgetInput) {

        BigDecimal budget;

        try {
            budget = new BigDecimal(budgetInput);
        }
        catch (Exception ex) {
            ex.printStackTrace(System.out);
            budget = new BigDecimal(0);
        }

        return budget;
    }

    protected Collection<Company> parseCompanies(String companyIdInput) {
        Collection<Company> companies = new ArrayList<>();

        if (companyIdInput == null || companyIdInput.equals(""))
            return companies;

        Long companyId = Long.parseLong(companyIdInput);

        if (companyId > 0){
            Company company;
            CompanyDAO companyDAO = DaoManager.getInstance().getCompanyDAO();
            company = companyDAO.getById(companyId);
            companies.add(company);
        }

        return companies;
    }

    protected Collection<Contact> parseContacts(Collection<String> contactsIdInput) {
        Collection<Contact> contacts = new ArrayList<>();
        Long contactId;
        Contact contact;
        ContactDAO contactDAO = DaoManager.getInstance().getContactDAO();

        for (String contactIdInput : contactsIdInput) {
            contactId = Long.parseLong(contactIdInput);

            if (contactId > 0) {
                contact = contactDAO.getById(contactId);
                contacts.add(contact);
            }
        }

        return contacts;
    }

    protected Collection<Comment> parseComments(String commentsString, String instance,  String instanceName) {
        Collection<Comment> commentList = new ArrayList<>();

        if (commentsString.equals(""))
            return commentList;

        Comment comment = new CommentImpl();

        Date date = new Date();

        comment.setName("comment for " + instance + " \"" + instanceName + "\"");
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

    protected Collection<Phone> parsePhones(String phoneTypeId, String phoneNumber) {
        Collection<Phone> phones = new ArrayList<>();
        int typeId;
        Phone phone;

        if (phoneNumber.equals("")) {
            return phones;
        }

        try {
            typeId = Integer.parseInt(phoneTypeId);
            phone = new PhoneImpl();
            phone.setPhoneType(typeId);
            phone.setNumber(phoneNumber);
            phones.add(phone);
        }
        catch(Exception ex) {
            ex.printStackTrace(System.out);
            return phones;
        }

        return phones;
    }

    protected Collection<File> parseFiles(Collection<Part> partFiles) {
        Collection<File> fileList = new ArrayList<>();
        File file;

        for (Part filePart : partFiles) {
            file = getFile(filePart);

            try {
                fileList.add(file);
            } catch (Exception e) {
                e.printStackTrace(System.out);
            }
        }

        return fileList;
    }

    private File getFile(Part filePart) {
        File file = new FileImpl();
        Date date = new Date();

        String fileName = getFileName(filePart);
        MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();
        String mimeType = mimeTypesMap.getContentType(fileName);

        try {
            InputStream fileContent = filePart.getInputStream();
            ArrayList<Byte> data = new ArrayList<>();
            int c;

            while ((c = fileContent.read()) > -1) {
                data.add((byte)c);
            }
            byte[] content = new byte[data.size()];

            for (int i = 0; i < content.length; i++) {
                content[i] = data.get(i);
            }

            file.setMimeType(mimeType);
            file.setName("c:\\files\\" + fileName);
            file.setContent(content);
            file.setCreated(date);
            file.setUpdated(date);

        } catch(IOException ex) {
            ex.printStackTrace(System.out);
        }

        return file;
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
}
