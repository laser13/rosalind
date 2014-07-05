package ru.pavlenov.bio.chapter.stepic;

import org.apache.commons.lang.StringUtils;
import ru.pavlenov.bio.utils.Dump;
import ru.pavlenov.bio.utils.File;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Какой сам ☭
 * User: Павленов Семён
 * Date: 19.01.14 21:02
 */
public class C310_3 {

    public static byte[] b;
    public static int[] pos;

    /**
     * Suffix Arrays
     * https://stepic.org/Bioinformatics-Algorithms-2/Suffix-Arrays-310/step/3
     *
     * Constructing Suffix Array Problem: Construct the suffix array of a string.
     * Input: A string Text.
     * Output: SuffixArray(Text).
     */
    public static void start() throws IOException {

        String[] data = File.readFile("/home/semen/IdeaProjects/bio/src/ru/pavlenov/bio/chapter/stepic/C310_3.data", Charset.defaultCharset()).split("\n");
        String text = data[0];
        int strLen = text.length();
        Dump.println(strLen);

        pos = new int[strLen];
        b = new byte[strLen];

        int counter = 0;
        for (Character c : text.toCharArray()) {
            if (c == 'A') b[counter] = 1;
            else if (c == 'C') b[counter] = 2;
            else if (c == 'G') b[counter] = 3;
            else if (c == 'T') b[counter] = 4;
            else if (c == '$') b[counter] = 0;
            counter++;
        }

        int bLen = b.length;
        for (int i = 0; i < bLen; i++) pos[i] = i;

        Dump.println("Prepare array");

        qSort(0, bLen-1);

        Dump.println("\n\nSort array");
//        Dump.println(pos);


        BufferedWriter logWriter = new BufferedWriter(new FileWriter("/home/semen/IdeaProjects/bio/src/ru/pavlenov/bio/chapter/stepic/C310_3.answer", true));
        for (int i = bLen - 1; i >= 0; i--) {

            logWriter.write(pos[i] + ", ");
            logWriter.flush();

        }

    }

    public static int compareTo(int i, int j) {

        int result = 0;
        int ii = pos[i];
        int jj = pos[j];
        int diff = jj - ii;

//        Dump.println("[" + ii + ":" + jj + "]");
//        Dump.println(getBytes(ii));
//        Dump.println(getBytes(jj));

        for (int k = ii; k < b.length; k++) {
            result = (b[k+diff] - b[k]);
            if (result != 0) {
                break;
            }
        }
        Dump.println(">>> " + result);
        Dump.line();
        return result;

    }

    public static int compareTo2(int i, int jj) {

        int result = 0;
        int ii = pos[i];
        int diff = jj - ii;

        for (int k = ii; k < b.length; k++) {
            result = (b[k+diff] - b[k]);
            if (result != 0) {
                break;
            }
        }
        return result;

    }

    public static void qSort(int low, int high) {
        int i = low;
        int x = pos[(low + high) / 2];
        int j = high;

//        Dump.println("[" + i + ":" + x + ":" + j + "]");

        do {
            while (compareTo2(i, x) < 0) ++i;
            while (compareTo2(j, x) > 0) --j;
            if (i <= j) {
                int temp = pos[i];
                pos[i] = pos[j];
                pos[j] = temp;
                i++ ; j--;
            }
        } while(i <= j);
        //рекурсивные вызовы функции qSort
        if (low < j) qSort(low, j);
        if (i < high) qSort(i, high);
    }

    public static String getBytes(int index) {
        StringBuilder builder = new StringBuilder();
        for (int i = index; i < b.length; i++) {
            builder.append(b[i]).append(',');
        }
        return builder.toString();
    }

}
