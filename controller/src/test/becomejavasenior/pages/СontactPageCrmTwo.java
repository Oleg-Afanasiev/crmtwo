package becomejavasenior.pages;

import becomejavasenior.WebPage;
import becomejavasenior.webcomponent.Link;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by pyavchik on 30.08.15.
 */
public class СontactPageCrmTwo extends WebPage<СontactPageCrmTwo> {


    private static final String PAGE_URL = "http://crmtwo.herokuapp.com/contactlist";

    public СontactPageCrmTwo(WebDriver driver) {
        super(driver);
    }

    @Override
    public СontactPageCrmTwo load() {
        driver.get(PAGE_URL);
        return this;
    }

    @Override
    public boolean isAvailable() {
        return getAddContactLink().isAvailable()&&
        getContactListLink().isAvailable();


    }


    private Link getAddContactLink() {
        return new Link(driver, By.xpath("//a[contains(@href, 'contactadd')]"));
    }

    private Link getContactListLink() {
        return new Link(driver, By.xpath("//a[contains(@href, 'contactlist')]"));
    }

}
