package ru.pavlenov.bio.utils;

import java.util.Arrays;

/**
 * Какой сам ☭
 * User: Павленов Семён
 * Date: 16.11.13 13:44
 */
public abstract class ArrayWrapper<T> {

    protected T[] ts;
    private int index = 0;

    public ArrayWrapper(T[] ts) {
        this.ts = ts;
    }

    public ArrayWrapper(T[] ts, T v) {
        this.ts = ts;
        add(v);
    }

    public void add(T val) {
        ts[index] = val;
        index++;
    }

    public int size() {
        return ts.length;
    }

    public T[] asArray() {
        return ts;
    }

    public abstract T[] asUnique();

    public T[] clone() {
        return ts.clone();
    }

    public ArrayWrapper sort() {
        Arrays.sort(ts);
        return this;
    }
}
