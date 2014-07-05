package ru.pavlenov.bio.chapter.stepic;

import ru.pavlenov.bio.utils.*;

import java.io.*;
import java.nio.charset.Charset;

/**
 * Какой сам ☭
 * User: Павленов Семён
 * Date: 07.01.14 15:27
 */
public class C286_2 {

    public static BufferedWriter out;

    static {
        try {
            out = new BufferedWriter(new FileWriter("/home/laser13/IdeaProjects/bio/genome/answer_286_2.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void start() throws IOException {

        String data = ru.pavlenov.bio.utils.File.readFile("/home/laser13/IdeaProjects/bio/genome/data", Charset.defaultCharset());
        data = data.substring(1, data.length()-1);
        String[] dataArr = data.split(" ");

        Dump.println(dataArr);

        Integer[] p = new Integer[dataArr.length];

        for (int i = 0; i < dataArr.length; i++) {
            p[i] = Integer.valueOf(dataArr[i]);
        }

        Dump.println(p.length);
        Dump.println("-----");

        greedySorting(p);

    }

    public static void greedySorting(Integer[] p) throws IOException {

        int dist = 0;

        for (int i = 1; i <= p.length; i++) {
            int index = i - 1;
            if (p[index] != i && p[index] != -i) {
                p = reversal(p, i);
                dist += 1;
//                out.write(Dump.printfln(p)); out.newLine();
//                out.flush();
            }

            try {
                if (p[index] == -i) {
                    p[index] = i;
                    dist += 1;
    //                out.write(Dump.printfln(p)); out.newLine();
    //                out.flush();
                }
            }
            catch (NullPointerException e) {
                Dump.println(index);
                Dump.println(p[index]);
            }

        }

        Dump.println(dist);

    }

    public static Integer[] reversal(Integer[] p, int v) {
        Integer[] result = new Integer[p.length];
        int vIndex = -1;

        for (int i = 0; i < p.length; i++) {
            int el = p[i];
            if (i < v - 1) result[i] = el;
            else if (vIndex == -1) {
                if (v == el || v == -el) vIndex = i;
            }
            else {
                result[i] = el;
            }
        }

        int c = 0;
        for (int i = v - 1; i <= vIndex; i++) {
            result[vIndex - c] = -p[i];
            c++;
        }

        return result;
    }

}
