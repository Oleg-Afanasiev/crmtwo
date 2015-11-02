package com.becomejavasenior.dealAddClasses;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oleg on 9/30/15.
 */
public class ErrorsContainer {

    private List<String> nameErrors;

    public ErrorsContainer() {
        nameErrors = new ArrayList<>();
    }

    public void setError(String nameError) {
        this.nameErrors.add(nameError);
    }

    public boolean isStatusError() {
        return this.nameErrors.size() > 0;
    }

    public String getName() {
        if (this.nameErrors.size() > 0)
            return this.nameErrors.get(0);
        else
            return "";
    }

    public void reset() {
        this.nameErrors.clear();
    }
}
