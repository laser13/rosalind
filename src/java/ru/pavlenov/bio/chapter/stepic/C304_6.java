package ru.pavlenov.bio.chapter.stepic;

import ru.pavlenov.bio.algorithm.BWT;
import ru.pavlenov.bio.utils.Dump;
import ru.pavlenov.bio.utils.File;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Какой сам ⚝
 * Author: Pavlenov Semen
 * Date: 29.01.14
 * <p>
 * E = mc^2
 */
public class C304_6 {

    private static final String path = "/home/laser13/IdeaProjects/bio/src/ru/pavlenov/bio/chapter/stepic/";

    // /home/laser13/IdeaProjects/bio/src/ru/pavlenov/bio/chapter/stepic/C304_6.data

    private static long time;
    private static int counter = 0;

    private static final List<Integer> result = new ArrayList<>();


    /**
     * Epilogue: Mismatch-Tolerant Read Mapping
     * https://stepic.org/Bioinformatics-Algorithms-2/Epilogue-Mismatch-Tolerant-Read-Mapping-304/step/6
     *
     * CODE CHALLENGE: Solve the Multiple Approximate Pattern Matching Problem.
     * Input: A string Text, followed by a collection of strings Patterns, and an integer d.
     * Output: All positions where one of the strings in Patterns appears as a substring of Text with at most d mismatches.
     *
     * @throws IOException
     */
    public static void start() throws IOException {

        String[] data = File.readFile(path + "/C304_6.data", Charset.defaultCharset()).split("\n");
        String text = data[0];
        String[] subs = data[1].split(" ");
        Integer k = Integer.valueOf(data[2]);
        int len = text.length();
        Dump.println(len);
//        Dump.println(text);
        Dump.println(subs.length);
        Dump.println(k);

        BWT bwt = new BWT();
        bwt.setText(text + "$");

        Dump.println("Make BWT");

//        byte[] lastColumn = bwt.getLastColumn();
//        byte[] firstColumn = bwt.getFirstColumn();
        int[] suffix = bwt.getSuffix();

//        Dump.println(firstColumn);
//        Dump.println(lastColumn);
//        Dump.println(bwt.getMap());


        Dump.println("Start matching");

//        Map<Integer, Byte> mismatch = bwt.matching("CCACGACGGGAGCATTTTTGGTCATGAGGCGCGCTAAAGGACACCCAGCGCGTG", k);

        time = System.currentTimeMillis();

        ReentrantLock resultLock = new ReentrantLock();
        Arrays.asList(subs).parallelStream().forEach(s -> {
            Integer[][] mismatch = bwt.matching(s, k);

            resultLock.lock();
            for (Integer[] pair : mismatch) {
                if (pair == null) continue;
                result.add(suffix[pair[0]]);
            }


            if (counter % 5000 == 0) {
                Dump.println(counter + " >>> " + (System.currentTimeMillis() - time) + "ms.");
                time = System.currentTimeMillis();
            }
            counter++;
            resultLock.unlock();

        });

        Dump.print("Final sorting ");
        time = System.currentTimeMillis();
        Collections.sort(result);
        Dump.println("complite: " + (System.currentTimeMillis() - time) + "ms.");
//        Dump.println(result);

        BufferedWriter logWriter = new BufferedWriter(new FileWriter(path + "C304_6.answer", false));
        for (Integer i : result) {
            logWriter.write(i + " ");
            logWriter.flush();
        }

        Dump.ln();

    }

}
