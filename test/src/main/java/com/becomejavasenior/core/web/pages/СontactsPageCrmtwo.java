package com.becomejavasenior.core.web.pages;

import com.becomejavasenior.core.web.WebPage;
import com.becomejavasenior.core.web.elements.Link;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by pyavchik on 30.08.15.
 */
public class СontactsPageCrmtwo extends WebPage<СontactsPageCrmtwo>{


    private static final String PAGE_URL = "http://crm2rad.herokuapp.com/contacts";

    public СontactsPageCrmtwo(WebDriver driver) {
        super(driver);
    }

    @Override
    public СontactsPageCrmtwo load() {
        driver.get(PAGE_URL);
        return this;
    }

    @Override
    public boolean isAvailable() {
        return getAddContactLink().isAvailable();

    }

    public LoginPageCrmtwo loadAsUser() {
        load();
        return new LoginPageCrmtwo(driver).waitUntilAvalible();
    }

    private Link getAddContactLink(){
        return new Link(driver, By.xpath("//a[contains(@href, 'contactAddForm')]"));
    }

}
