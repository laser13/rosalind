package ru.pavlenov.bio.chapter.stepic;

import com.google.common.base.Joiner;
import ru.pavlenov.bio.genome.DNA;
import ru.pavlenov.bio.utils.Dump;
import ru.pavlenov.bio.utils.File;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Какой сам ☭
 * User: Павленов Семён
 * Date: 12.01.14 15:15
 */
public class C289_2 {

    /**
     * Shared k-mers Problem: Given two strings, find all their shared k-mers.
     * https://stepic.org/Bioinformatics-Algorithms-2/Synteny-Block-Construction-289/step/2
     *
     * Input: An integer k and two strings.
     * Output: All k-mers shared by these strings, in the form of ordered pairs (x, y).
     */
    public static void start() throws IOException {

        int k = 19;
        String str1 = "AAAACTCATCCAAAAACC";
        String str2 = "TTTTCTCAGGTTCAAATC";

        String[] data = File.readFile("/home/laser13/IdeaProjects/bio/src/ru/pavlenov/bio/chapter/C289_2.data", Charset.defaultCharset()).split("\n");
        str1 = data[1];
        str2 = data[0];

        List<Integer[]> indexes = new ArrayList<>();

        for (int i = 0; i <= str1.length() - k; i++) {

            String kmer = str1.substring(i, i + k);
            String reverse = DNA.reverse(kmer);

//            Dump.println(kmer + " | " + reverse);

            int j1 = 0;
            int j2 = 0;

            while (j1 >= 0) {
                j1 = str2.indexOf(kmer, j1 + 1);
                if (j1 >= 0) indexes.add(new Integer[]{j1, i});
            }

            while (j2 >= 0) {
                j2 = str2.indexOf(reverse, j2 + 1);
                if (j2 >= 0) indexes.add(new Integer[]{j2, i});
            }

//            Dump.println(indexes.size());

        }

        for (Integer[] integers : indexes) {
            Dump.println("(" + Joiner.on(", ").join(integers) + ")");
        }

    }

}
