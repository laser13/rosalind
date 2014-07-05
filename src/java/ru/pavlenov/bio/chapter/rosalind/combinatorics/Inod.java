package ru.pavlenov.bio.chapter.rosalind.combinatorics;

import ru.pavlenov.bio.utils.Dump;

/**
 * Created by laser13 on 27.06.14.
 * Counting Phylogenetic Ancestors
 * http://rosalind.info/problems/inod/
 *
 * Given: A positive integer n (3≤n≤10000).
 * Return: The number of internal nodes of any unrooted binary tree having n leaves.
 *
 */
public class Inod {

    public static void start() {

        int n = 9357;
        int i = n;
        int cach = 0;
        boolean stop = false;

        do {
            cach += (i/2);
            i = (i/2) + (i%2);

            if (i == 2) stop = true;
            if (i == 3) {
                cach += 1;
                stop = true;
            }
        } while (!stop);

        Dump.println(cach);
        Dump.println(n - 2);

    }

}
