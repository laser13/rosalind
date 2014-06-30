package ru.pavlenov.bio.chapter.rosalind.string_algoritm;

import org.paukov.combinatorics.CombinatoricsVector;
import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;
import ru.pavlenov.bio.utils.Dump;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by laser13 on 19.04.14.
 */
public class Lexv {

    /**
     * http://rosalind.info/problems/lexv/
     * Ordering Strings of Varying Length Lexicographically
     */
    public static void start() {

        Character[] alph = {'V', 'R', 'A', 'G', 'K', 'I', 'P', 'E', 'W', 'T'};
        int k = 4;

        Integer[] vectorData = new Integer[alph.length];

        for (int i = 0; i < alph.length; i++) {
            vectorData[i] = i;
        }

        List<List<Integer>> list = new ArrayList<>();
        for (int n = 1; n <= k; n++) {
            Generator<Integer> gen = Factory.createPermutationWithRepetitionGenerator(new CombinatoricsVector<Integer>(vectorData), n);
            for (ICombinatoricsVector<Integer> perm : gen) {
                if (perm.getSize() > 0) list.add(perm.getVector());
            }
        }

        Collections.sort(list, (List<Integer> o1, List<Integer> o2) -> {

            int result = 0;
            int s1 = o1.size();
            int s2 = o2.size();

            int s = Math.min(s1, s2);

            for (int i = 0; i < s; i++) {

                if (o1.get(i) > o2.get(i)) {
                    result = 1;
                    break;
                } else if (o1.get(i) < o2.get(i)) {
                    result = -1;
                    break;
                }
            }
            return result;
        });

        String[] result = new String[list.size()];
        int counter = 0;
        for (List<Integer> el : list) {

            StringBuilder sb = new StringBuilder();
            for (Integer b : el) {
//                Dump.println(Arrays.binarySearch(vectorData, b));
                sb.append(alph[Arrays.binarySearch(vectorData, b)]);
            }
            result[counter] = sb.toString();
            counter++;

        }

        for (String s : result) {
            Dump.println(s);
        }

    }

}
