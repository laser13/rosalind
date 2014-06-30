package ru.pavlenov.bio.chapter.stepic;

import com.google.common.base.Joiner;
import ru.pavlenov.bio.graph2.Trie;
import ru.pavlenov.bio.graph2.TrieEdge;
import ru.pavlenov.bio.graph2.TrieGraph;
import ru.pavlenov.bio.graph2.TrieNode;
import ru.pavlenov.bio.utils.Dump;
import ru.pavlenov.bio.utils.File;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Какой сам ☭
 * User: Павленов Семён
 * Date: 19.01.14 16:22
 */
public class C296_5 {

    /**
     * Suffix Trees
     * https://stepic.org/Bioinformatics-Algorithms-2/Suffix-Trees-296/step/5
     *
     * Longest Shared Substring Problem: Find the longest Substring shared by two strings.
     * Input: Strings Text1 and Text2.
     * Output: The longest substring that occurs in both Text1 and Text2.
     *
     */
    public static void start() throws IOException {

        String[] data = File.readFile("/home/laser13/IdeaProjects/bio/src/ru/pavlenov/bio/chapter/stepic/C296_5.data", Charset.defaultCharset()).split("\n");
        String text1 = data[0];
        String text2 = data[1];

        chit(text1, text2);

        return;

//        String text = text1.substring(2500, 8000) + text2.substring(2000, 7000) + "$";
//
//        int textLen = text.length();
//        int size = 0;
//
////        Dump.println(text + "|" + textLen);
//
//        String[] subArr = new String[text.length()];
//
//        for (int i = 0; i < text.length(); i++) {
//            subArr[i] = text.substring(i);
//            size += subArr[i].length();
//        }
////        Arrays.sort(subArr);
//        Dump.println("Make subtext array: " + textLen);
//
//        Trie trie = new Trie(textLen);
//
//        int counter = 0;
//        long start = System.currentTimeMillis();
//        for (String substring : subArr) {
//
//            trie.addString(substring);
//
//            if (counter % 1000 == 0) {
//                Dump.println(counter + ": " + (System.currentTimeMillis() - start) + "ms.");
//                start = System.currentTimeMillis();
//            }
//
//            counter++;
//
//
//        }
//
//        Dump.line();
//
//        String maxSub = "";
//        int maxLen = 0;
//
//        for (int i = 0; i < trie.getNodes().size(); i++) {
//
//            TrieNode node = trie.getNodes().get(i);
//            int[][] edges = node.getEdges();
//
//            int eSize = 0;
//            for (int[] edge : edges) {
//                if (edge[0] > 0 && edge[1] > 0) eSize += 1;
//            }
//
//            if (eSize > 1) {
//
//                int[] path = trie.upFrom(i);
//
//                if (path.length > maxLen) {
//                    String candidate = "";
//
//                    for (int val : path) {
//                        candidate = (char) val + candidate;
//                    }
//
//                    if (text1.contains(candidate) && text2.contains(candidate)) {
//                        maxLen = path.length;
//                        maxSub = candidate;
////                        Dump.println(candidate);
//                    }
//                }
//
//            }
//
//        }
//
//        Dump.println(maxSub);

    }

    public static void chit(String str1, String str2) {

        int minLen = 10;
        int maxLen = 22;

        for (int len = minLen; len < maxLen; len++) {

            boolean next = false;
            for (int i = 0; i < str1.length() - len + 1; i++) {
                String sub1 = str1.substring(i, i + len);

                for (int j = 0; j < str1.length() - len + 1; j++) {

                    String sub2 = str2.substring(j, j + len);

                    if (sub1.equals(sub2)) {
                        Dump.println(sub1 + " : " + len);
                        next = true;
                        break;
                    }

                }

                if (next) break;

            }

        }

    }

}
