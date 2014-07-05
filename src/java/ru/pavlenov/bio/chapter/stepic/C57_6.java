package ru.pavlenov.bio.chapter.stepic;

import ru.pavlenov.bio.assemble.Euler;
import ru.pavlenov.bio.assemble.Graph;
import ru.pavlenov.bio.utils.Dump;
import ru.pavlenov.bio.utils.File;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;

/**
 * Какой сам ☭
 * User: Павленов Семён
 * Date: 07.12.13 14:41
 */
public class C57_6 {

    public static void start() throws IOException {

        String input = File.readFile("/home/laser13/IdeaProjects/bio/genome/dataset_57_6.txt", Charset.defaultCharset());
        String[] inputList = input.split("\n");

        Graph.Directed  directedGraph = new Graph.Directed();

        SortedSet<String> sortedSet = new TreeSet<>();
        for (int i = 0; i < inputList.length; i++) {

            String[] pair = inputList[i].split(" -> ");
            Collections.addAll(sortedSet, pair);

        }

        String[] kmerList = sortedSet.toArray(new String[sortedSet.size()]);

        for (int i = 0; i < inputList.length; i++) {

            String[] pair = inputList[i].split(" -> ");
            Graph.Directed.Node node = new Graph.Directed.Node(Arrays.binarySearch(kmerList, pair[0]));
            node.addOutEdge(Arrays.binarySearch(kmerList, pair[1]));
            directedGraph.addNode(node);
        }

        for (int i = 0; i < kmerList.length; i++) {
            if (!directedGraph.containsKey(i)) {
                directedGraph.addNode(new Graph.Directed.Node(i));
            }
        }
        directedGraph.build();

        Integer[] path = Euler.findFullPath(directedGraph);

        Dump.println("RESULT: ");

        String result = null;

        for (Integer i : path) {

            if (result == null) {
                result = kmerList[i];
            }
            else {
                result += kmerList[i].charAt(kmerList[i].length()-1);
            }
        }

        Dump.println(result);

    }

}
