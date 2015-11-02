package com.becomejavasenior.dealAddClasses;

import com.becomejavasenior.Contact;
import com.becomejavasenior.Phone;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by oleg on 11/2/15.
 */
public class DataContainer {
    private CompanyFields companyFields;
    private ContactFields contactFields;
    private DealFields dealFields;
    private Collection<ContactFields> savedContacts;

    public DataContainer() {
        companyFields = new CompanyFields();
        contactFields = new ContactFields();
        dealFields = new DealFields();
        savedContacts = new ArrayList<>();
    }

    public void readAllParameters(HttpServletRequest request) {
        readDealFields(request);
        readContactFields(request);
        readCompanyFields(request);
    }

    public void addSavedContacts(Contact savedContact) {
        ContactFields savedContactFields = new ContactFields();
        Collection<Phone> phones = savedContact.getPhones();
        PhoneTypeCollect phoneTypeCollect = new PhoneTypeCollect();
        int phoneTypeId;

        contactFields.setContactName(savedContact.getName());
        contactFields.setCompanyId(savedContact.getCompany().getId().toString());
        contactFields.setCompanyName(savedContact.getCompany().getName());
        contactFields.setJobPosition(savedContact.getJobPosition());
        contactFields.setEmail(savedContact.getEmail());
        contactFields.setSkype(savedContact.getSkype());

        if (phones.size() > 0) {
            phoneTypeId = phones.iterator().next().getPhoneType();
            contactFields.setPhoneTypeId(Integer.toString(phoneTypeId));
            contactFields.setPhoneTypeName(phoneTypeCollect.getPhoneTypeNameById(phoneTypeId));
            contactFields.setPhoneNumber(phones.iterator().next().getNumber());
        }

        savedContacts.add(savedContactFields);
    }

    public void clearAll() {
        dealFields.clear();
        contactFields.clear();
        companyFields.clear();
        savedContacts.clear();
    }

    public void dealFieldsClear() {
        dealFields.clear();
    }

    public void contactFieldsClear() {
        contactFields.clear();
    }

    public void companyFieldsClear() {
        companyFields.clear();
    }

    public CompanyFields getCompanyFields() {
        return this.companyFields;
    }

    public ContactFields getContactFields() {
        return this.contactFields;
    }

    public DealFields getDealFields() {
        return this.dealFields;
    }

    public Collection<ContactFields> getSavedContacts() {
        return this.savedContacts;
    }

    private void readDealFields(HttpServletRequest request) {
        dealFields.setName(request.getParameter("name"));
        dealFields.setTags(request.getParameter("tags"));
        dealFields.setResponsibleUserId(request.getParameter("responsibleUser"));
        dealFields.setBudget(request.getParameter("budget"));
        dealFields.setStatusId(request.getParameter("status"));
        dealFields.setComments(request.getParameter("comments"));
        dealFields.setCompany(request.getParameter("attached_company_id"), request.getParameter("attached_company_name"));

        readAttachedContacts(request);
        readFiles(request);
    }

    private void readAttachedContacts(HttpServletRequest request) {
        String n = request.getParameter("n_attached_contacts");

        int nContacts = (n == null ? 0 : Integer.parseInt(n));

        for (int i = 1; i <= nContacts; i++)
            dealFields.addContact(request.getParameter("attached_contact_id_" + i), request.getParameter("attached_contact_name_" + i));
    }

    private void readContactFields(HttpServletRequest request) {
        contactFields.setContactName(request.getParameter("contact_name"));
        contactFields.setCompanyId(request.getParameter("contact_company_id"));
        contactFields.setPhoneTypeId(request.getParameter("contact_phone_type_id"));
        contactFields.setPhoneNumber(request.getParameter("contact_phone_number"));
        contactFields.setJobPosition(request.getParameter("contact_job_position"));
        contactFields.setEmail(request.getParameter("contact_email"));
        contactFields.setSkype(request.getParameter("contact_skype"));

        readSavedContacts(request);
    }

    private void readSavedContacts(HttpServletRequest request) {
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

    private void readCompanyFields(HttpServletRequest request) {
        companyFields.setCompanyName(request.getParameter("company_name"));
        companyFields.setPhoneTypeId(request.getParameter("company_phone_type_id"));
        companyFields.setPhoneNumber(request.getParameter("company_phone_number"));
        companyFields.setEmail(request.getParameter("company_email"));
        companyFields.setAddress(request.getParameter("company_address"));
        companyFields.setWebAddress(request.getParameter("company_web_address"));
    }

    private void readFiles(HttpServletRequest request)  {
        int countFiles = Integer.parseInt(request.getParameter("n_uploaded_files"));
        for (int i = 1; i <= countFiles; i++) {
            try {
                dealFields.addPartFile(request.getPart("attached_file_" + i));
            } catch(Exception ex) {
                ex.printStackTrace(System.out);
            }

        }
    }
}
