package ru.pavlenov.bio.chapter.rosalind.combinatorics;

import ru.pavlenov.bio.algorithm.ReversalSort;
import ru.pavlenov.bio.utils.Dump;
import ru.pavlenov.bio.utils.File;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by laser13 on 26.06.14.
 */
public class Rear {

    public static void start() throws IOException {

        String data = File.readFile("/home/semen/www/java/rosalind/src/java/ru/pavlenov/bio/chapter/rosalind/combinatorics/Rear.data", Charset.defaultCharset());
        String[] line = data.split("\n");

        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for (String l : line) {
            if (!l.isEmpty()) {
                String[] sArr = l.split(" ");
                ArrayList<Integer> iArr = new ArrayList<>();
                for (String a : sArr) {
                    iArr.add(Integer.parseInt(a));
                }
                list.add(iArr);
            }
        }

        for (int j = 0; j < list.size(); j += 2) {

            // Эталонная последовательность
            ArrayList<Integer> a1 = list.get(j);
            ArrayList<Integer> a2 = list.get(j + 1);

//            Collections.reverse(a2);
//            int len = a1.size();
//
//            int b1 = -1;
//            for (int i = 0; i < len; i++) {
//
//                int prev;
//                if (i == 0) prev = 0;
//                else prev = a1.get(i-1);
//
//                if (Math.abs(prev - a1.get(i)) != 1) b1++;
//
//            }
//
//            int b2 = -1;
//            for (int i = 0; i < len; i++) {
//
//                int prev;
//                if (i == 0) prev = 0;
//                else prev = a2.get(i-1);
//
//                if (Math.abs(prev - a2.get(i)) != 1) b2++;
//
//            }

//            Dump.println(a1);
//            Dump.println(b1);
            ReversalSort.greedySorting(a1.toArray(new Integer[a1.size()]));

//            Dump.println(a2);
//            Dump.println(b2);
            ReversalSort.greedySorting(a2.toArray(new Integer[a2.size()]));

            Dump.line();

        }
    }
}
