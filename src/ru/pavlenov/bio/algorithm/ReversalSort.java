package ru.pavlenov.bio.algorithm;

import ru.pavlenov.bio.utils.Dump;

import java.io.IOException;

/**
 * Created by laser13 on 26.06.14.
 */
public class ReversalSort {

    public static void greedySorting(Integer[] p) throws IOException {

        int dist = 0;

        for (int i = 1; i <= p.length; i++) {
            int index = i - 1;
            if (p[index] != i && p[index] != -i) {
                p = reversal(p, i);
                dist += 1;
//                out.write(Dump.printfln(p)); out.newLine();
//                out.flush();
            }

            try {
                if (p[index] == -i) {
                    p[index] = i;
                    dist += 1;
                    //                out.write(Dump.printfln(p)); out.newLine();
                    //                out.flush();
                }
            }
            catch (NullPointerException e) {
                Dump.println(index);
                Dump.println(p[index]);
            }

        }

        Dump.println(dist);

    }

    public static Integer[] reversal(Integer[] p, int v) {

        Integer[] result = new Integer[p.length];
        int vIndex = -1;

        for (int i = 0; i < p.length; i++) {
            int el = p[i];
            if (i < v - 1) result[i] = el;
            else if (vIndex == -1) {
                if (v == el || v == -el) vIndex = i;
            }
            else {
                result[i] = el;
            }
        }

        int c = 0;
        for (int i = v - 1; i <= vIndex; i++) {
            result[vIndex - c] = -p[i];
            c++;
        }

        return result;
    }

}
