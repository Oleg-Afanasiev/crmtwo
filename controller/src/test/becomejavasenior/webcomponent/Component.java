package becomejavasenior.webcomponent;

import becomejavasenior.Wait;
import org.openqa.selenium.WebDriver;

/**
 * Created by machine on 29.08.15.
 */
// component - link, button, page
public abstract class Component<T extends Component<T>> {

    protected WebDriver driver;

    public Component(WebDriver driver){
        this.driver = driver;
    }

    public abstract boolean isAvailable();

    public T waitUntilAvalible() {
        return new Wait<T>().forComponent((T) this).toBeAvailable();
    }
}
