package becomejavasenior;

import becomejavasenior.webcomponent.Component;
import org.openqa.selenium.WebDriver;

/**
 * Created by pyavchik on 30.08.15.
 */
public abstract class WebPage<T extends WebPage<T>> extends Component<T> {

    public WebPage(WebDriver driver){
        super(driver);
    }

    public abstract T load();

    public T loadAndWaitUntilAvailable(){
        load();
        waitUntilAvalible();
        return waitUntilAvalible();
    }

}
