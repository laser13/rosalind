package ru.pavlenov.bio.chapter.stepic;

import ru.pavlenov.bio.amino.Peptide;
import ru.pavlenov.bio.amino.Spectra;
import ru.pavlenov.bio.utils.IntegerArray;
import ru.pavlenov.bio.utils.StringArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

/**
 * Какой сам ⚝
 * Author: Pavlenov Semen
 * Date: 19.11.13
 * Time: 8:35
 * <p>
 * E = mc^2
 */
public class C22_2 {

    public static void start() {

        ArrayList<Integer> spectrum = new ArrayList<>();

        String tmp = "71 97 99 103 113 113 114 115 131 137 196 200 202 208 214 226 227 228 240 245 299 311 311 316 327 337 339 340 341 358 408 414 424 429 436 440 442 453 455 471 507 527 537 539 542 551 554 556 566 586 622 638 640 651 653 657 664 669 679 685 735 752 753 754 756 766 777 782 782 794 848 853 865 866 867 879 885 891 893 897 956 962 978 979 980 980 990 994 996 1022 1093";

        for (String i : tmp.split(" ")) {
            spectrum.add(Integer.valueOf(i));
        }

        ArrayList<Integer> t = new ArrayList<>();
        Collections.addAll(t, 71, 137, 103, 97, 99, 115, 113, 113, 114, 131);

        Peptide peptide = new Peptide(t);

        System.out.println(Arrays.asList(peptide.cyclospectrum()));

        Integer[] sp = spectrum.toArray(new Integer[spectrum.size()]);
        Arrays.sort(sp);

        Spectra.cyclopeptideSequencing(sp);

//        HashMap<String, Integer> list = new HashMap<>();
//        for (Integer m : mass) {
//            list.put(String.format("%s", m), m);
//        }
//
//        ArrayList<String> result = new ArrayList<>();
//
//        while (!list.isEmpty()) {
//
//            list = expand(list);
//
//            ArrayList<String> removeKey = new ArrayList<>();
//            ArrayList<String> successKey = new ArrayList<>();
//
//            list.forEach((key, val) -> {
//
//                if (spectrum.contains(val)) {
//                    successKey.print(key);
//                }
//                else {
//                    removeKey.print(key);
//                    mass.remove(val);
//                }
//
//            });
//
//            for (String rKey : removeKey) {
//                list.remove(rKey);
//            }
//
//            if (!successKey.isEmpty()) {
//                result.clear();
//                result.addAll(successKey);
//            }
//
//            if (!list.isEmpty()) {
//                list = expand(list);
//            }
//
//        }


//        ArrayList<String> finalResult = new ArrayList<>();
//        for (String r : result) {
//
//            if (compare(cyclospectrum(r.split("-")), spectrum)) {
//                finalResult.print(r);
//            }
//
//        }
//
//
//
//        for (String i: finalResult) {
//            System.out.print(i);
//            System.out.print(" ");
//        }
//        System.out.println();
//
//        System.out.println(finalResult.size());


    }

}
