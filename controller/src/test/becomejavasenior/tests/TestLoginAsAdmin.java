package becomejavasenior.tests;

import becomejavasenior.pages.AdminHomePage;
import org.testng.annotations.Test;

/**
 * Created by pyavchik.a on 27.09.15.
 */
public class TestLoginAsAdmin extends BaseTest {

    @Test
    public void testLoginAsAdmin(){
        new AdminHomePage(driver)
                .loadAsAnonymusUser()
                .loginAs("puva", "pass");

        new AdminHomePage(driver).waitUntilAvalible();

        try {Thread.sleep(2000);}catch (Exception e){}

    }
}
