package com.becomejavasenior.tests;

import com.becomejavasenior.core.BaseTest;
import com.becomejavasenior.core.web.pages.LoginPageCrmtwo;
import org.testng.annotations.Test;

/**
 * Created by pyavchik on 29.08.15.
 */
public class authorizationTest extends BaseTest{

    @Test
    public void authorizationTestMethod(){
        new LoginPageCrmtwo(driver)
                .loadAndWaitUntilAvailable()
                .loginAs("test@test.test", "test");

        try {Thread.sleep(20000);} catch (Exception e){}
    }
}
