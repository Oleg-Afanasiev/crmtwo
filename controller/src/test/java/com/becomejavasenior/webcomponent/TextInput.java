package com.becomejavasenior.webcomponent;

import com.becomejavasenior.WebComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by machine on 30.08.15.
 */
public class TextInput extends WebComponent<TextInput> {

    public TextInput(WebDriver driver, By findByMethod) {
        super(driver, findByMethod);

    }

    public TextInput inputText(String text){
        getWebElement().sendKeys(text);
        return this;
    }

}
