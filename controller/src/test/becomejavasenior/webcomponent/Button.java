package becomejavasenior.webcomponent;

import becomejavasenior.WebComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by machine on 30.08.15.
 */
public class Button extends WebComponent<Button> {

    public Button(WebDriver driver, By findByMethod) {
        super(driver, findByMethod);

    }

}
