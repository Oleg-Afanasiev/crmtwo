package com.becomejavasenior.core.web.pages;

import com.becomejavasenior.core.web.WebPage;
import com.becomejavasenior.core.web.elements.Button;
import com.becomejavasenior.core.web.elements.TextInput;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by pyavchik on 30.08.15.
 */
public class AddContactPageCrmtwo extends WebPage<AddContactPageCrmtwo>{


    private static final String PAGE_URL = "http://crm2rad.herokuapp.com/contactAddForm";

    public AddContactPageCrmtwo(WebDriver driver) {
        super(driver);
    }

    @Override
    public AddContactPageCrmtwo load() {
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
                getFilesAddingInput().   isAvailable()&&
                getTaskTextInput().      isAvailable()&&
                getDealNameInput().      isAvailable()&&
                getDealBudgetInput().    isAvailable()&&
                getCompanyNameInput().   isAvailable()&&
                getCompanyPhoneInput().  isAvailable()&&
                getWebAddressInput().    isAvailable()&&
                getCompanyAddressInput().isAvailable()&&
                getAddContactButton().   isAvailable()&&
                getContactSubmitButton().isAvailable();

    }

    public СontactsPageCrmtwo addNewContactCrmtwo(String Name,
                                                  String Tags,
                                                  String JobPosition,
                                                  String PhoneNumber,
                                                  String Email,
                                                  String Skype,
                                                  String Message){
        getNameAndSurnameInput().inputText(Name);
        getTagsInput()          .inputText(Tags);
        getJobPositionInput()   .inputText(JobPosition);
        getPhoneNumberInput()   .inputText(PhoneNumber);
        getEmailInput()         .inputText(Email);
        getSkypeInput()         .inputText(Skype);
        getMessageInput()       .inputText(Message);
        getContactSubmitButton().click();

        return new СontactsPageCrmtwo(driver).waitUntilAvalible();
    }


    private TextInput getNameAndSurnameInput(){
        return new TextInput(driver, By.id("name"));
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
        return new TextInput(driver, By.id("message"));
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
        return new Button(driver, By.id("contact_submit_button"));
    }


}
