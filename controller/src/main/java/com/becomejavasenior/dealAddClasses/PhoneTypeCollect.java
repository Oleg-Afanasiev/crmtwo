package com.becomejavasenior.dealAddClasses;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by oleg on 10/19/15.
 */
public class PhoneTypeCollect {

    final int N_TYPES = 5;
    private Collection<PhoneType> phoneTypes;

    public PhoneTypeCollect() {

        phoneTypes = new ArrayList<>();
        String name;

        for (int id = 1; id <= N_TYPES; id++) {
            name = getPhoneTypeNameById(id);
            phoneTypes.add(new PhoneType(name, Integer.toUnsignedLong(id)));
        }
    }

    public Collection<PhoneType> getPhoneTypes() {
        return this.phoneTypes;
    }

    public String getPhoneTypeNameById(int id) {
        switch (id) {
            case 1:
                return "Рабочий";
            case 2:
                return "Раб.прямой";
            case 3:
                return "Мобильный";
            case 4:
                return "Факс";
            case 5:
                return "Домашний";
            case 6:
                return "Другой";
        }
        return "unknown";
    }

}
