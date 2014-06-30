package ru.pavlenov.bio.utils;

import java.math.BigInteger;

/**
 * Какой сам ☭
 * User: Павленов Семён
 * Date: 08.01.14 21:57
 */
public class Calc {

//    def binomial(n, k):
//            """Compute n factorial by a direct multiplicative method."""
//            if k > n - k:
//    k = n - k  # Use symmetry of Pascal's triangle
//    accum = 1
//            for i in range(1, k + 1):
//    accum *= (n - (k - i))
//    accum /= i
//    return accum


    /**
     * Compute n factorial by a direct multiplicative method.
     *
     * @param n
     * @param k
     * @return
     */
    public static Long binomial(int n, int k) {

        // Use symmetry of Pascal's triangle
        if (k > n - k) k = n - k;
        long result = 1;
        for (int i = 1; i <= k; i++) {
            result *= (n - (k - 1));
            result /= i;
        }
        return result;

    }

    public static BigInteger factorial(int n) {
        BigInteger ret = BigInteger.ONE;
        for (int i = 1; i <= n; ++i) ret = ret.multiply(BigInteger.valueOf(i));
        return ret;
    }

}
