package ru.pavlenov.bio.chapter.stepic;

import ru.pavlenov.bio.assemble.Base;
import ru.pavlenov.bio.assemble.Euler;
import ru.pavlenov.bio.assemble.Graph;
import ru.pavlenov.bio.utils.Dump;
import ru.pavlenov.bio.utils.File;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Какой сам ☭
 * User: Павленов Семён
 * Date: 15.12.13 17:58
 */
public class C59_5 {

    public static void start() throws IOException {

        String input = File.readFile("/home/semen/IdeaProjects/bio/genome/dataset_59_5.txt", Charset.defaultCharset());
        String[] kmerList = input.split("\n");

        Arrays.sort(kmerList);

        int k = kmerList[0].length();

        Dump.println(k);
        Dump.println(kmerList.length);

//        Dump.println(kmerList);

        HashMap<String, AtomicInteger> kmerCounter = new HashMap<>();

        SortedSet<String> sortedSet = new TreeSet<>();
        for (String kmer: kmerList) {
            sortedSet.add(Base.prefix(kmer));
            sortedSet.add(Base.suffix(kmer));

//            if (kmerCounter.containsKey(kmer)) {
//                kmerCounter.get(kmer).getAndIncrement();
//            }
//            else {
//                kmerCounter.put(kmer, new AtomicInteger(1));
//            }

        }
        String[] sortedArray = sortedSet.toArray(new String[sortedSet.size()]);

//        Dump.println(sortedArray);

//        HashMap<String, ArrayList<String>> overlapGraph = Base.getOverlapGraph(kmerList);
        HashMap<String, ArrayList<String>> deBruijnGraph = Base.getDeBruijnGraph(kmerList);

//        Dump.println(overlapGraph);
//        Dump.println(deBruijnGraph);

        Graph.Directed  directedGraph = new Graph.Directed();
        directedGraph.buildFrom(deBruijnGraph, sortedArray);

//        Dump.println(directedGraph);

        ArrayList<Integer[]> result = Euler.findNonBranchPath(directedGraph);

        for (Integer[] path : result) {
//            Dump.println(path);
        }

        String[] resultKmers = new String[result.size()];
        int counter = 0;
        for (Integer[] path : result) {
            String kmer = null;
            for (Integer i : path) {
                if (kmer == null) kmer = sortedArray[i];
                else kmer += sortedArray[i].charAt(k-2);
            }
            resultKmers[counter] = kmer;
            counter++;
        }

        Arrays.sort(resultKmers);

//        Dump.println(resultKmers);

        for (String kmer : resultKmers) {
//            if (kmerCounter.containsKey(kmer)) {
//                for (int i = 0; i < kmerCounter.get(kmer).get(); i ++) {
//                    Dump.println(kmer);
//                }
//            }
//            else {
                Dump.println(kmer);
//            }
        }
        Dump.println("");

    }

}
