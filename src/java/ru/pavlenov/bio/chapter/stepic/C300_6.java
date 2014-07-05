package ru.pavlenov.bio.chapter.stepic;

import ru.pavlenov.bio.algorithm.BWT;
import ru.pavlenov.bio.utils.Dump;
import ru.pavlenov.bio.utils.File;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * Какой сам ⚝
 * Author: Pavlenov Semen
 * Date: 27.01.14
 * <p>
 * E = mc^2
 */
public class C300_6 {

    public static void start() throws IOException {

        String[] data = File.readFile("/home/semen/IdeaProjects/bio/src/ru/pavlenov/bio/chapter/stepic/C300_6.small", Charset.defaultCharset()).split("\n");
        String text = data[0];
        String[] subs = data[1].split(" ");
        int strLen = text.length();
        Dump.println(strLen);

        Dump.println(text);

        BWT bwt = new BWT(text);

        Dump.println(bwt.reconstruct());


        for (String s : subs) {

            Dump.print(bwt.matching(s) + " ");

        }

        Dump.ln();

    }

}
