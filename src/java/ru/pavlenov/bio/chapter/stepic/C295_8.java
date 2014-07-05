package ru.pavlenov.bio.chapter.stepic;

import com.carrotsearch.sizeof.RamUsageEstimator;
import ru.pavlenov.bio.graph2.Trie;
import ru.pavlenov.bio.graph2.TrieEdge;
import ru.pavlenov.bio.graph2.TrieGraph;
import ru.pavlenov.bio.graph2.TrieNode;
import ru.pavlenov.bio.utils.Dump;
import ru.pavlenov.bio.utils.File;

import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Какой сам ☭
 * User: Павленов Семён
 * Date: 17.01.14 20:43
 */
public class C295_8 {

    /**
     * Preprocessing the Genome Instead
     * https://stepic.org/Bioinformatics-Algorithms-2/Preprocessing-the-Genome-Instead-295/step/8
     *
     * Longest Repeat Problem: Find the longest repeat in a string.
     * Input: A string Text.
     * Output: A longest repeat in Text, i.e., a longest substring of Text that appears in Text more than once.
     *
     */
    public static void start() throws IOException {


        String[] data = File.readFile("/home/laser13/IdeaProjects/bio/src/ru/pavlenov/bio/chapter/stepic/C295_8.data", Charset.defaultCharset()).split("\n");
        String text = data[0];

//        Arrays.sort(data);

        Dump.println(text.length());

        String[] subArr = new String[text.length()];

        for (int i = 0; i < text.length(); i++) {

            subArr[i] = text.substring(i);

        }

        Arrays.sort(subArr);

        Dump.println("Make subtext array");


//        TrieGraph trie = new TrieGraph(Integer.MAX_VALUE / 2);

        Trie trie = new Trie();

        int counter = 0;
        long start = System.currentTimeMillis();
        for (String substring : subArr) {

            trie.addString(substring);

            if (counter % 1000 == 0) {
                Dump.println(counter + ": " + (System.currentTimeMillis() - start) + "ms.");
                start = System.currentTimeMillis();
            }

            counter++;


        }

        Dump.line();
//        Dump.println(trie);



        int maxLevel = 0;
        int maxNode = 0;


        for (int i = 0; i < trie.getNodes().size(); i++) {

            TrieNode node = trie.getNodes().get(i);
            int[][] edges = node.getEdges();


            if (maxLevel >= node.getLevel()) continue;

            int size = 0;
            for (int[] edge : edges) {
                if (edge[0] > 0 && edge[1] > 0) size += 1;
            }

            if (size > 1) {
                maxNode = i;
                maxLevel = node.getLevel();
            }

        }

        Dump.println(maxLevel);
        Dump.println(maxNode);

        int[] path = trie.upFrom(maxNode);

        String answer = "";
        for (int val : path) {
            answer = (char) val + answer;
        }

        Dump.println(answer);


    }

}
