package ru.pavlenov.bio.chapter.rosalind.probability;

import ru.pavlenov.bio.utils.Convert;
import ru.pavlenov.bio.utils.Dump;

/**
 * Created by laser13 on 28.06.14.
 * Introduction to Random Strings
 * http://rosalind.info/problems/prob/
 *
 * Given: A DNA string s of length at most 100 bp and an array A containing at most 20 numbers between 0 and 1.
 * Return: An array B having the same length as A in which B[k] represents the common logarithm of the probability
 * that a random string constructed with the GC-content found in A[k] will match s exactly.
 *
 */
public class Prob {

    public static void start() {

        String text = "CTTTTATACAGAGGGAGCTGGAAGCGCTAGTGACTGTATAGTGGTAACTTCGGAGGGCAAGGCTGGTCGACGATTTATAGGGCTCTGCGTT";
        Float[] gc = Convert.from("0.064 0.115 0.193 0.248 0.306 0.337 0.414 0.446 0.508 0.580 0.631 0.682 0.731 0.789 0.877 0.915").toFloatArray(" ");

        int countAT = 0;
        int countGC = 0;

        for (char ch : text.toCharArray()) {
            if (ch == 'A' || ch == 'T') countAT++;
            if (ch == 'G' || ch == 'C') countGC++;
        }

        for (Float pr : gc) {

            double logAT = Math.log10((1 - pr) / 2);
            double logGC = Math.log10(pr / 2);

            double out = countAT * logAT + countGC * logGC;

            Dump.print(String.format("%.3f", out).replace(',', '.') + " ");

        }
        Dump.ln();



    }

}
