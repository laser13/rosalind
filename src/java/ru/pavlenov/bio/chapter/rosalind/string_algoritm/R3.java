package ru.pavlenov.bio.chapter.rosalind.string_algoritm;

import ru.pavlenov.bio.genome.DNA;
import ru.pavlenov.bio.utils.Dump;

/**
 * Какой сам ☭
 * User: Павленов Семён
 * Date: 27.12.13 20:16
 */
public class R3 {

    /**
     * Complementing a Strand of DNA
     * http://rosalind.info/problems/revc/
     */
    public static void start() {

        String text = "AAGAAACCCCGGTTCACTAGGTGCGCAATGAGGAGGTCAAATCGCGAATGTGAATCCACGTGACGGTAATCTGGCGCCTACTACGATGGATCTCAGCATCCCCGACCTAAAGCATTAGACGGCCTCATGGACTTCCTTCTCTAGATGTGTACAACGACTCGACCACGAAGATCAATTGCCTCAGCGCTACTTCGCGTGAATGAGGCTTGCGATAGTCGCGCTGAAATGTAGCCAATCTGTTGCCTGGTGAAGTGTTCGATCTCTACCTCCACGAAGGACGGACGGGGATCAAGTCGTGTGCTGTTGTTATTCTCACTGATCAAACATCCTGCCGTGGTGGGCGTGCGGCCTGGCCCGGTGGTCAGTCGTGCCAGAGCTAGCAGCCCCAAAGCCTCGCTCCTCATCCCGTTTACTTATAGGTGTATTAATCCTAACAGCTATAGCCGTAAGCATGGACCTAAGACAGGTAGCAAATTCTATTCCGTCTCTTTACCGATTATGATTAGTTCATAGGCAAGACCTTTTGTCATGCAAGCTCCGAGCGGTGGTCACGTGCAAGGCCCATAAGTTCTTGCCAGAACCGCTAACTGGTAATCTCAGAACTCATGCCTACTGGTTTATGCTAATTACTCTTCGAGCCGCTGAGACCGCAATTATAGCCACATTATATTCAAAGAGGAACATGTGACCTGAACTGCCGCGCGCGCCGACCCTGGCTGACGTTTTCCAGCTCACAACGGCCTACCTTAACTATTTAGGGTTGATCTCAAAGAGCCGCCAGCTTGCCGACCAATATTGACTGCCGTCTTAGGATAGCATGGGTTGAATACCAACAATCACAGCAGTATAAGCTTCCAACGCTCCGACCCGACTATATTTTATTAAGAAACCCCGGTTCACTAGGTGCGCAATGAGGAGGTCAAATCGCGAATGTGAATCCACGTGACGGTAATCTGGCGCCTACTACGATGGATCTCAGCATCCCCGACCTAAAGCATTAGACGGCCTCATGGACTTCCTTCTCTAGATGTGTACAACGACTCGACCACGAAGATCAATTGCCTCAGCGCTACTTCGCGTGAATGAGGCTTGCGATAGTCGCGCTGAAATGTAGCCAATCTGTTGCCTGGTGAAGTGTTCGATCTCTACCTCCACGAAGGACGGACGGGGATCAAGTCGTGTGCTGTTGTTATTCTCACTGATCAAACATCCTGCCGTGGTGGGCGTGCGGCCTGGCCCGGTGGTCAGTCGTGCCAGAGCTAGCAGCCCCAAAGCCTCGCTCCTCATCCCGTTTACTTATAGGTGTATTAATCCTAACAGCTATAGCCGTAAGCATGGACCTAAGACAGGTAGCAAATTCTATTCCGTCTCTTTACCGATTATGATTAGTTCATAGGCAAGACCTTTTGTCATGCAAGCTCCGAGCGGTGGTCACGTGCAAGGCCCATAAGTTCTTGCCAGAACCGCTAACTGGTAATCTCAGAACTCATGCCTACTGGTTTATGCTAATTACTCTTCGAGCCGCTGAGACCGCAATTATAGCCACATTATATTCAAAGAGGAACATGTGACCTGAACTGCCGCGCGCGCCGACCCTGGCTGACGTTTTCCAGCTCACAACGGCCTACCTTAACTATTTAGGGTTGATCTCAAAGAGCCGCCAGCTTGCCGACCAATATTGACTGCCGTCTTAGGATAGCATGGGTTGAATACCAACAATCACAGCAGTATAAGCTTCCAACGCTCCGACCCGACTATATTTTATTAAGAAACCCCGGTTCACTAGGTGCGCAATGAGGAGGTCAAATCGCGAATGTGAATCCACGTGACGGTAATCTGGCGCCTACTACGATGGATCTCAGCATCCCCGACCTAAAGCATTAGACGGCCTCATGGACTTCCTTCTCTAGATGTGTACAACGACTCGACCACGAAGATCAATTGCCTCAGCGCTACTTCGCGTGAATGAGGCTTGCGATAGTCGCGCTGAAATGTAGCCAATCTGTTGCCTGGTGAAGTGTTCGATCTCTACCTCCACGAAGGACGGACGGGGATCAAGTCGTGTGCTGTTGTTATTCTCACTGATCAAACATCCTGCCGTGGTGGGCGTGCGGCCTGGCCCGGTGGTCAGTCGTGCCAGAGCTAGCAGCCCCAAAGCCTCGCTCCTCATCCCGTTTACTTATAGGTGTATTAATCCTAACAGCTATAGCCGTAAGCATGGACCTAAGACAGGTAGCAAATTCTATTCCGTCTCTTTACCGATTATGATTAGTTCATAGGCAAGACCTTTTGTCATGCAAGCTCCGAGCGGTGGTCACGTGCAAGGCCCATAAGTTCTTGCCAGAACCGCTAACTGGTAATCTCAGAACTCATGCCTACTGGTTTATGCTAATTACTCTTCGAGCCGCTGAGACCGCAATTATAGCCACATTATATTCAAAGAGGAACATGTGACCTGAACTGCCGCGCGCGCCGACCCTGGCTGACGTTTTCCAGCTCACAACGGCCTACCTTAACTATTTAGGGTTGATCTCAAAGAGCCGCCAGCTTGCCGACCAATATTGACTGCCGTCTTAGGATAGCATGGGTTGAATACCAACAATCACAGCAGTATAAGCTTCCAACGCTCCGACCCGACTATATTTTATT";
        DNA dna = new DNA(text);

        Dump.println(dna.reverse());

    }

}
