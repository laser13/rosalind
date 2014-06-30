package ru.pavlenov.bio.chapter.rosalind.combinatorics;

import com.google.common.base.Joiner;
import com.google.common.collect.Collections2;
import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;
import ru.pavlenov.bio.utils.Convert;
import ru.pavlenov.bio.utils.Dump;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ⓭
 * Какой сам? by Pavlenov Semen 29.06.14.
 *
 * Enumerating Oriented Gene Orderings
 * http://rosalind.info/problems/sign/
 *
 * Given: A positive integer n≤6.
 * Return: The total number of signed permutations of length n,
 * followed by a list of all such permutations
 * (you may list the signed permutations in any order).
 *
 */
public class Sign {

    public static void start() {

        int n = 5;

        Integer[] alph = new Integer[n*2];

        for (int i = 0; i < n; i++) {
            alph[i] = i+1;
            alph[n+i] = (i+1)*-1;
        }
        Dump.println(alph);

        ICombinatoricsVector<Integer> initialVector = Factory.createVector(alph);
        Generator<Integer> gen = Factory.createPermutationWithRepetitionGenerator(initialVector, n);

        int count = 0;
        List<List<Integer>> result = new ArrayList<>();
        for (ICombinatoricsVector<Integer> combination : gen) {
            List<Integer> v = combination.getVector();

            boolean ok = true;
            for (int i = 0; i < v.size(); i++) {

                for (int j = 0; j < v.size(); j++) {

                    if (i != j && Math.abs(v.get(i)) == Math.abs(v.get(j))) {
                        ok = false;
                        break;
                    }

                }

            }

            if (ok) {
                result.add(v);
            }

        }

        Dump.println(result.size());

        for (List<Integer> r : result) {
            Dump.println(Joiner.on(" ").join(r));
        }

    }

}