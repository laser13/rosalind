package ru.pavlenov.bio.chapter.stepic;

import ru.pavlenov.bio.amino.Peptide;
import ru.pavlenov.bio.utils.IntegerArray;
import ru.pavlenov.bio.utils.StringArray;

import java.util.Arrays;

/**
 * Какой сам ⚝
 * Author: Pavlenov Semen
 * Date: 19.11.13
 * Time: 7:30
 * <p>
 * E = mc^2
 */
public class C20_2 {

//    public static void start() {
//
//
//        String peptideText = "VKLFPWFNQY";
//        Integer[] peptimeMass = new Integer[]{103,137,71,131,114,113,113,115,99,97};
//        int len = peptideText.length();
//        Peptide peptide = new Peptide(peptimeMass);
//
//        peptideText = peptide.get();
//
//        StringArray sub = new StringArray(len * (len - 1) + 1, peptideText);
//
//        for (int i = 1; i < len; i++) {
//
//            int rs = i - 1;
//            String text0;
//
//            text0 = peptideText + peptideText.substring(0, rs);
//
//            for (int j = 0; j <= text0.length() - i; j++) {
//
//                sub.print(text0.substring(j, j + i));
//
//            }
//        }
//
////        System.out.println(sub.asArray().length);
////        System.out.println(Arrays.asList(sub.asArray()));
//
//        String[] arr = sub.asArray();
//        IntegerArray result = new IntegerArray(arr.length + 1, 0);
//        for (String a: arr) {
//
//            Peptide p = new Peptide(a);
//            result.print(p.getMass());
//
//        }
//
////        System.out.println(result.asArray().length);
////        System.out.println(Arrays.asList(result.sort().asArray()));
//
//        Integer[] r = (Integer[]) result.sort().asArray();
//
//        for (int i: r) {
//            System.out.print(i);
//            System.out.print(" ");
//        }
//        System.out.println();
//
//    }

}
