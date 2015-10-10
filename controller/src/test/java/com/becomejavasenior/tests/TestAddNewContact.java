package com.becomejavasenior.tests;

import com.becomejavasenior.UserGenerator;
import com.becomejavasenior.pages.AddContactPageCrmTwo;
import com.becomejavasenior.pages.СontactPageCrmTwo;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by pyavchik.a on 27.09.15.
 */
public class TestAddNewContact extends BaseTest {

    private AddContactPageCrmTwo addContactPage;
    private СontactPageCrmTwo contactsPageCrmtwo;

    @BeforeMethod
    public void init(){
        addContactPage = new AddContactPageCrmTwo(driver);
        contactsPageCrmtwo = new СontactPageCrmTwo(driver);

    }


    @Test(alwaysRun = true)
    public void testAddNewContact() {

        UserGenerator userGenerator = new UserGenerator();


        addContactPage.loadAndWaitUntilAvailable()
                .addNewContactCrmtwo(userGenerator.getName(),
                        userGenerator.getTag(),
                        userGenerator.getJobPosition(),
                        userGenerator.getPhoneNumber(),
                        userGenerator.getEmail(),
                        userGenerator.getSkypeLogin(),
                        userGenerator.getMessageText());


        contactsPageCrmtwo.waitUntilAvalible();



        try {
            Thread.sleep(2000);
        } catch (Exception e) {
        }

    }
}
