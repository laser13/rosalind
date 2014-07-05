package ru.pavlenov.bio.chapter.stepic;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Какой сам ☭
 * User: Павленов Семён
 * Date: 20.11.13 20:24
 */
public class C26_1 {

    public static void start() {


        ArrayList<Integer> spectrum = new ArrayList<>();
        String tmp = "631 260 391 381 577 358 735 158 680 990 1034 544 604 245 1034 567 876 478 935 963 763 664 218 99 257 87 767 457 490 963 299 1024 517 730 186 675 158 717 643 441 359 1008 131 71 762 446 473 200 848 344 87 354 935 861 386 1121 577 113 777 283 822 271 1034 554 864 664 1050 903 827 517 838 294 925 921 850 273 196 958 1022 740 386 0 163 186 87 604 97 648 404 457 544 735";
        for (String i : tmp.split(" ")) {
            spectrum.add(Integer.valueOf(i));
        }

        Integer[] data = spectrum.toArray(new Integer[spectrum.size()]);
        Arrays.sort(data);


//        Integer[] data = new Integer[]{0,137,186,323};

        ArrayList<Integer> t = new ArrayList<>();

        for (Integer d1 : data) {

            for (Integer d2 : data) {

                int d = d1 - d2;

                if (d > 0) {
                    t.add(d);
                }

            }

        }

        t.sort(Integer::compare);

//        System.out.println(t);

        for (Integer d : t) {
            System.out.print(d);
            System.out.print(" ");
        }

        System.out.println();


    }

}
