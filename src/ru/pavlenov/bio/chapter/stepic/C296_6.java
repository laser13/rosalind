package ru.pavlenov.bio.chapter.stepic;

import ru.pavlenov.bio.utils.Dump;
import ru.pavlenov.bio.utils.File;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Какой сам ☭
 * User: Павленов Семён
 * Date: 21.01.14 6:07
 */
public class C296_6 {

    /**
     * Suffix Trees
     * https://stepic.org/Bioinformatics-Algorithms-2/Suffix-Trees-296/step/6
     *
     * Shortest Non-Shared Substring Problem: Find the shortest substring of one string that does not appear in another string.
     * Input: Strings Text1 and Text2.
     * Output: The shortest substring of Text1 that does not appear in Text2.
     *
     */
    public static void start() throws IOException {

        String[] data = File.readFile("/home/laser13/IdeaProjects/bio/src/ru/pavlenov/bio/chapter/stepic/C296_6.data", Charset.defaultCharset()).split("\n");
        String text1 = data[0];
        String text2 = data[1];

        chit(text1, text2);

    }

    public static void chit(String str1, String str2) {

        int minLen = 1;
        int maxLen = 10;

        for (int len = maxLen; len > minLen; len--) {

            boolean next = false;
            for (int i = 0; i < str1.length() - len + 1; i++) {
                String sub1 = str1.substring(i, i + len);

                boolean notFind = true;
                for (int j = 0; j < str2.length() - len + 1; j++) {

                    String sub2 = str2.substring(j, j + len);

                    if (sub1.equals(sub2)) notFind = false;

                }

                if (notFind) {
                    Dump.println(sub1 + " : " + len);
                    break;
                }

            }

        }

    }

}
