package ru.pavlenov.bio.utils;

import java.util.*;

/**
 * Какой сам ☭
 * User: Павленов Семён
 * Date: 20.11.13 21:58
 */
public class Sort {

    static class ValueComparator implements Comparator<Integer> {

        Map<Integer, Integer> base;

        ValueComparator(Map<Integer, Integer> base) {
            this.base = base;
        }

        @Override
        public int compare(Integer a, Integer b) {
            Integer x = base.get(a);
            Integer y = base.get(b);
            if (y.equals(x)) {
                return b.compareTo(a);
            }
            return y.compareTo(x);
        }

    }

    private static Map sortByComparator(Map unsortMap, int order) {

        List list = new LinkedList(unsortMap.entrySet());

        // sort list based on comparator
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                return order * ((Comparable) ((Map.Entry) (o1)).getValue()).compareTo(((Map.Entry) (o2)).getValue());
            }
        });

        // put sorted list into map again
        //LinkedHashMap make sure order in which keys were inserted
        Map sortedMap = new LinkedHashMap();
        for (Object aList : list) {
            Map.Entry entry = (Map.Entry) aList;
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }

    public static Map byValue(Map unsortedMap, int order) {
        return sortByComparator(unsortedMap, order);
    }

    public static Map byKey(Map unsortedMap) {
        Map sortedMap = new TreeMap();
        sortedMap.putAll(unsortedMap);
        return sortedMap;
    }

    public static Integer[] array(Integer[] unsortArray) {
        Arrays.sort(unsortArray, Integer::compare);
        return unsortArray;
    }

    public static String[] array(String[] unsortArray) {
        Arrays.sort(unsortArray, String::compareTo);
        return unsortArray;
    }

}
