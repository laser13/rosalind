package ru.pavlenov.bio.chapter.rosalind.probability;

import net.sf.jfasta.impl.FASTAFileReaderImpl;
import ru.pavlenov.bio.genome.Kmer;
import ru.pavlenov.bio.utils.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * ⓭ + 50
 * Какой сам? by Pavlenov Semen 03.07.14.
 * Matching Random Motifs
 * http://rosalind.info/problems/rstr/
 *
 * Given: A positive integer N≤100000, a number x between 0 and 1, and a DNA string s of length at most 10 bp.
 *
 * Return: The probability that if N random DNA strings having the same length as s are constructed with GC-content x
 *         (see “Introduction to Random Strings”), then at least one of the strings equals s.
 *         We allow for the same random string to be created more than once.
 *
 *
 */
public class Rstr {

    public static void start() throws IOException {

        int n = 94040;
        double contentGC = 0.503609d;
        double probAT = (1 - contentGC) / 2.0d;
        double probGC = contentGC / 2.0d;

        String kmer = "TAACCACCTC";

        Map<Character, Integer> counts = Kmer.calcCounts(kmer);

        Dump.println(counts);

        // Считаем вероятность получения такой строки (это просто произведение всех вероятностей по очереди)
        double probText = Math.pow(probAT, counts.get('A') + counts.get('T')) * Math.pow(probGC, counts.get('G') + counts.get('C'));

        Dump.println(probText);

        // Так как считать все вероятности по формуле бернулли C(n,k)*p^k*(1-p)^(n-k), не охота
        // то можно поступить так, найдём вероятность что данная строка вообще не встретилась и найдём вероятность дополнения к этому событию (1 - P)
        // k = 0, C(n, k) = 1, p^0 = 1

        Double powP = Math.pow(probText, 0);
        Double powQ = Math.pow(1 - probText, n);

        Dump.println(powP);
        Dump.println(powQ);

        Dump.println(String.format("%.3f", 1 - powQ * powP).replace(",", "."));

    }

}