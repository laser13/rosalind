package ru.pavlenov.bio.chapter.stepic;

import ru.pavlenov.bio.assemble.Base;
import ru.pavlenov.bio.assemble.Euler;
import ru.pavlenov.bio.assemble.Graph;
import ru.pavlenov.bio.utils.Dump;
import ru.pavlenov.bio.utils.Gen;

import java.util.*;

/**
 * Created by semen on 10.12.13.
 */
public class C57_10 {

    public static void start() {

        byte[][] perm = Gen.permutation(2, 4);

        String[] data0 = new String[perm.length];

        int counter = 0;
        for (byte[] el : perm) {

            String str = "";

            for (byte b : el) {
                str += b;
            }
            data0[counter] = str.replaceAll("1", "0").replaceAll("2", "1");
            counter++;
        }

        Arrays.sort(data0);

        HashMap<String, ArrayList<String>> overlapGraph = Base.getDeBruijnGraph(data0);

        Dump.println(data0);
        Dump.println(overlapGraph);

        SortedSet<String> sortedSet = new TreeSet<>();
        for (Map.Entry<String, ArrayList<String>> pair : overlapGraph.entrySet()) {
            for (String link : pair.getValue()) {
                sortedSet.add(link);
            }
        }

        Dump.println(sortedSet);

        String[] data = sortedSet.toArray(new String[sortedSet.size()]);


        Graph.Directed  directedGraph = new Graph.Directed();

        for (Map.Entry<String, ArrayList<String>> pair : overlapGraph.entrySet()) {
            Graph.Directed.Node node = new Graph.Directed.Node(Arrays.binarySearch(data, pair.getKey()));
            for (String link : pair.getValue()) {
                node.addOutEdge(Arrays.binarySearch(data, link));
            }
            directedGraph.addNode(node);
        }

        directedGraph.build();

        Dump.println(directedGraph);

        ArrayList<Graph.Directed.Node> fullCycle = Euler.findFullCycle(directedGraph, directedGraph.get(4), null);

        for (Graph.Directed.Node node : fullCycle) {

            Dump.print(node.getIndex() + ", ");

        }

        Dump.println("");

        String result = null;

        String[] edge = new String[fullCycle.size() - 1];
        Graph.Directed.Node prevNode = null;
        int i = 0;
        for (Graph.Directed.Node node : fullCycle) {

            if (prevNode != null) {
                edge[i] = data[prevNode.getIndex()] + data[node.getIndex()].charAt(data[node.getIndex()].length()-1);
                i++;
            }
            prevNode = node;

        }

        Dump.println(edge);

        for (String item : edge) {

            if (result == null) {
                result = item;
            }
            else {
                result += item.charAt(item.length()-1);
            }
        }

        Dump.println(result);
        Dump.println("0000110010111101");

    }

}
