package com.becomejavasenior.tests;

import com.becomejavasenior.pages.AdminHomePage;
import com.becomejavasenior.pages.GuestHomePage;
import com.becomejavasenior.pages.LoginPage;
import org.testng.annotations.Test;

/**
 * Created by pyavchik.a on 27.09.15.
 */
public class TestLoginAsGuest extends BaseTest {

    private static final String LOGIN = "avma@mail.ru";
    private static final String PASSWORD = "password";

    @Test(alwaysRun = true)
    public void testLoginAsGuest(){
        new LoginPage(driver)
                .loadAsAnonymusUser()
                .loginAs(LOGIN, PASSWORD);

        new GuestHomePage(driver).waitUntilAvalible();

        try {Thread.sleep(2000);}catch (Exception e){}

    }


}
