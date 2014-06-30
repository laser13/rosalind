package ru.pavlenov.bio.chapter.stepic;

import ru.pavlenov.bio.utils.Dump;
import ru.pavlenov.bio.utils.File;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * Какой сам ⚝
 * Author: Pavlenov Semen
 * Date: 22.01.14
 * <p>
 * E = mc^2
 */
public class C297_4 {

    /**
     * The Burrows-Wheeler Transform
     *
     * Burrows-Wheeler Transform Construction Problem: Construct the Burrows-Wheeler transform of a string.
     * Input: A string Text.
     * Output: BWT(Text).
     *
     * @throws IOException
     */
    public static void start() throws IOException {

        String[] data = File.readFile("/home/semen/IdeaProjects/bio/src/ru/pavlenov/bio/chapter/stepic/C297_4.small", Charset.defaultCharset()).split("\n");
        String text = data[0];
        int strLen = text.length();
        Dump.println(strLen);

        String[] bwtArray = new String[strLen];

        for (int i = 0; i < strLen; i++) {

            bwtArray[i] = text.substring(strLen - i) + text.substring(0, strLen - i);

        }

        Arrays.sort(bwtArray);

        for (String str : bwtArray) {
            Dump.print(str.substring(strLen - 1));
        }
        Dump.ln();


    }

}
