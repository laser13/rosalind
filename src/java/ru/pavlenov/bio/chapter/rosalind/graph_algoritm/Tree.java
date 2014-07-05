package ru.pavlenov.bio.chapter.rosalind.graph_algoritm;

import ru.pavlenov.bio.graph2.DefaultEdge;
import ru.pavlenov.bio.graph2.UndirectedGraph;
import ru.pavlenov.bio.utils.Convert;
import ru.pavlenov.bio.utils.Dump;
import ru.pavlenov.bio.utils.File;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created by laser13 on 27.06.14.
 * http://rosalind.info/problems/tree/
 * Completing a Tree
 *
 * Given: A positive integer n (n≤1000) and an adjacency list corresponding to a graph on n nodes that contains no cycles.
 *
 * Return: The minimum number of edges that can be added to the graph to produce a tree.
 *
 */
public class Tree {

    public static void start() throws IOException {

        String[] data = File.readFile("/home/laser13/IdeaProjects/bio/src/ru/pavlenov/bio/chapter/rosalind/graph_algoritm/Tree.data", Charset.defaultCharset()).split("\n");

        int n = Integer.parseInt(data[0]);

//        UndirectedGraph<Integer, DefaultEdge<Integer, Integer>> graph = new UndirectedGraph<>();
//
//        for (int i = 0; i < n; i++) {
//            graph.addNode(i+1);
//        }
//
//        for (int i = 1; i < data.length; i++) {
//
//            Integer[] link = Convert.from(data[i]).toIntArray(" ");
//            graph.addEdge(link[0], link[1], new DefaultEdge<Integer, Integer>(i));
//
//        }


        Dump.println(n - data.length);

    }

}
