package ru.pavlenov.bio.chapter.rosalind.probability;

import ru.pavlenov.bio.utils.Calc;
import ru.pavlenov.bio.utils.Dump;

import static org.apache.commons.math3.util.ArithmeticUtils.binomialCoefficient;

/**
 * Какой сам ☭
 * User: Павленов Семён
 * Date: 08.01.14 22:02
 */
public class Lia {

    /**
     * Independent Alleles
     * http://rosalind.info/problems/lia/
     *
     * Given: Two positive integers k (k≤7) and j (j≤2^k). In this problem, we begin with Tom, who in
     * the 0th generation has genotype Aa Bb. Tom has two children in the 1st generation, each of whom has two children,
     * and so on. Each organism always mates with an organism having genotype Aa Bb.
     *
     * Return: The probability that at least N Aa Bb organisms will belong to the k-th generation of Tom's family tree
     * (don't count the Aa Bb mates at each level). Assume that Mendel's second law holds for the factors.
     */
    public static void start() {

        // Кол-во поколений
        int k = 6;

        // Искомое число особей с генотипом AaBb >=j
        int j = 16;

        // Число особей в k-ом поколении
        int total = (int) Math.pow(2, k);

        Dump.println("total: " + total);

        double sum = 0;
        for (int i = 0; i < j; i++) {
            sum += bernoulli(i, total, 0.25);
        }

        Dump.println(1-sum);
        Dump.println(Math.round((1-sum)*1000.0)/1000.0);

    }

    /**
     * Bernoulli trials
     * @param k number of successes
     * @param n number of trials
     * @param pSuccess probability of success in one trial
     * @return
     */
    public static double bernoulli(int k, int n, double pSuccess) {
        double pFailure = 1 - pSuccess;

        Dump.println(k + " in " + n);

        Dump.println(Calc.binomial(n, k));

        return binomialCoefficient(n, k) * Math.pow(pSuccess, k) * Math.pow(pFailure, n - k);
    }


}
