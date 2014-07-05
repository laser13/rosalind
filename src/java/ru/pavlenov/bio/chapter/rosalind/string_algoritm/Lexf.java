package ru.pavlenov.bio.chapter.rosalind.string_algoritm;

import com.google.common.collect.Collections2;
import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;
import ru.pavlenov.bio.utils.Dump;
import ru.pavlenov.bio.utils.Gen;

import java.util.*;

/**
 * Какой сам ☭
 * User: Павленов Семён
 * Date: 11.01.14 22:41
 */
public class Lexf {

    public static void start() {

        Character[] alph = {'T', 'I', 'P', 'D', 'G', 'V', 'L', 'X', 'Q', 'Y'};

        Integer[] vectorData = new Integer[alph.length];

        for (int i = 0; i < alph.length; i++) {
            vectorData[i] = i;
        }
        int n = 2;

        Generator<Integer> gen = Factory.createPermutationWithRepetitionGenerator(Factory.createVector(vectorData), n);


        List<List<Integer>> list = new ArrayList<>();
        for (ICombinatoricsVector<Integer> perm : gen) {
            list.add(perm.getVector());
        }

        Collections.sort(list, (List<Integer> o1, List<Integer> o2) -> {

            int result = 0;
            for (int i = 0; i < o1.size(); i++) {

                if (o1.get(i) > o2.get(i)) {
                    result = 1;
                    break;
                }
                else if (o1.get(i) < o2.get(i)) {
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
