package ru.pavlenov.bio.chapter.stepic;

import ru.pavlenov.bio.utils.Dump;
import ru.pavlenov.bio.utils.File;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Какой сам ☭
 * User: Павленов Семён
 * Date: 21.01.14 7:52
 */
public class C310_6 {

    /**
     * Suffix Arrays
     * https://stepic.org/Bioinformatics-Algorithms-2/Suffix-Arrays-310/step/6
     *
     * Constructing Suffix Tree from Suffix Array Problem:
     * Construct a suffix tree from the suffix array and LCP array of a string.
     * Input: A string Text, SuffixArray(Text), and LCP(Text).
     * Output: SuffixTree(Text).
     */
    public static void start() throws IOException {

        String[] data = File.readFile("/home/laser13/IdeaProjects/bio/src/ru/pavlenov/bio/chapter/stepic/C310_6.small", Charset.defaultCharset()).split("\n");
        String text = data[0];
        String[] strSuffArr = data[1].split(", ");
        String[] strLcpArr = data[2].split(", ");

        Integer[] suffArr = new Integer[strSuffArr.length];
        Integer[] lcpArr = new Integer[strLcpArr.length + 1];

        for (int i = 0; i < strLcpArr.length; i++) {
            suffArr[i] = Integer.valueOf(strSuffArr[i].trim());
            lcpArr[i] = Integer.valueOf(strLcpArr[i].trim());
        }
        lcpArr[strLcpArr.length] = 0;

        int strLen = text.length();
//        Dump.println(text);
//        Dump.println(suffArr);
//        Dump.println(lcpArr);

        List<String> edges = new ArrayList<>();
        for (int i = 0; i < strLcpArr.length; i++) {

            int currOrder = suffArr[i];
            int tDiff = lcpArr[i];
            int bDiff = lcpArr[i+1];


            String curr = text.substring(currOrder);

            Dump.println(currOrder + " | " + tDiff + " | " + bDiff);

            if (tDiff >= bDiff) {
                edges.add(curr.substring(tDiff));
            }
            if (tDiff < bDiff) {
                edges.add(curr.substring(tDiff, bDiff));
                edges.add(curr.substring(bDiff));
            }
//            if (tDiff > bDiff) {
//                edges.add(curr.substring(tDiff));
//            }

        }

        Dump.println(edges);

//        BufferedWriter logWriter = new BufferedWriter(new FileWriter("/home/laser13/IdeaProjects/bio/src/ru/pavlenov/bio/chapter/stepic/C310_6.answer", true));
//        for (String edge : edges) {
//
//            logWriter.write(edge + "\n");
//            logWriter.flush();
//
//        }

    }

}
