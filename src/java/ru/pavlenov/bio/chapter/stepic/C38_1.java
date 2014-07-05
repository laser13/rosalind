package ru.pavlenov.bio.chapter.stepic;

import ru.pavlenov.bio.genome.DNAArray;
import ru.pavlenov.bio.utils.Dump;

import java.util.ArrayList;

/**
 * Какой сам ☭
 * User: Павленов Семён
 * Date: 23.11.13 19:14
 */
public class C38_1 {

    public static void start() {

        int k = 6;
        String tmp = "AAGCCAGAAGGACAATCATACACTGTAACGGCGGTTGTGGCT\n" +
                "GAAGAGATCTTAGACTGTGAGGTTGCGGTATTTTCAAGGAGG\n" +
                "TAGAAGGGGGTTTAGTACGGAATCTGTTAAAATATATAAGGT\n" +
                "CGCAAACCCCTTAACCTAGGGGTTATGCCCCGCTTCCCACAG\n" +
                "AAAAACTGTCTCGTGGTTGGGATATAGCTTGGTGTCCCCCTC\n" +
                "CCGTTCGCGGTTAAATTCCGCTCCATGTGCAATGTGGGAATC\n" +
                "GGGACTGTGGGTGAGGTTTCGGTTACATATTTGCGTCTGGAC\n" +
                "TGGGGATATCGGAGGTATATGTAACTACGCCAGGAAGCGGTT\n" +
                "CAAAACCACCGTAGTCACTAACAGGAGGTTGTCGTACCGGGA\n" +
                "GCCTCGCCGATCGGGGTTACGTAAGAGACCGCCCCGAGTCCA";
        String[] genomeList = tmp.split("\n");


        DNAArray dnaArray = new DNAArray(genomeList);

        ArrayList<String> median = dnaArray.findMedian(k);

        Dump.println(median);

    }

}
