package src.com.becomejavasenior.core.web.elements;

import src.com.becomejavasenior.core.web.WebComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by pyavchik on 30.08.15.
 */
public class Link extends WebComponent<Link>{

    public Link(WebDriver driver, By findByMethod) {
        super(driver, findByMethod);

    }

}
