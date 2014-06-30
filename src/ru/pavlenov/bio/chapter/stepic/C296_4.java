package ru.pavlenov.bio.chapter.stepic;

import ru.pavlenov.bio.graph2.Trie;
import ru.pavlenov.bio.graph2.TrieEdge;
import ru.pavlenov.bio.graph2.TrieGraph;
import ru.pavlenov.bio.graph2.TrieNode;
import ru.pavlenov.bio.utils.Dump;
import ru.pavlenov.bio.utils.File;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Какой сам ☭
 * User: Павленов Семён
 * Date: 19.01.14 13:48
 */
public class C296_4 {

    /**
     * Suffix Trees
     * https://stepic.org/Bioinformatics-Algorithms-2/Suffix-Trees-296/step/4
     *
     * CODE CHALLENGE: Solve the Suffix Tree Construction Problem.
     * Input: A string Text.
     * Output: The edge labels of SuffixTree(Text). You may return these strings in any order.
     *
     */
    public static void start() throws IOException {

        String[] data = File.readFile("/home/laser13/IdeaProjects/bio/src/ru/pavlenov/bio/chapter/stepic/C296_4.data", Charset.defaultCharset()).split("\n");
        String text = data[0];//.replace("$", "");

        int textLen = text.length();

        Dump.println(text + "|" + textLen);

        String[] subArr = new String[text.length()];

        for (int i = 0; i < text.length(); i++) subArr[i] = text.substring(i);
        Arrays.sort(subArr);
        Dump.println("Make subtext array");

        Trie trie = new Trie();

        int counter = 0;
        long start = System.currentTimeMillis();
        for (String substring : subArr) {

            trie.addString(substring);

            if (counter % 100 == 0) {
                Dump.println(counter + ": " + (System.currentTimeMillis() - start) + "ms.");
                start = System.currentTimeMillis();
            }

            counter++;


        }

//        Dump.println(trie);
        Dump.line();

        List<TrieNode> nodes = trie.getNodes();
        List<String> edges = new ArrayList<>();

        for (int i = 1; i < nodes.size(); i++) {

            TrieNode node = nodes.get(i);

//            Dump.println(node);

            if (node.countEdges() == 1) continue;

            int parent = node.getParent();
            List<Character> path = trie.upFrom(i, true);

            String edge = trie.toString(path);

            edges.add(edge);

        }



        BufferedWriter logWriter = new BufferedWriter(new FileWriter("/home/laser13/IdeaProjects/bio/src/ru/pavlenov/bio/chapter/stepic/C296_4.answer", true));
        for (String edge : edges) {

            logWriter.write(edge + "\n");
            logWriter.flush();

        }

    }

}
