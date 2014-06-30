package ru.pavlenov.bio.chapter.stepic;

import ru.pavlenov.bio.amino.Peptide;
import ru.pavlenov.bio.amino.Spectra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Какой сам ☭
 * User: Павленов Семён
 * Date: 19.11.13 19:00
 */
public class C24_1 {

    public static void start() {

        String tmp = "57 57 71 99 129 137 170 186 194 208 228 265 285 299 307 323 356 364 394 422 493";
        ArrayList<Integer> spectrum = new ArrayList<>();
        for (String i : tmp.split(" ")) {
            spectrum.add(Integer.valueOf(i));
        }

        Integer[] sp = spectrum.toArray(new Integer[spectrum.size()]);
        Arrays.sort(sp);

        Spectra.leaderCyclopeptideSequencing(sp, 26);

        System.out.println("_____________________________________________________________________________________________");
        System.out.println("156-71-113-114-131-156-113-101-129-128-128-114-128-103-97-131-131-113-131-113-128-115-128-113");

    }

}
