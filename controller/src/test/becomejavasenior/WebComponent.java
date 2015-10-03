package becomejavasenior;

import becomejavasenior.webcomponent.Component;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

/**
 * Created by pyavchik on 30.08.15.
 */
public abstract class WebComponent<T extends WebComponent<T>> extends Component {

    protected final By findByMethod;

    public WebComponent(WebDriver driver, By findByMethod) {
        super(driver);
        this.findByMethod = findByMethod;
    }

    @Override
    public boolean isAvailable(){
        try {
            return getWebElement() != null;
        } catch (NoSuchElementException e) {
            return false;
        }

    }

    public void click() {
        getWebElement().click();
    }

    public String getText() {
        return getWebElement().getText();
    }

    protected WebElement getWebElement(){
        return driver.findElement(findByMethod);
    }

}
