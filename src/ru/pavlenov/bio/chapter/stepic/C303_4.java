package ru.pavlenov.bio.chapter.stepic;

import ru.pavlenov.bio.algorithm.BWT;
import ru.pavlenov.bio.utils.Dump;
import ru.pavlenov.bio.utils.File;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Какой сам ⚝
 * Author: Pavlenov Semen
 * Date: 28.01.14
 * <p>
 * E = mc^2
 */
public class C303_4 {

    /**
     * Burrows and Wheeler Set Up Checkpoints
     * https://stepic.org/Bioinformatics-Algorithms-2/Burrows-and-Wheeler-Set-Up-Checkpoints-303/step/4
     *
     * CODE CHALLENGE: Solve the Multiple Pattern Matching Problem.
     * Input: A string Text followed by a collection of strings Patterns.
     * Output: All starting positions in Text where a string from Patterns appears as a substring.
     *
     * @throws IOException
     */
    public static void start() throws IOException {

        String[] data = File.readFile("/home/semen/IdeaProjects/bio/src/ru/pavlenov/bio/chapter/stepic/C303_4.small", Charset.defaultCharset()).split("\n");
        String text = data[0];
        int strLen = text.length();
        Dump.println(strLen);


        BWT bwt = new BWT();

        bwt.setText(text + "$");

        int[] suffix = bwt.getSuffix();

        for (int i = 1; i < data.length; i++) {

//            Dump.println(data[i]);

            int[] match = bwt.matching(data[i]);

            if (match == null) continue;
            for (int j = match[0]; j <= match[1]; j++) {

                Dump.print(suffix[j] + " ");

            }

        }

        Dump.ln();

    }

}
