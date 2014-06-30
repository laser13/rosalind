package ru.pavlenov.bio.chapter.rosalind.dinamic;

import org.apache.commons.lang.ArrayUtils;
import ru.pavlenov.bio.utils.Dump;
import ru.pavlenov.bio.utils.File;
import sun.dc.pr.PathFiller;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.*;

/**
 * Created by laser13 on 19.04.14.
 *
 * Longest Increasing Subsequence
 * http://rosalind.info/problems/lgis/
 *
 */
public class Lgis {

    public static void start() throws IOException {

        String[] data = File.readFile("/home/laser13/IdeaProjects/bio/src/ru/pavlenov/bio/chapter/rosalind/dinamic/Lgis.data", Charset.defaultCharset()).split("\n");
        int n = Integer.parseInt(data[0]);
        String[] tmp = data[1].split(" ");

        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(tmp[i]);
        }

        ArrayList<Integer> path1 = maxLen(n, a);

        Collections.reverse(path1);
        Dump.line();
        for (Integer index : path1) {
            Dump.print(a[index] + " ");
        }
        Dump.ln();

        ArrayUtils.reverse(a);
        ArrayList<Integer> path2 = maxLen(n, a);

        for (Integer index : path2) {
            Dump.print(a[index] + " ");
        }
        Dump.ln();

    }

    public static ArrayList<Integer> maxLen(int n, int[] a) {

        int[] d = new int[n];
        int[] p = new int[n];

        for (int i = 0; i < n; ++i) {
            d[i] = 1;
            p[i] = -1;
            for (int j = 0; j < i; ++j)
                if (a[j] < a[i])
                    if (1 + d[j] > d[i]) {
                        d[i] = 1 + d[j];
                        p[i] = j;
                    }
        }

        int ans = d[0],  pos = 0;
        for (int i=0; i<n; ++i)
            if (d[i] > ans) {
                ans = d[i];
                pos = i;
            }


        ArrayList<Integer> path = new ArrayList<>();
        while (pos != -1) {
            path.add(pos);
            pos = p[pos];
        }

        return path;

    }

}
