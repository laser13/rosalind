package ru.pavlenov.bio.chapter.stepic;

import ru.pavlenov.bio.graph.Edge;
import ru.pavlenov.bio.graph.trie.TrieNode;
import ru.pavlenov.bio.graph2.DefaultEdge;
import ru.pavlenov.bio.graph2.TrieEdge;
import ru.pavlenov.bio.graph2.TrieGraph;
import ru.pavlenov.bio.utils.Dump;
import ru.pavlenov.bio.utils.File;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;

/**
 * Какой сам ☭
 * User: Павленов Семён
 * Date: 12.01.14 19:59
 */
public class C294_3 {

    public static void start() throws IOException {

        String[] data = File.readFile("/home/laser13/dev/java/rosalind/src/java/ru/pavlenov/bio/chapter/stepic/C294_3.data", Charset.defaultCharset()).split("\n");

        Arrays.sort(data);

        Dump.println(data);

        int rootNode = 0;

        TrieGraph trie = new TrieGraph();

//        TrieGraph trie = new TrieGraph();
//        TrieNode root = new TrieNode(rootIndex, rootIndex);
//
//        trie.addNode(root);

        int nodeCounter = 1;
        int edgeCounter = 0;

        for (String str : data) {

//            Dump.println(str);

            int node = rootNode;
            for (int level = 0; level < str.length(); level++) {

                String c = String.valueOf(str.charAt(level));
                List<TrieEdge> edges = trie.startOf(node);

                Dump.print("Level" + level + ": " + c + " > size:" + edges.size() + " | ");

                if (edges.isEmpty()) {
                    trie.addEdge(node, nodeCounter, new TrieEdge(c, level));
                    Dump.print(node + ">" + nodeCounter);
                    node = nodeCounter;
                    nodeCounter++;
                }
                else {
                    boolean exist = false;
                    for (TrieEdge edge : edges) {
                        if (edge.getData().equals(c)) {

                            Dump.print(node + ">" + edge.getTargetNode() + " [" + edge.getData() + "]");

                            node = edge.getTargetNode();
                            exist = true;
                            break;
                        }
                    }

                    if (!exist) {
                        trie.addEdge(node, nodeCounter, new TrieEdge(c, level));
                        Dump.print(node + ">" + nodeCounter);
                        node = nodeCounter;
                        nodeCounter++;
                    }

                }
                Dump.println("");
            }


        }


//        trie.build();

        Dump.println(trie.getNodes());
        Dump.println(trie.getEdges());

        List<TrieEdge> edges = trie.getEdges();

        List<TrieEdge> list = new ArrayList<>();
        list.addAll(edges);

        Collections.sort(list, TrieEdge::compareTo);

        for (TrieEdge edge : list) {
            Dump.println((edge.getSourceNode()) + " " + (edge.getTargetNode()) + " " + edge.getData());
        }

    }

}
