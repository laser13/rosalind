package ru.pavlenov.bio.chapter.stepic;

import org.apache.commons.lang.StringUtils;
import ru.pavlenov.bio.amino.FittingAlignment;
import ru.pavlenov.bio.amino.GlobalAlignment;
import ru.pavlenov.bio.amino.InvalidAlphabetException;
import ru.pavlenov.bio.amino.LocalAlignment;
import ru.pavlenov.bio.utils.Dump;

import java.util.ArrayList;

/**
 * Какой сам ⚝
 * Author: Pavlenov Semen
 * Date: 24.12.13
 * <p>
 * E = mc^2
 */
public class C77_5 {

    public static void start() throws InvalidAlphabetException {

        String str2 = "TTAGGACTACGACTAGACCTCACTCTACTGACAATCACGGTAAGGAGTCGCCCGCTTTTTTGGCGTATTTAGCGGGATAAGTGAGGTAGGCACGGCGGAGGCCAATCTCAGGGCTCCTATGGCTATTCGCATTTACTTACTTCACCCACGCATACGGGGCGAAATCTCTCGTCGCCCAGCCATCCATCGATTCATTGTACAGCGCTACGGGGGACCATGGTCATCAAGATGGTCAGCCGTATCACCTGAGTCCTGGGTCCAAACGATCCGGCCCTGCCACAGGTGCTAGCCATTGCAACGCCTTATAAGAGCGCCTTACAATTGAAAGTGCTACTGAGTGTGACCGTAGCGAATTGTCCACAGGTTGGAAGCGAAATCCTTTGGTGCAGCCTCGTGCCTAGTAATCAGGGCATGTTCTGACCGCCCGAGCACCGCATGTAAGCAACAGTACGGTAATTTAGTCAGGAATCCTTGATAATGTCACAAGCAGCCCGCGGGTAATCACAGTAGATTCAGGTTACCGTAATTAAAGATCAGCATGCGTATCAGCTTCCGTATGTATTGGCATCAAAGGGCCTAGAAGCATGCTCGAACTGGATCTGTGCTGACCGTGAAAACACAGGAAGTATCAAGCCCCCTCTAATAAGATTGGGCATTTTCGCGCATTAATCATTCCGGGATTTGACGCACCCGGATTTAAGCAGGCAGGCGAGCACCACTGTAATTTCGATCATCTTCCAGCCCTCATGTCCCACGCCTTAATCCCGTAACGAACTTTCCGCTCAAATAAGTTAACAGGGGTGTAATTTCTTCCCATCT";
        String str1 = "ACCAGCTCATTACTGCGTCATGCTTACTCTCAACGGGTTACCGACTGGGAGCTGAGCAGGCTGTAAGAGCCCGGAGCTTTTCCAATTT";

        int len2 = str2.length();

        Integer bestScoring = null;
        ArrayList<Character> bestResult1 = new ArrayList<>();
        ArrayList<Character> bestResult2 = new ArrayList<>();

        Dump.println(len2);

        for (int l = 1; l <= len2; l++) {

            if (l % 50 == 0)
                Dump.println(l + " in " + len2);

            for (int k = 0; k <= len2 - l; k++) {

                String str3 = str2.substring(k, k + l);

                FittingAlignment alignment = new FittingAlignment(str1, str3);
                alignment.setPenalty(1).setMatch(1);

                alignment.make();

                int currScoring = alignment.getWeightMatrix()[str1.length()][str3.length()];

                if (bestScoring == null || bestScoring < currScoring) {
                    bestScoring = currScoring;

                    alignment.track(str1.length() - 1, str3.length() - 1);

                    bestResult1 = alignment.getResult1();
                    bestResult2 = alignment.getResult2();
                }

            }

        }

        Dump.println("==================================");
        Dump.println(bestScoring);
        Dump.println(StringUtils.join(bestResult2, ""));
        Dump.println(StringUtils.join(bestResult1, ""));

    }

}
