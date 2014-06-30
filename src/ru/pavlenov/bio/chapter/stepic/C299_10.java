package ru.pavlenov.bio.chapter.stepic;

import ru.pavlenov.bio.utils.Dump;
import ru.pavlenov.bio.utils.File;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Какой сам ⚝
 * Author: Pavlenov Semen
 * Date: 23.01.14
 * <p>
 * E = mc^2
 */
public class C299_10 {

    public static void start() throws IOException {

        String[] data = File.readFile("/home/semen/IdeaProjects/bio/src/ru/pavlenov/bio/chapter/stepic/C299_10.data", Charset.defaultCharset()).split("\n");
        String bwtText = data[0];
        int len = bwtText.length();
        Dump.println(len);

        char[] bwtFirstArr = bwtText.toCharArray();
        char[] bwtLastArr = bwtText.toCharArray();

        String[] bwtFirstArr2 = new String[len];
        String[] bwtLastArr2 = new String[len];

        Arrays.sort(bwtFirstArr);

//        Dump.println(bwtFirstArr);
//        Dump.println(bwtLastArr);

        int aCounter = 0;
        int cCounter = 0;
        int gCounter = 0;
        int tCounter = 0;
        for (int i = 0; i < len; i++) {
            if (bwtFirstArr[i] == '$') bwtFirstArr2[i] = "$";
            if (bwtFirstArr[i] == 'A') {
               bwtFirstArr2[i] = "A" + aCounter;
               aCounter++;
            }
            if (bwtFirstArr[i] == 'C') {
                bwtFirstArr2[i] = "C" + cCounter;
                cCounter++;
            }
            if (bwtFirstArr[i] == 'G') {
                bwtFirstArr2[i] = "G" + gCounter;
                gCounter++;
            }
            if (bwtFirstArr[i] == 'T') {
                bwtFirstArr2[i] = "T" + tCounter;
                tCounter++;
            }
        }

        aCounter = 0;
        cCounter = 0;
        gCounter = 0;
        tCounter = 0;
        for (int i = 0; i < len; i++) {
            if (bwtLastArr[i] == '$') bwtLastArr2[i] = "$";
            if (bwtLastArr[i] == 'A') {
                bwtLastArr2[i] = "A" + aCounter;
                aCounter++;
            }
            if (bwtLastArr[i] == 'C') {
                bwtLastArr2[i] = "C" + cCounter;
                cCounter++;
            }
            if (bwtLastArr[i] == 'G') {
                bwtLastArr2[i] = "G" + gCounter;
                gCounter++;
            }
            if (bwtLastArr[i] == 'T') {
                bwtLastArr2[i] = "T" + tCounter;
                tCounter++;
            }
        }

//        Dump.println(bwtFirstArr2);
//        Dump.println(bwtLastArr2);

        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            map.put(bwtFirstArr2[i], bwtLastArr2[i]);
        }

//        Dump.println(map);

        String result = "";
        int counter = 0;
        String key = bwtFirstArr2[0];
        while (counter < len) {

            result = key.substring(0, 1) + result;
            key = map.get(key);
            counter++;

        }

        Dump.println(result);

    }

}
