package ru.pavlenov.bio.chapter.stepic;

import ru.pavlenov.bio.utils.Dump;

/**
 * Какой сам ⚝
 * Author: Pavlenov Semen
 * Date: 19.12.13
 * <p>
 * E = mc^2
 */
public class C72_9 {

    public static void start() {

        int n = 16;
        int m = 18;

        String downStr = "4 2 4 4 0 1 4 4 0 1 4 4 0 3 1 4 4 2 2\n" +
                "4 1 0 3 1 4 3 4 3 2 1 3 4 4 4 1 0 2 1\n" +
                "2 3 0 4 2 0 1 0 1 3 0 4 1 2 4 1 4 4 3\n" +
                "2 4 1 4 0 0 2 4 0 3 2 2 1 4 3 2 0 4 2\n" +
                "0 2 0 2 1 2 1 1 3 0 3 1 1 4 1 4 4 3 4\n" +
                "1 4 0 4 1 1 1 4 2 0 4 4 4 4 1 0 4 1 1\n" +
                "1 3 1 1 2 0 3 2 0 3 4 3 2 4 3 1 4 1 2\n" +
                "0 0 4 2 2 1 3 0 2 4 2 4 2 0 2 0 3 1 2\n" +
                "0 2 4 4 3 0 1 3 2 0 0 1 4 4 3 0 4 2 2\n" +
                "0 0 1 2 1 0 0 3 1 4 3 4 2 1 1 4 4 2 3\n" +
                "3 4 4 1 2 1 2 4 0 0 2 1 1 3 0 3 4 0 2\n" +
                "3 1 4 1 0 0 3 4 1 3 2 0 0 3 4 2 0 0 3\n" +
                "4 0 0 2 3 2 1 2 4 0 2 2 1 0 4 3 0 3 0\n" +
                "2 3 1 1 2 1 0 3 2 3 2 0 4 1 3 1 0 1 3\n" +
                "1 2 0 1 2 2 2 2 2 1 3 3 4 0 2 2 4 3 2\n" +
                "1 4 0 0 2 3 1 1 2 0 0 2 3 0 2 3 4 3 0";

        String rightStr = "2 1 3 0 0 0 3 4 4 4 0 0 1 1 3 1 1 2\n" +
                "1 4 1 0 1 1 2 4 3 4 0 2 1 3 1 3 2 1\n" +
                "2 2 0 1 2 0 0 1 1 2 0 0 1 4 1 2 2 1\n" +
                "0 1 1 3 2 0 2 0 0 1 3 2 0 0 3 0 3 2\n" +
                "1 1 0 3 1 1 1 0 0 0 1 2 3 0 0 0 2 3\n" +
                "2 2 2 1 1 4 1 4 3 2 2 1 4 4 0 0 1 0\n" +
                "2 0 2 3 2 3 0 4 3 3 3 3 0 3 3 3 1 4\n" +
                "3 2 2 1 0 0 1 2 4 3 0 0 3 0 4 0 3 1\n" +
                "0 2 0 3 3 4 2 1 1 4 0 4 3 3 2 1 4 3\n" +
                "3 3 4 0 3 4 2 1 3 1 1 4 0 0 4 4 0 2\n" +
                "3 3 0 0 4 0 1 1 4 4 3 1 2 2 2 2 4 0\n" +
                "0 3 3 1 4 3 2 3 0 3 4 3 1 1 3 2 1 4\n" +
                "2 3 2 0 1 4 4 4 2 3 0 0 4 1 1 0 2 4\n" +
                "0 2 0 3 2 0 3 0 2 2 0 4 3 4 3 1 1 2\n" +
                "1 3 3 0 4 3 4 3 1 2 1 4 1 3 1 1 1 3\n" +
                "0 2 2 2 4 3 2 0 1 3 0 1 0 4 1 3 3 3\n" +
                "1 0 1 1 2 3 4 4 0 2 2 1 2 4 3 1 0 0";

        Integer[][] down = new Integer[n][m+1];
        Integer[][] right = new Integer[n+1][m];

        int i = 0;
        int j = 0;
        for (String row : downStr.split("\n")) {
            for (String v : row.split(" ")) {
                down[i][j] = Integer.valueOf(v);
                j++;
            }
            j = 0;
            i++;
        }

        i = 0;
        j = 0;
        for (String row : rightStr.split("\n")) {
            for (String v : row.split(" ")) {
                right[i][j] = Integer.valueOf(v);
                j++;
            }
            j = 0;
            i++;
        }

        Dump.println("down");
        Dump.println(down);
        Dump.println("right");
        Dump.println(right);

        Integer[][] s = new Integer[n+1][m+1];

        s[0][0] = 0;

        for (i = 1; i <= n; i++) {
            s[i][0] = s[i-1][0] + down[i-1][0];
        }

        for (j = 1; j <= m; j++) {
            s[0][j] = s[0][j-1] + right[0][j-1];
        }

        Dump.println("-----------------");
        Dump.println(s);

        for (i = 1; i <= n; i++) {

            for (j = 1; j <= m; j++) {

                int s1 = s[i-1][j] + down[i-1][j];
                int s2 = s[i][j-1] + right[i][j-1];

                s[i][j] = s1 > s2 ? s1 : s2;

                Dump.println(i + ":" + j + " = " + s[i][j]);

            }

        }


        Dump.println(s);


    }

}
