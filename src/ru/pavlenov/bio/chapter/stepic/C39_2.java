package ru.pavlenov.bio.chapter.stepic;

import ru.pavlenov.bio.genome.Kmer;
import ru.pavlenov.bio.utils.Convert;
import ru.pavlenov.bio.utils.Dump;
import ru.pavlenov.bio.utils.Sort;

import java.util.*;

/**
 * Какой сам ☭
 * User: Павленов Семён
 * Date: 24.11.13 18:41
 */
public class C39_2 {

    public static void start() {

        Character[] nucl = new Character[]{'A', 'C', 'G', 'T'};

        String text = "AAGATATTACTGATGAGGTTCGAGCGTACCTCAAGTTGCGGTGTGCCGGGGGCAGTCACTGGCTCCCTGCATATGGTAATTCCGATACGCCGCGATCTAATTGGGATATATTGGACCCTTACACAGTCTCGTGTCTTCTGACAGACACGAAGATTCCCAATTCCTGATGTTTCCTTGGCTAGATCGATATGACTGTGTGG";
        String strMatrix = "0.16 0.32 0.24 0.28\n" +
                "0.16 0.32 0.2 0.32\n" +
                "0.28 0.16 0.28 0.28\n" +
                "0.2 0.32 0.28 0.2\n" +
                "0.24 0.32 0.16 0.28\n" +
                "0.2 0.36 0.32 0.12\n" +
                "0.48 0.24 0.16 0.12\n" +
                "0.32 0.24 0.32 0.12";
        Float[][] profile = Convert.from(strMatrix).toFloatMatrix("\n", " ");

        HashMap<String, ArrayList<Integer>> kmerList = Kmer.findAll(text, 8);

        HashMap<String, Float> probableList = new HashMap<>();

        for (Map.Entry<String, ArrayList<Integer>> pair : kmerList.entrySet()) {

            char[] charList = pair.getKey().toCharArray();
            float p = 1;
            for (int i = 0; i < charList.length; i++) {
                int j = Arrays.binarySearch(nucl, charList[i]);
                p *= profile[i][j];
            }
            probableList.put(pair.getKey(), p);

        }

        HashMap<String, Float> sortList = (HashMap<String, Float>) Sort.byValue(probableList, -1);
        for (Map.Entry<String, Float> pair : sortList.entrySet()) {

            String kmer = pair.getKey();

            if (text.contains(kmer)) {

                Dump.println(kmer);
                Dump.println(pair.getValue());
                break;

            }

        }

//        Dump.println(sortList);


    }

}
