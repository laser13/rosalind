package ru.pavlenov.bio.chapter.stepic;

import ru.pavlenov.bio.graph2.TrieEdge;
import ru.pavlenov.bio.graph2.TrieGraph;
import ru.pavlenov.bio.utils.Dump;
import ru.pavlenov.bio.utils.File;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Какой сам ☭
 * User: Павленов Семён
 * Date: 17.01.14 18:27
 */
public class C294_6 {

    /**
     * Preprocessing Patterns into a Trie
     * https://stepic.org/Bioinformatics-Algorithms-2/Preprocessing-Patterns-into-a-Trie-294/step/6
     *
     * CODE CHALLENGE: Implement TRIEMATCHING to solve the Multiple Pattern Matching Problem.
     * Input: A string Text and a collection of strings Patterns.
     * Output: All starting positions in Text where a string from Patterns appears as a substring.
     *
     */
    public static void start() throws IOException {

        String[] data = File.readFile("/home/laser13/IdeaProjects/bio/src/ru/pavlenov/bio/chapter/stepic/C294_6.data", Charset.defaultCharset()).split("\n");
        String text = data[0];

//        Arrays.sort(data);

        Dump.println(data);

        int rootNode = 0;

        TrieGraph trie = new TrieGraph();

        int nodeCounter = 1;

        for (int i = 1; i < data.length; i++) {

            String substring = data[i];

//            Dump.println(str);

            int node = rootNode;
            for (int level = 0; level < substring.length(); level++) {

                boolean isLeaf = (level == substring.length() - 1);

                String c = String.valueOf(substring.charAt(level));
                List<TrieEdge> edges = trie.startOf(node);

//                Dump.print("Level" + level + ": " + c + " > size:" + edges.size() + " | ");

                if (edges.isEmpty()) {
                    trie.addEdge(node, nodeCounter, new TrieEdge(c, level, isLeaf));
//                    Dump.print(node + ">" + nodeCounter);
                    node = nodeCounter;
                    nodeCounter++;
                }
                else {
                    boolean exist = false;
                    for (TrieEdge edge : edges) {
                        if (edge.getData().equals(c)) {

//                            Dump.print(node + ">" + edge.getTargetNode() + " [" + edge.getData() + "]");

                            node = edge.getTargetNode();
                            exist = true;
                            break;
                        }
                    }

                    if (!exist) {
                        trie.addEdge(node, nodeCounter, new TrieEdge(c, level, isLeaf));
//                        Dump.print(node + ">" + nodeCounter);
                        node = nodeCounter;
                        nodeCounter++;
                    }

                }
//                Dump.println("");
            }


        }


//        Dump.println(trie.getNodes());
//        Dump.println(trie.getEdges());

        Dump.println("");
        trieMatching(text, trie);
        Dump.println("");

    }

    public static void trieMatching(String text, TrieGraph trie) {

        int counter = 0;
        while (!text.isEmpty()) {

            int node = 0;

            for (int i = 0; i < text.length(); i++) {

                String c = String.valueOf(text.charAt(i));
                List<TrieEdge> edges = trie.startOf(node);

                TrieEdge findEdge = null;
                for (TrieEdge edge : edges) {
                    if (edge.getData().equals(c)) {
                        findEdge = edge;
                        break;
                    }
                }



                if (findEdge == null) break;

//                Dump.print(c);

                if (findEdge.isLeaf()) Dump.print(counter + " ");

                node = findEdge.getTargetNode();

            }

            text = text.substring(1);
            counter++;

        }

    }


}
