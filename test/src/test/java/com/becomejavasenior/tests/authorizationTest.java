package com.becomejavasenior.tests;

import com.becomejavasenior.core.BaseTest;
import org.testng.annotations.Test;

/**
 * Created by pyavchik on 29.08.15.
 */
public class authorizationTest extends BaseTest{

    @Test
    public void authorizationTestMethod(){
        driver.get("http://localhost:8080");
        try {Thread.sleep(20000);} catch (Exception e){}
    }
}
