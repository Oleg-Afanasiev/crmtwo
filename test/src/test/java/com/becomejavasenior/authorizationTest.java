package com.becomejavasenior;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Created by pyavchik on 29.08.15.
 */
public class authorizationTest {

    private WebDriver driver;

    @BeforeTest
    public void setUp(){
        driver = new FirefoxDriver();
    }

    @Test
    public void authorizationTestMethod(){
        driver.get("http://localhost:8080");
        try {Thread.sleep(20000);} catch (Exception e){}
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }

}
