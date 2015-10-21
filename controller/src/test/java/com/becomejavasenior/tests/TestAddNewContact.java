package com.becomejavasenior.tests;

import com.becomejavasenior.UserGenerator;
import com.becomejavasenior.pages.AddContactPageCrmTwo;
import com.becomejavasenior.pages.ContactPageCrmTwo;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by pyavchik.a on 27.09.15.
 */
public class TestAddNewContact extends BaseTest {

    private AddContactPageCrmTwo addContactPage;
    private ContactPageCrmTwo contactsPageCrmtwo;

    @BeforeMethod
    public void init(){
        addContactPage = new AddContactPageCrmTwo(driver);
        contactsPageCrmtwo = new ContactPageCrmTwo(driver);

    }


    @Test(alwaysRun = true)
    public void testAddNewContactPositive() {

        UserGenerator userGenerator = new UserGenerator();


        addContactPage.loadAndWaitUntilAvailable();
        addContactPage.fillNewContactForm(userGenerator.getName(),
                userGenerator.getTag(),
                userGenerator.getJobPosition(),
                userGenerator.getPhoneNumber(),
                userGenerator.getEmail(),
                userGenerator.getSkypeLogin(),
                userGenerator.getMessageText());
        addContactPage.submitContactForm();


        contactsPageCrmtwo.waitUntilAvalible();
        Assert.assertEquals("Список контактов",driver.getTitle(), "Title is not correct");



        try {
            Thread.sleep(2000);
        } catch (Exception e) {
        }

    }

    @Test(alwaysRun = true)
    public void testAddNewContactWrongUserName() {

        UserGenerator userGenerator = new UserGenerator();


        addContactPage.loadAndWaitUntilAvailable();
        addContactPage.fillNewContactForm("0wrongUserName",
                userGenerator.getTag(),
                userGenerator.getJobPosition(),
                userGenerator.getPhoneNumber(),
                userGenerator.getEmail(),
                userGenerator.getSkypeLogin(),
                userGenerator.getMessageText());
        addContactPage.submitContactForm();


        Assert.assertEquals("Contact Create Form",driver.getTitle(), "Title is not correct");



        try {
            Thread.sleep(2000);
        } catch (Exception e) {
        }

    }


}
