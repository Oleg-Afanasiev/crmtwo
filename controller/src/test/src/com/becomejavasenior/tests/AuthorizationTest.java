package src.com.becomejavasenior.tests;

import src.com.becomejavasenior.core.BaseTest;
import src.com.becomejavasenior.core.web.pages.LoginPageCrmtwo;

/**
 * Created by pyavchik on 29.08.15.
 */
public class AuthorizationTest extends BaseTest{

    //@Test
    public void authorizationTestMethod(){
        new LoginPageCrmtwo(driver)
                .loadAndWaitUntilAvailable();

        try {Thread.sleep(20000);} catch (Exception e){}
    }
}
