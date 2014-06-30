package ru.pavlenov.bio.chapter.stepic;

import ru.pavlenov.bio.assemble.Base;
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
 * Date: 14.12.13 17:18
 */
public class C58_14 {

    public static void start() throws IOException {

        int d = 1000;
        int k = 0;

        String input = File.readFile("/home/semen/IdeaProjects/bio/genome/Carsonella-ruddii", Charset.defaultCharset());
        String[] inputList = input.split("\n");

        Dump.println(inputList.length);

        String[] list1 = new String[inputList.length];
        String[] list2 = new String[inputList.length];
        SortedSet<String> sortedSet = new TreeSet<>();
        SortedSet<String> sortedSet2 = new TreeSet<>();

        for (int i = 0; i < inputList.length; i++) {
            String[] pair = inputList[i].split("\\|");
            list1[i] = pair[0];
            list2[i] = pair[1];
            sortedSet.add(pair[0] + pair[1]);
            sortedSet2.add(Base.prefix(pair[0]));
            sortedSet2.add(Base.prefix(pair[1]));
            sortedSet2.add(Base.suffix(pair[0]));
            sortedSet2.add(Base.suffix(pair[1]));

        }

        k = list1[0].length();

        String[] sortedArray = sortedSet.toArray(new String[sortedSet.size()]);
        String[] sortedArray2 = sortedSet2.toArray(new String[sortedSet.size()]);

        Dump.println(sortedArray.length);

        HashMap<String[], ArrayList<String[]>> overlapGraph = Base.getOverlapGraph(list1, list2);
        HashMap<String, ArrayList<String>> deBruijnGraph = Base.getDeBruijnGraph(list1);

//        Dump.println(deBruijnGraph);

        Graph.Directed  directedGraph = new Graph.Directed();
        for (Map.Entry<String[], ArrayList<String[]>> pair : overlapGraph.entrySet()) {
            Graph.Directed.Node node = new Graph.Directed.Node(Arrays.binarySearch(sortedArray, pair.getKey()[0]+pair.getKey()[1]));
            for (String[] link: pair.getValue()) {
                node.addOutEdge(Arrays.binarySearch(sortedArray, link[0]+link[1]));
            }
            directedGraph.addNode(node);
        }

        directedGraph.buildFrom(deBruijnGraph, sortedArray2);

//        directedGraph.build();

//        Dump.println("directedGraph: " + directedGraph);

        Integer[] fullPath = Euler.findFullPath(directedGraph);

        Dump.println(fullPath);

//        String result1 = null;
//        String result2 = null;
//
//        for (int i : fullPath) {
//
//            if (result1 == null) result1 = sortedArray[i].substring(0, k);
//            else result1 += sortedArray[i].charAt(k-1);
//
//            if (result2 == null) result2 = sortedArray[i].substring(k,2*k);
//            else result2 += sortedArray[i].charAt(k*2-1);
//
////            Dump.println(sortedArray[i]);
//
//        }

//        Dump.println(result1);
//        Dump.println(result2);

//        assert result2 != null;
//        Dump.println(result1 + result2.substring(result1.length() - k - d, result1.length()));

    }

}
