package com.becomejavasenior.tests;

import com.becomejavasenior.pages.AdminHomePage;
import com.becomejavasenior.pages.LoginPage;
import org.testng.annotations.Test;

/**
 * Created by pyavchik.a on 27.09.15.
 */
public class TestLoginAsAdmin extends BaseTest {

    private static final String LOGIN = "puva@mail.ru";
    private static final String PASSWORD = "password";

    @Test(alwaysRun = true)
    public void testLoginAsAdmin(){
        new LoginPage(driver)
                .loadAsAnonymusUser()
                .loginAs(LOGIN, PASSWORD);

        new AdminHomePage(driver).waitUntilAvalible();

        try {Thread.sleep(2000);}catch (Exception e){}

    }
}
