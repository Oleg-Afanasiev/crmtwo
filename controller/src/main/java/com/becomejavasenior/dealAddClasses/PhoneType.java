package com.becomejavasenior.dealAddClasses;

/**
 * Created by oleg on 9/25/15.
 */
public class PhoneType {
    private String name;
    private Long id;

    public PhoneType(String name, Long phoneType) {
        this.name = name;
        this.id = phoneType;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }
}
