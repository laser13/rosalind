package ru.pavlenov.bio.chapter.rosalind.combinatorics;

import ru.pavlenov.bio.amino.LocalAlignment;
import ru.pavlenov.bio.utils.Dump;

import java.util.ArrayList;

/**
 * Какой сам ☭
 * User: Павленов Семён
 * Date: 28.12.13 14:55
 */
public class Fib {

    /**
     * Rabbits and Recurrence Relations
     * http://rosalind.info/problems/fib/
     */
    public static void start() {

        int n = 84;
        int k = 1;
        int m = 18;

        ArrayList<Long> allList = new ArrayList<>();
        ArrayList<Long> adultList = new ArrayList<>();
        ArrayList<Long> childrenList = new ArrayList<>();

        rabbit2(allList, adultList, childrenList, m, n);

        Dump.println(allList);
        Dump.println(adultList);
        Dump.println(childrenList);

    }


    public static long rabbit(long adult, long children, int k, int m, int n) {

        if (n > 1) {
            return rabbit(adult + children, adult * k, k, m, n-1);
        }
        else {
            return adult + children;
        }

    }

    public static void rabbit2(ArrayList<Long> allList, ArrayList<Long> adultList, ArrayList<Long> childrenList, int m, int n) {

        int sizeAdult = adultList.size();
        int sizeChildren = childrenList.size();

        if (sizeAdult == 0 && sizeChildren == 0) {
            adultList.add(0L);
            childrenList.add(1L);
            allList.add(1L);
            rabbit2(allList, adultList, childrenList, m, n);
            return;
        }

        long adult = adultList.get(sizeAdult-1) + childrenList.get(sizeChildren-1);
        long children = adultList.get(sizeAdult-1);

        if (sizeChildren >= m) {
            adult -= childrenList.get(sizeChildren - m);
        }

        adultList.add(adult);
        childrenList.add(children);
        allList.add(adult + children);

        if (sizeAdult < n - 1) {
            rabbit2(allList, adultList, childrenList, m, n);
        }

    }


}
