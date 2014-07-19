package ru.pavlenov.bio.chapter.stepic;

import ru.pavlenov.bio.graph.*;
import ru.pavlenov.bio.graph2.DefaultEdge;
import ru.pavlenov.bio.graph2.UndirectedGraph;
import ru.pavlenov.bio.utils.Dump;
import ru.pavlenov.bio.utils.File;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;

/**
 * Какой сам ⚝
 * Author: Pavlenov Semen
 * Date: 15.01.14
 * <p>
 * E = mc^2
 */
public class C288_1 {

    /**
     * Computing the 2-Break Distance
     * https://stepic.org/Bioinformatics-Algorithms-2/Computing-the-2-Break-Distance-288/step/1
     *
     * CODE CHALLENGE: Solve the 2-Break Distance Problem.
     * Input: Genomes P and Q.
     * Output: The 2-break distance d(P, Q).
     */
    public static void start() throws IOException {

        String[] data = File.readFile("/home/semen/IdeaProjects/bio/src/ru/pavlenov/bio/chapter/stepic/C288_1.data", Charset.defaultCharset()).split("\n");
        String str1 = data[0];
        String str2 = data[1];

        String[] strLoop1 = str1.substring(1, str1.length() - 1).split("\\)\\(");
        String[] strLoop2 = str2.substring(1, str2.length() - 1).split("\\)\\(");

        Integer edgeCounter = 0;
        Integer nodeCounter = 0;
        HashMap<String, Integer> nodeMap = new HashMap<>();

//        Graph graph = new Graph();

        UndirectedGraph<Integer, DefaultEdge<String, Integer>> graph = new UndirectedGraph<>();

        for (String loop : strLoop1) {

            String[] elems = loop.split(" ");

            for (int i = 0; i < elems.length; i++) {

                String curEl = elems[i];
                String nextEl;
                if (i < elems.length - 1)
                    nextEl = elems[i+1];
                else
                    nextEl = elems[0];

                String edgeValue = curEl;
                if (nextEl.charAt(0) == '+') nextEl = nextEl.replace("+", "-");
                else if (nextEl.charAt(0) == '-') nextEl = nextEl.replace("-", "+");
                edgeValue += nextEl;

                Integer currNode;
                if (!nodeMap.containsKey(curEl)) {
                    nodeMap.put(curEl, nodeCounter++);
                }
                currNode = nodeMap.get(curEl);

                Integer nextNode;
                if (!nodeMap.containsKey(nextEl)) {
                    nodeMap.put(nextEl, nodeCounter++);
                }
                nextNode = nodeMap.get(nextEl);

                graph.addEdge(currNode, nextNode, new DefaultEdge<>(edgeValue));


            }

        }

        for (String loop : strLoop2) {

            String[] elems = loop.split(" ");

            for (int i = 0; i < elems.length; i++) {

                String curEl = elems[i];
                String nextEl;
                if (i < elems.length - 1)
                    nextEl = elems[i+1];
                else
                    nextEl = elems[0];

                String edgeValue = curEl;
                if (nextEl.charAt(0) == '+') nextEl = nextEl.replace("+", "-");
                else if (nextEl.charAt(0) == '-') nextEl = nextEl.replace("-", "+");
                edgeValue += nextEl;

                Integer currNode;
                if (!nodeMap.containsKey(curEl)) {
                    nodeMap.put(curEl, nodeCounter++);
                }
                currNode = nodeMap.get(curEl);

                Integer nextNode;
                if (!nodeMap.containsKey(nextEl)) {
                    nodeMap.put(nextEl, nodeCounter++);
                }
                nextNode = nodeMap.get(nextEl);

                graph.addEdge(currNode, nextNode, new DefaultEdge<>(edgeValue));

            }

        }

        Dump.println("Graph complite!");

        int node = 0;
        int cycle = 1;

        Map<Integer, List<DefaultEdge<String, Integer>>> nodes = graph.getNodes();
        while (!nodes.isEmpty()) {
            DefaultEdge<String, Integer> edge = graph.edgeOfAndPassed(node);
            nodes = graph.getNodes();
            if (edge == null) {
                cycle++;
                node = nodes.keySet().iterator().next();
            }
            else {
                node = edge.getOtherNode(node);
            }
        }

        Dump.println("\n\n==========================");
        Dump.println(cycle);

    }

}
