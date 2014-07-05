package ru.pavlenov.bio.chapter.stepic;

import ru.pavlenov.bio.utils.Dump;

import java.util.Arrays;

/**
 * Created by semen on 11.12.13.
 */
public class C58_6 {

    public static void start() {

        int k = 3;
        int d = 2;
        String text = "TAATGCCATGGGATGTT";

        String[] result = new String[text.length() - 2*k - d + 1];

        for (int i = 0; i < text.length() - 2*k - d + 1; i++) {

            result[i] = text.substring(i, i + k) + text.substring(i + k + d, i + k*2 + d);

        }

        Arrays.sort(result);

        Dump.println(result);

        for (String pair : result) {
            Dump.print("("+pair.substring(0,3)+"|"+pair.substring(3,6)+") ");
        }

    }

}
