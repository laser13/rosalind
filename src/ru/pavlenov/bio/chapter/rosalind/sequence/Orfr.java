package ru.pavlenov.bio.chapter.rosalind.sequence;

import org.biojava3.core.sequence.DNASequence;
import ru.pavlenov.bio.amino.Peptide;
import ru.pavlenov.bio.amino.RNA;
import ru.pavlenov.bio.genome.DNA;
import ru.pavlenov.bio.utils.Dump;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.function.IntPredicate;

/**
 * Created by laser13 on 28.06.14.
 * Finding Genes with ORFs
 * http://rosalind.info/problems/orfr/
 *
 * Given: A DNA string s of length at most 1 kbp.
 * Return: The longest protein string that can be translated from an ORF of s. If more than one protein string of maximum length exists, then you may output any solution.
 *
 */
public class Orfr {

    public static void start() {

        String dnaText = "GTCATAAAAACATCGGTGTCCGCGCTGTACCTTATCACCCAGTGCTGACGCCGATTTACCTTGCAGGCAATCGCTAATTAAGCCAAAGTGAGGTCTATACACTTGAAGTGCATGCGTATCATTCATGACAAGAATAGGCAACCCAGGGACATAATATGGATTCTGAGTGATTTACGTTGGGGCGTACCCGATGTTTTCATAGGGAAGTCTGTTGCAGGGGTGATCTACCCGTCACGAACGCCCGATGTATAACCCTGACCAGCAATAAGAAAAACCACTTGAGGCCGGAACCCTGGTGGGCTGTCGCGAGAGTCTATGATTGTCTTAGTTAACGCTTGATTTAACGTTAGCAAGCTCCCAGAGACTAAGACTTCGTAAGCATGAAATTCAGTCTGTTTGCCACGAGCACATGGGACCACGGGTGGATTAGCTAATCCACCCGTGGTCCCATTGTCCGCAAAATACAGGGTGAAATGCTGACTCCCGCTCCGTGGGTAGTACATTCGACAATAAACAGCCAGCTGCACCAAGCCAAGGGACTATTGGCGGTAAATGTGGAGGGGCCAGCTCGAGCTACATGACGACTCCGTAGTCTTGAGCGGCGTTGTATGGGAGCGTCACCACTCGGACGCGCTAGCGTCACGTCAATTACTCCAAGAAGTGGGGGCCGGCTACGAGGTTCGTAGTCCAGTGTAGAGTCCCCCAATCAACTTCCGGAAGCCTACGCCTACGGAGTAACGGTTGCCTCAACCGCCAAGCGCTCTTGAAATAGTTTAACGTAGTCAGGCCTAACTGGTTACCCGGCCTATGCCATTACATTCCTCGCTGCTGAAACGGGAGAACGTGGATGAACATTCTAT";

        DNA dna = new DNA(dnaText);
        RNA.Translate trans = dna.transcribe().translate();

        String startAcid = "M";
        String maxProtein = "";
        for (Peptide peptide : trans) {

            String[] proteins = peptide.toString().split("\\*");
            for (String protein : proteins) {

                int mIndex = protein.indexOf(startAcid);
                if (mIndex < 0) continue;
                String aspirant = protein.substring(mIndex);
                if (aspirant.length() > maxProtein.length()) {
                    maxProtein = aspirant;
                }
            }
        }
        Dump.println(maxProtein);
    }
}
