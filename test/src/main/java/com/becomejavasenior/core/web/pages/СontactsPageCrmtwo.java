package com.becomejavasenior.core.web.pages;

import com.becomejavasenior.core.web.WebPage;
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
        return false;

    }

    public LoginPageCrmtwo loadAsUser() {
        load();
        return new LoginPageCrmtwo(driver).waitUntilAvalible();
    }

}
