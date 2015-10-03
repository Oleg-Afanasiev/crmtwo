package becomejavasenior.pages;

import becomejavasenior.webcomponent.Link;
import becomejavasenior.WebPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by pyavchik.a on 27.09.15.
 */
public class AdminHomePage extends WebPage<AdminHomePage>{

    private static final String PAGE_URL = "http://crm2rad.herokuapp.com";

    public AdminHomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public AdminHomePage load() {
        driver.get(PAGE_URL);

        return this;
    }

    @Override
    public boolean isAvailable() {
        return false;
    }

    public LoginPage loadAsAnonymusUser(){
        load();
        return new LoginPage(driver).waitUntilAvalible();
    }

    private Link getContactsList(){
        return new Link(driver, By.xpath("//a[contains(@href, 'contactlist')]"));
    }

}
