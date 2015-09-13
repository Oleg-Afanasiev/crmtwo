package src.com.becomejavasenior.tests;

import src.com.becomejavasenior.core.BaseTest;
import src.com.becomejavasenior.core.UserGenerator;
import src.com.becomejavasenior.core.web.pages.AddContactPageCrmtwo;
import org.testng.annotations.Test;

/**
 * Created by pyavchik.a on 10.09.15.
 */
public class AddContactTest extends BaseTest {

    @Test(alwaysRun = true)
    public void addContactTest(){
        new AddContactPageCrmtwo(driver)
                .loadAndWaitUntilAvailable()
                .addNewContactCrmtwo(new UserGenerator().name,
                                     new UserGenerator().tag,
                                     new UserGenerator().jobPosition,
                                     new UserGenerator().phoneNumber,
                                     new UserGenerator().email,
                                     new UserGenerator().skypeLogin,
                                     new UserGenerator().messageText);

        try {Thread.sleep(20000);} catch (Exception e){}
    }

}
