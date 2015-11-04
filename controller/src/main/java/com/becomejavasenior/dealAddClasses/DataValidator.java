package com.becomejavasenior.dealAddClasses;

import java.math.BigDecimal;

/**
 * Created by oleg on 11/2/15.
 */
public class DataValidator {

    private ErrorsContainer errorList;

    public DataValidator() {
        errorList = new ErrorsContainer();
    }

    public ErrorsContainer getErrorList() {
        return errorList;
    }

    public boolean isValidDealFields(DealFields dealFields) {
        errorList.reset();
        if (isEmpty(dealFields.getName())) {
            errorList.setError("deal: incorrect name");
            return false;
        }

        if (!isValidTags(dealFields.getTags())) {
            errorList.setError("deal: tag format error");
            return false;
        }

        if (!isValidId(dealFields.getResponsibleUserId())) {
            errorList.setError("deal: unknown responsible user");
            return false;
        }

        if (isEmpty(dealFields.getBudget())) {
            errorList.setError("deal: budget is empty");
            return false;
        }

        if (!isValidMoneySum(dealFields.getBudget())) {
            errorList.setError("deal: budget format");
            return false;
        }

        if (!isValidId(dealFields.getStatusId())) {
            errorList.setError("deal: unknown status");
            return false;
        }

        return true;
    }

    public boolean isValidContactFields(ContactFields contactFields) {
        errorList.reset();
        if (!isValidName(contactFields.getContactName())) {
            errorList.setError("contact: name is incorrect");
            return false;
        }

        if (!isValidId(contactFields.getCompanyId())) {
            errorList.setError("contact: company is not selected");
            return false;
        }

        if (isEmpty(contactFields.getJobPosition())) {
            errorList.setError("contact: job position is empty");
            return false;
        }

        if (isEmpty(contactFields.getPhoneNumber())) {
            errorList.setError("contact: phone is empty");
            return false;
        }

        if (!isValidPhoneNumber(contactFields.getPhoneNumber())) {
            errorList.setError("contact: phone is not valid");
            return false;
        }

        if (!isValidId(contactFields.getPhoneTypeId())) {
            errorList.setError("contact: phone type is unknown");
            return false;
        }

        if (isEmpty(contactFields.getEmail())) {
            errorList.setError("contact: email is empty");
            return false;
        }

        if (!isValidEmail(contactFields.getEmail())) {
            errorList.setError("contact: email is not valid");
            return false;
        }

        if (isEmpty(contactFields.getSkype())) {
            errorList.setError("contact: skype is empty");
            return false;
        }


        return true;
    }

    public boolean isValidCompanyFields(CompanyFields companyFields) {
        errorList.reset();
        if(isEmpty(companyFields.getCompanyName())) {
            errorList.setError("company: name is empty");
            return false;
        }

        if (isEmpty(companyFields.getPhoneNumber())) {
            errorList.setError("company: number is empty");
            return false;
        }

        if (!isValidPhoneNumber(companyFields.getPhoneNumber())) {
            errorList.setError("company: number is not valid");
            return false;
        }

        if (!isValidId(companyFields.getPhoneTypeId())) {
            errorList.setError("company: unknown phone type");
            return false;
        }

        if (isEmpty(companyFields.getEmail())) {
            errorList.setError("company: email is empty");
            return false;
        }

        if (!isValidEmail(companyFields.getEmail())) {
            errorList.setError("company: email is not valid");
            return false;
        }

        if (isEmpty(companyFields.getWebAddress())) {
            errorList.setError("company: url is empty");
            return false;
        }

        if (!isValidURL(companyFields.getWebAddress())) {
            errorList.setError("company: url is not correct");
            return false;
        }

        if (isEmpty(companyFields.getAddress())) {
            errorList.setError("company: address is empty");
            return false;
        }

        return true;
    }

    public void reset() {
        errorList.reset();
    }

    private boolean isEmpty(String name) {
        if (name.equals(""))
            return true;
        else
            return false;
    }

    private boolean isValidName(String name) {
        if (name == null || name.equals(""))
            return false;

        return true;
    }

    private boolean isValidId(String id) {
        try {
            long idL = Long.parseLong(id);
            if (idL <= 0)
                return false;
        }
        catch (Exception ex) {
            ex.printStackTrace(System.out);
            return false;
        }
        return true;
    }

    private boolean isValidTags(String tags) {
        if (tags == null || tags.equals(""))
            return true;

        String arrTags[] = tags.split(" ");

        for (int i = 0; i < arrTags.length; i++) {
            if (arrTags[i].charAt(0) != '#') {
                return false;
            }
        }

        return true;
    }

    private boolean isValidMoneySum(String moneySum) {

        try {
            BigDecimal moneySumBG = new BigDecimal(moneySum);
        }
        catch (Exception ex) {
            ex.printStackTrace(System.out);
            return false;
        }

        return true;
    }

    private boolean isValidEmail(String email) {

        if (email.indexOf('@') == -1)
            return false;

        return true;
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        return true;
    }

    private boolean isValidURL(String url) {
        return true;
    }

    private boolean isValidSkype(String skype) {
        return true;
    }
}
