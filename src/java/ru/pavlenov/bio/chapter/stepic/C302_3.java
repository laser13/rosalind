package ru.pavlenov.bio.chapter.stepic;

import ru.pavlenov.bio.algorithm.BWT;
import ru.pavlenov.bio.utils.Dump;
import ru.pavlenov.bio.utils.File;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Какой сам ⚝
 * Author: Pavlenov Semen
 * Date: 27.01.14
 * <p>
 * E = mc^2
 */
public class C302_3 {

    public static void start() throws IOException {

        String[] data = File.readFile("/home/semen/IdeaProjects/bio/src/ru/pavlenov/bio/chapter/stepic/C302_3.data", Charset.defaultCharset()).split("\n");
        String text = data[0];
        Integer k = Integer.valueOf(data[1]);
        int strLen = text.length();
        Dump.println(strLen);

        BWT bwt = new BWT();

        bwt.setText(text);

        bwt.partial(k);
        Dump.ln();

    }

}
