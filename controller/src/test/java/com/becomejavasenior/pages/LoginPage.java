package com.becomejavasenior.pages;

import com.becomejavasenior.webcomponent.Button;
import com.becomejavasenior.webcomponent.TextInput;
import com.becomejavasenior.WebPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by pyavchik.a on 27.09.15.
 */
public class LoginPage extends WebPage<LoginPage>{

    private static final String PAGE_URL = "https://crm2rad.herokuapp.com/";

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public LoginPage load() {
        driver.get(PAGE_URL);

        return this;
    }

    @Override
    public boolean isAvailable() {

        return getUsernameInput().waitUntilAvalible().isAvailable()&&
               getPasswordInput().waitUntilAvalible().isAvailable()&&
               getLoginButton().waitUntilAvalible().isAvailable();
    }

    private TextInput getUsernameInput(){
        return new TextInput(driver, By.id("inputLogin"));
    }

    public AdminHomePage loginAs(String username, String password){
        getUsernameInput().inputText(username);
        getPasswordInput().inputText(password);
        getLoginButton().click();

        return new  AdminHomePage(driver).waitUntilAvalible();
    }

    private TextInput getPasswordInput(){
        return new TextInput(driver, By.id("inputPassword"));
    }

    private Button getLoginButton(){
        return new Button(driver, By.xpath("html/body/div[1]/form/button"));
    }
}
