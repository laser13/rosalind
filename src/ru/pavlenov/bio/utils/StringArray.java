package ru.pavlenov.bio.utils;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Какой сам ☭
 * User: Павленов Семён
 * Date: 16.11.13 14:15
 */
public class StringArray extends ArrayWrapper<String> {

    public StringArray(int len) {
        super(new String[len]);
    }

    public StringArray(int len, String v) {
        super(new String[len], v);
    }

    @Override
    public String[] asUnique() {
        HashSet<String> s = new HashSet<>(Arrays.asList(ts));
        String[] ua = s.toArray(new String[s.size()]);
//        Arrays.sort(ua);
        return ua;
    }

}
