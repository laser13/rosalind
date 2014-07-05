package ru.pavlenov.bio.utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Какой сам ☭
 * User: Павленов Семён
 * Date: 16.11.13 14:18
 */
public class IntegerArray extends ArrayWrapper<Integer> {

    public IntegerArray(int len) {
        super(new Integer[len]);
    }

    public IntegerArray(int len, Integer v) {
        super(new Integer[len], v);
    }

    @Override
    public Integer[] asUnique() {
        Set<Integer> s = new HashSet<>(Arrays.asList(ts));
        return s.toArray(new Integer[s.size()]);
    }
}
