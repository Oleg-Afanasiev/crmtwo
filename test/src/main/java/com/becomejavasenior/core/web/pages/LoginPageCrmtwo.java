package com.becomejavasenior.core.web.pages;

import com.becomejavasenior.core.web.WebPage;
import com.becomejavasenior.core.web.elements.Button;
import com.becomejavasenior.core.web.elements.TextInput;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

/**
 * Created by pyavchik on 30.08.15.
 */
public class LoginPageCrmtwo extends WebPage<LoginPageCrmtwo>{


    private static final String PAGE_URL = "http://crmone.herokuapp.com/index.jsp";

    public LoginPageCrmtwo(WebDriver driver) {
        super(driver);
    }

    @Override
    public LoginPageCrmtwo load() {
        driver.get(PAGE_URL);
        return this;
    }

    @Override
    public boolean isAvailable() {
        return getEmailInput().isAvailable()&&
               getPasswordInput().isAvailable()&&
               getEnterButton().isAvailable();

    }

//    public UserHomePageCrmtwo loginAs(String email, String password) {
//        return this;
//    }

    private TextInput getEmailInput(){
        return new TextInput(driver, By.id("loginID"));
    }

    private TextInput getPasswordInput(){
        return new TextInput(driver, By.id("passwordID"));
    }

    private Button getEnterButton(){
        return new Button(driver, By.id("submit"));
    }

}
