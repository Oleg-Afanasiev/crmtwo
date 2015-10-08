package com.becomejavasenior.pages;

import com.becomejavasenior.WebPage;
import com.becomejavasenior.webcomponent.Link;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by pyavchik.a on 27.09.15.
 */
public class GuestHomePage extends WebPage<GuestHomePage>{

    private static final String PAGE_URL = "http://crm2rad.herokuapp.com/login";

    public GuestHomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public GuestHomePage load() {
        driver.get(PAGE_URL);

        return this;
    }

    @Override
    public boolean isAvailable() {
        return getContactsList().isAvailable();
    }



    private Link getContactsList(){
        return new Link(driver, By.xpath("//a[contains(@href, 'contactlist')]"));
    }

}
