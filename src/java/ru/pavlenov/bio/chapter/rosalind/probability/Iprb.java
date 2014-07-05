package ru.pavlenov.bio.chapter.rosalind.probability;

import ru.pavlenov.bio.utils.Dump;

/**
 * Какой сам ☭
 * User: Павленов Семён
 * Date: 08.01.14 16:42
 */
public class Iprb {

    /**
     * Mendel's First Law
     * http://rosalind.info/problems/iprb/
     *
     * Given: Three positive integers k, m, and n, representing a population containing k+m+n organisms:
     * k individuals are homozygous dominant for a factor, m are heterozygous, and n are homozygous recessive.
     *
     * Return: The probability that two randomly selected mating organisms will produce an individual possessing
     * a dominant allele (and thus displaying the dominant phenotype). Assume that any two organisms can mate.
     *
     */
    public static void start() {

        int k = 19;
        int m = 26;
        int n = 29;

        float total = k + m + n;

        // Pr(AA,AA) - A(100%)
        float pKK = (k / total) * ((k - 1) / (total - 1));

        // Pr(Aa,Aa) - A(75%)
        float pMM = (m / total) * ((m - 1) / (total - 1)) * 0.75f;

        // Pr(AA, Aa) and Pr(Aa, AA) - A(100%)
        float pKM = 2 * (k / total) * ((m) / (total - 1));

        // Pr(AA, aa) and Pr(aa, AA) - A(100%)
        float pKN = 2 * (k / total) * ((n) / (total - 1));

        // Pr(Aa, aa) and Pr(aa, Aa) - A(50%)
        float pMN = 2 * (m / total) * ((n) / (total - 1)) * 0.5f;

        float pAll = pKK + pMM + pKM + pKN + pMN;

        Dump.println(pAll);

    }


}
