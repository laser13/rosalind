package ru.pavlenov.bio.chapter.rosalind.combinatorics;

import ru.pavlenov.bio.amino.RNA;
import ru.pavlenov.bio.utils.Calc;
import ru.pavlenov.bio.utils.Dump;

import java.math.BigInteger;

/**
 * Created by laser13 on 19.04.14.
 */
public class Pmch {

    /**
     * http://rosalind.info/problems/pmch/
     * Perfect Matchings and RNA Secondary Structures
     *
     * Given: An RNA string s of length at most 80 bp having the same number of occurrences of 'A' as 'U' and the same number of occurrences of 'C' as 'G'.
     * Return: The total possible number of perfect matchings of basepair edges in the bonding graph of s.
     *
     */
    public static void start() {

        String text = "CUUGCAGGAUCAGGCAGCACAAUCUCGGGAAUUAGUACUUCUCGAGGCUCAACCUGAACUUACUGUCGGAGU";

        RNA rna = new RNA(text);

        int countA = rna.count('A');
        int countC = rna.count('C');

        BigInteger result = Calc.factorial(countA).multiply(Calc.factorial(countC));

        Dump.println(result);


    }

}
