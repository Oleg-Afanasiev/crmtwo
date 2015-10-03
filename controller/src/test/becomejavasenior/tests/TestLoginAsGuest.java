package becomejavasenior.tests;

import becomejavasenior.pages.AdminHomePage;
import org.testng.annotations.Test;

/**
 * Created by pyavchik.a on 27.09.15.
 */
public class TestLoginAsGuest extends BaseTest {

    @Test(alwaysRun = true)
    public void testLoginAsGuest(){
        new AdminHomePage(driver)
                .loadAsAnonymusUser()
                .loginAs("avma", "pass");                                        // Login as guest

        try {Thread.sleep(2000);}catch (Exception e){}

    }


}
