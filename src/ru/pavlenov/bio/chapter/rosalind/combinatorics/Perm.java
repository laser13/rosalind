package ru.pavlenov.bio.chapter.rosalind.combinatorics;

import com.google.common.base.Joiner;
import com.google.common.collect.Collections2;
import ru.pavlenov.bio.utils.Dump;
import ru.pavlenov.bio.utils.Gen;

import java.util.*;

/**
 * Какой сам ☭
 * User: Павленов Семён
 * Date: 12.01.14 13:39
 */
public class Perm {

    /**
     * Enumerating Gene Orders
     * http://rosalind.info/problems/perm/
     *
     * Given: A positive integer n≤7.
     * Return: The total number of permutations of length n, followed by a list of all such permutations (in any order).
     *
     */
    public static void start() {

        ArrayList<Integer> alph = new ArrayList<>();
        Collections.addAll(alph, 1, 2, 3, 4, 5);

        Collection<List<Integer>> permutations = Collections2.permutations(alph);

        Dump.println(permutations.size());
        for (List<Integer> perm : permutations) {
            Dump.println(Joiner.on(' ').join(perm));
        }


    }

}
