package com.becomejavasenior.pages;

import com.becomejavasenior.webcomponent.Button;
import com.becomejavasenior.webcomponent.TextInput;
import com.becomejavasenior.WebPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by pyavchik on 30.08.15.
 */
public class AddContactPageCrmTwo extends WebPage<AddContactPageCrmTwo> {


    private static final String PAGE_URL = "https://crmtwo.herokuapp.com/contactadd";

    public AddContactPageCrmTwo(WebDriver driver) {
        super(driver);
    }

    @Override
    public AddContactPageCrmTwo load() {
        driver.get(PAGE_URL);
        return this;
    }

    @Override
    public boolean isAvailable(){
        return getNameAndSurnameInput(). isAvailable()&&
                getTagsInput().          isAvailable()&&
                getJobPositionInput().   isAvailable()&&
                getPhoneNumberInput().   isAvailable()&&
                getSkypeInput().         isAvailable()&&
                getMessageInput().       isAvailable()&&
                getEmailInput().         isAvailable()&&
                getContactSubmitButton().isAvailable();
    }

    public СontactPageCrmTwo addNewContactCrmtwo(String name,
                                                  String tags,
                                                  String jobPosition,
                                                  String phoneNumber,
                                                  String email,
                                                  String skype,
                                                  String message){
        getNameAndSurnameInput().inputText(name);
        getTagsInput()          .inputText(tags);
        getJobPositionInput()   .inputText(jobPosition);
        getPhoneNumberInput()   .inputText(phoneNumber);
        getEmailInput()         .inputText(email);
        getSkypeInput()         .inputText(skype);
        getMessageInput()       .inputText(message);
        getContactSubmitButton().click();

        return new СontactPageCrmTwo(driver).waitUntilAvalible();
    }


    private TextInput getNameAndSurnameInput(){
        return new TextInput(driver, By.id("contactName"));
    }

    private TextInput getTagsInput(){
        return new TextInput(driver, By.id("tags"));
    }

    private TextInput getJobPositionInput(){
        return new TextInput(driver, By.id("jobPosition"));
    }

    private TextInput getPhoneNumberInput(){
        return new TextInput(driver, By.id("phoneNumber"));
    }

    private TextInput getEmailInput(){
        return new TextInput(driver, By.id("email"));
    }

    private TextInput getSkypeInput(){
        return new TextInput(driver, By.id("skype"));
    }

    private TextInput getMessageInput(){
        return new TextInput(driver, By.id("contactComment"));
    }

    private TextInput getFilesAddingInput(){
        return new TextInput(driver, By.id("files"));
    }

    private TextInput getTaskTextInput(){
        return new TextInput(driver, By.id("taskText"));
    }

    private TextInput getDealNameInput(){
        return new TextInput(driver, By.id("dealName"));
    }

    private TextInput getDealBudgetInput(){
        return new TextInput(driver, By.id("dealBudget"));
    }

    private TextInput getCompanyNameInput(){
        return new TextInput(driver, By.id("companyName"));
    }

    private TextInput getCompanyPhoneInput(){
        return new TextInput(driver, By.id("companyPhone"));
    }

    private TextInput getWebAddressInput(){
        return new TextInput(driver, By.id("webAddress"));
    }

    private TextInput getCompanyAddressInput(){
        return new TextInput(driver, By.id("companyAddress"));
    }

    private Button getAddContactButton(){
        return new Button(driver, By.id("add_button"));
    }

    private Button getContactSubmitButton(){
        return new Button(driver, By.id("contactSubmitButton"));
    }


}
