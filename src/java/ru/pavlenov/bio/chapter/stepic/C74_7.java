package ru.pavlenov.bio.chapter.stepic;

import ru.pavlenov.bio.assemble.Graph;
import ru.pavlenov.bio.utils.Dump;

import java.util.*;

/**
 * Какой сам ☭
 * User: Павленов Семён
 * Date: 19.12.13 20:29
 */
public class C74_7 {

    public static void start() {

        int start = 9;
        int end = 35;

        String data = "27->35:5\n" +
                "6->20:15\n" +
                "0->33:39\n" +
                "9->20:21\n" +
                "7->12:38\n" +
                "14->18:9\n" +
                "5->11:13\n" +
                "5->10:8\n" +
                "5->13:9\n" +
                "4->17:3\n" +
                "0->16:5\n" +
                "28->29:4\n" +
                "3->7:3\n" +
                "3->4:9\n" +
                "5->30:8\n" +
                "22->33:28\n" +
                "1->25:34\n" +
                "14->24:38\n" +
                "27->28:25\n" +
                "1->22:19\n" +
                "4->27:32\n" +
                "26->32:10\n" +
                "15->25:32\n" +
                "19->26:35\n" +
                "5->20:5\n" +
                "13->15:13\n" +
                "17->27:13\n" +
                "14->27:25\n" +
                "12->15:37\n" +
                "19->28:28\n" +
                "0->29:27\n" +
                "6->33:11\n" +
                "9->10:38\n" +
                "9->34:0\n" +
                "0->20:6\n" +
                "21->33:16\n" +
                "2->30:20\n" +
                "2->18:26\n" +
                "15->20:18\n" +
                "22->27:12\n" +
                "10->13:37\n" +
                "28->35:10\n" +
                "5->22:27\n" +
                "18->20:6\n" +
                "5->24:8\n" +
                "11->17:9\n" +
                "11->16:3\n" +
                "22->28:29\n" +
                "6->8:27\n" +
                "15->19:38\n" +
                "4->35:8\n" +
                "5->19:22\n" +
                "8->32:38\n" +
                "0->2:22\n" +
                "4->16:25\n" +
                "13->27:23\n" +
                "10->27:32\n" +
                "17->31:19\n" +
                "3->30:12\n" +
                "3->15:21";

        String[] dataArr = data.split("\n");
        Arrays.sort(dataArr);

        HashSet<Integer> needed = new HashSet<>();
        needed.add(start);
        needed.add(end);
        HashMap<Integer, ArrayList<Integer[]>> dataHashMap = new HashMap<>();

        for (String item : dataArr) {
            String[] pair = item.split("->|:");

            int index = Integer.parseInt(pair[0]);
            int out = Integer.parseInt(pair[1]);
            int weight = Integer.parseInt(pair[2]);

            ArrayList<Integer[]> list = new ArrayList<>();
            list.add(new Integer[]{out, weight});

            if (dataHashMap.containsKey(index)) {
                dataHashMap.get(index).addAll(list);
            }
            else {
                dataHashMap.put(index, list);
            }
        }

        ArrayList<Integer> clearDate = new ArrayList<>();
        clear(start, dataHashMap, clearDate);

        Dump.println(clearDate);

        Graph.DirectedWithWeight directedGraph = new Graph.DirectedWithWeight();

        for (Integer index : clearDate) {
            ArrayList<Integer[]> list = dataHashMap.get(index);
            HashMap<Integer, Integer> hashMap = new HashMap<>();
            for (Integer[] pair : list) {
                hashMap.put(pair[0], pair[1]);
            }
            Graph.DirectedWithWeight.Node node = new Graph.DirectedWithWeight.Node(index, hashMap);
            directedGraph.addNode(node);
        }
        directedGraph.build();

//        Dump.println(directedGraph);

        ArrayList<Integer> topologicalSort = directedGraph.topologicalSort();

        Dump.println(topologicalSort);

        Graph.DirectedWithWeight directedGraph2 = new Graph.DirectedWithWeight();

        for (Integer index : clearDate) {
            ArrayList<Integer[]> list = dataHashMap.get(index);
            HashMap<Integer, Integer> hashMap = new HashMap<>();
            for (Integer[] pair : list) {
                hashMap.put(pair[0], pair[1]);
            }
            Graph.DirectedWithWeight.Node node = new Graph.DirectedWithWeight.Node(index, hashMap);
            directedGraph2.addNode(node);
        }
        directedGraph2.build();
        directedGraph2.setTopologicalSort(topologicalSort);

//        Dump.println(directedGraph2);

        directedGraph2.calcPrice();

//        Dump.println(directedGraph2);
//
//        for (Map.Entry<Integer, Graph.DirectedWithWeight.Vertex> pair : directedGraph2.getNodeList().entrySet()) {
//
//            Dump.println(pair.getKey() + " > " + pair.getValue().getPriceList());
//
//        }

        ArrayList<Integer> track = directedGraph2.backtrack(end);

        Dump.println(track);


        for (int i = track.size()-1; i >= 0; i--) {
            Dump.print(track.get(i) + "->");
        }

        Dump.println("");

        Dump.println(directedGraph2.getNode(end).getMaxPrice()[1]);

    }

    public static void clear(int index, HashMap<Integer, ArrayList<Integer[]>> data, ArrayList<Integer> result) {

        if (!data.containsKey(index)) {
            return;
        }
        result.add(index);
        for (Integer[] pair : data.get(index)) {
            clear(pair[0], data, result);
        }
    }

}
