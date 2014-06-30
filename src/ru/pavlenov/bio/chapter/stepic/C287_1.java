package ru.pavlenov.bio.chapter.stepic;

import ru.pavlenov.bio.utils.Dump;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Какой сам ☭
 * User: Павленов Семён
 * Date: 07.01.14 17:39
 */
public class C287_1 {

    public static BufferedWriter out;

    static {
        try {
            out = new BufferedWriter(new FileWriter("/home/laser13/IdeaProjects/bio/genome/answer_287_1.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void start() throws IOException {

        String data = ru.pavlenov.bio.utils.File.readFile("/home/laser13/IdeaProjects/bio/genome/dataset_88_1.txt", Charset.defaultCharset());
//        data = data.substring(1, data.length()-1);
        String[] dataArr = data.split(" ");

        Integer[] p = new Integer[dataArr.length];

        for (int i = 0; i < dataArr.length; i++) {
            p[i] = Integer.valueOf(dataArr[i]);
        }

        int b = 0;
        for (int i = 0; i < p.length; i++) {

            int prev;
            if (i == 0) prev = 0;
            else prev = p[i-1];

            if (prev + 1 != p[i]) b++;

        }

        Dump.println(b);
        Dump.println("-----");


    }

}
