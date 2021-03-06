package com.becomejavasenior;


import com.becomejavasenior.webcomponent.Component;
import org.openqa.selenium.TimeoutException;

/**
 * Created by machine on 29.08.15.
 */
public class Wait<T extends Component<T>> {

    private static final int DEFAULT_TIMEOUT = 30000;
    private static final int DEFAULT_RETRY_DELAY = 500;
    private T component;

    public Wait(){};

    public Wait<T> forComponent(T component){
        this.component = component;
        return this;
    }

    public T toBeAvailable(){
        int timePassed = 0;
        while (timePassed < DEFAULT_TIMEOUT){
            if (this.component.isAvailable()){
                return this.component;
            }
            timePassed = timePassed + delay();
        }
        if (!this.component.isAvailable()){
            throw new TimeoutException("Timeout after " + DEFAULT_TIMEOUT + "ms. waiting for " + this.component.getClass().getSimpleName() + " to be available.");
        }
        return this.component;
    }

    private int delay(){
        try {
            Thread.sleep(DEFAULT_RETRY_DELAY);
            return  DEFAULT_RETRY_DELAY;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}
