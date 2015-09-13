package src.com.becomejavasenior.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

/**
 * Created by pyavchik on 29.08.15.
 */
public abstract class BaseTest {

    protected WebDriver driver;

    @BeforeSuite(alwaysRun = true)
    public void setUp(){
        driver = new FirefoxDriver();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown(){
        driver.quit();
    }

}
