package com.becomejavasenior.core.web.pages;

import com.becomejavasenior.core.web.WebPage;
import org.openqa.selenium.WebDriver;

/**
 * Created by pyavchik on 30.08.15.
 */
public class UserHomePageCrmtwo extends WebPage<UserHomePageCrmtwo>{


    private static final String PAGE_URL = "http://crmone.herokuapp.com/login";

    public UserHomePageCrmtwo(WebDriver driver) {
        super(driver);
    }

    @Override
    public UserHomePageCrmtwo load() {
        driver.get(PAGE_URL);
        return this;
    }

    @Override
    public boolean isAvailable() {
        return false;

    }

}
