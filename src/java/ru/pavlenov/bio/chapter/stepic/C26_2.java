package ru.pavlenov.bio.chapter.stepic;

import ru.pavlenov.bio.utils.Convert;
import ru.pavlenov.bio.utils.Dump;
import ru.pavlenov.bio.utils.Sort;

import java.util.HashMap;
import java.util.Map;

/**
 * Какой сам ☭
 * User: Павленов Семён
 * Date: 20.11.13 21:14
 */
public class C26_2 {

    public static void start() {

        String tmp = "0 672 669 1075 761 690 464 579 344 841 1142 1291 633 1204 438 474 882 399 853 879 301 637 766 303 1275 985 216 595 113 781 668 1191 1086 1249 458 87 273 601 1248 486 1176 729 511 417 975 546 224 1261 1116 682 596 71 214 938 87 295 1275 1138 966 1174 367 568 725 759 1233 816 1305 1263 896 658 129 1215 300 396 924 325 287 0 1067 171 945 1089 466 704 1154 434 881 1158 1305 995 898 99 580 1199 276 382 158 612 1029 509 693 851 603 928 186 767 888 1037 805 270 57 963 295 521 204 1092 680 360 876 377 783 208 147 794 1146 387 1225 483 980 1148 480 137 1061 581 246 188 417 750 101 694 1018 481 904 333 945 1067 1059 57 213 1362 1002 424 114 163 1149 1062 220 782 557";
        Integer[] sp = Convert.from(tmp).toIntArray(" ");

        HashMap<Integer, Integer> t = new HashMap<>();

        for (Integer d1 : sp) {

            for (Integer d2 : sp) {

                int d = d1 - d2;

                if (d >= 57 && d <= 200) if (t.containsKey(d)) {
                    Integer v = t.get(d);
                    v++;
                    t.put(d, v);
                } else {
                    t.put(d, 1);
                }

            }

        }

        Dump.println(sp);

        Map sortedMap = Sort.byValue(t, -1);

        Dump.println(sortedMap);

//        Spectra.convolutionCyclopeptideSequencing(sp, 84, 16);

//        System.out.println("_____________________________________________");
//        System.out.println("147-99-114-57-129-87-71-137-87-101-129-147-57");

    }

}
