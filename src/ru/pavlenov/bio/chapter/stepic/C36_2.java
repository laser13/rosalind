package ru.pavlenov.bio.chapter.stepic;

import ru.pavlenov.bio.genome.DNAArray;
import ru.pavlenov.bio.utils.Dump;

import java.util.*;

/**
 * Какой сам ☭
 * User: Павленов Семён
 * Date: 22.11.13 19:52
 */
public class C36_2 {

    public static void start() {

        int k = 3;
        int d = 1;
        String tmp = "ATTTGGC\n" +
                "TGCCTTA\n" +
                "CGGTATC\n" +
                "GAAAATT";
        String[] genomeList = tmp.split("\n");


        DNAArray dnaArray = new DNAArray(genomeList);

        HashMap<String, Integer> motifList = dnaArray.findMotif(k, d);

        Dump.println(motifList);

    }

}
