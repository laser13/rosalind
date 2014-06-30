package ru.pavlenov.bio.chapter.stepic;

import ru.pavlenov.bio.utils.Dump;

/**
 * Какой сам ☭
 * User: Павленов Семён
 * Date: 19.12.13 18:26
 */
public class C74_5 {

    public static String[][] longestCommonSubsequence(String str1, String str2) {

        Integer[][] s = new Integer[str1.length()+1][str2.length()+1];
        String[][] backtrack = new String[str1.length()][str2.length()];


        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();

        for (int i = 0; i <= str1.length(); i++) {
            s[i][0] = 0;
        }
        for (int j = 0; j <= str2.length(); j++) {
            s[0][j] = 0;
        }

        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {

                int down = s[i-1][j];
                int right = s[i][j-1];
                int bias = s[i-1][j-1];
                if (s1[i-1] == s2[j-1]) bias += 1;

                s[i][j] = (((down > right) ? down : right) > bias) ? ((down > right) ? down : right) : bias;

                if (s[i][j].equals(s[i-1][j])) backtrack[i-1][j-1] = "↓";
                else if (s[i][j].equals(s[i][j-1])) backtrack[i-1][j-1] = "→";
                else if (s[i][j].equals(s[i-1][j-1]+1)) backtrack[i-1][j-1] = "↘";

            }
        }

        Dump.println(s);
        Dump.println("-------------------");
        Dump.println(backtrack);

        return backtrack;

    }

    public static void track(String[][] backtrack, String str, int i, int j) {

        if (i == -1 || j == -1) return;

//        Dump.println(i + ":" + j + "" + backtrack[i][j]);

        if (backtrack[i][j].equals("↓")) {
            track(backtrack, str, i - 1, j);
        }
        else if (backtrack[i][j].equals("→")) {
            track(backtrack, str, i, j-1);
        }
        else {
            track(backtrack, str, i-1, j-1);
//            Dump.print(i + ":" + j + "" + backtrack[i][j]);
            Dump.print(String.valueOf(str.charAt(i)));

        }



    }

    public static void start() {

        String str1 = "ACCGGAGTATGGGAATTAAACGAAACCAGTACGTCCTTTGAGCGGAGCAGGCACTGGCAGGCACAGTGAGCCGGCACACCGCAGCGTAGTTGCATACACACTCTCGTCGCTAAACTATGAAACGTGGCGAGAGTTCCCGTACGATGAAGTGACCTTCCCCCGGTGCGAGACGCGAGGGAGCGATGCAACCGAAAGATGGTTCTTATGTTTAGATAGATTTTGGGTAATAAATAAGAAAGCGAATTAGCGGATTTGGAATTAAAGAGGGAGGTTACGAATGCGGCATTTGATCCCCTTTGTCGCTACGATATTCCCCTCTACAAATAAGATTGCACTGTGGCAAGACAACATACCGTCTCCGCTTAAGCAGATGAGTTCCGGGGCCGAGCTTCGCTAGATGTTGGTTCGGATAGCCTCCTCTGGTATTCTGACGGAATGAAAGAGGGAAAAGCCTCAGAGACACGTAAGTAAAGCAGATTAACTACCACATTGCTGCCGTCATCATGTCTTATAACTCACAACGTTTGAATTGCTGGGATTCCCGATGGGAAGTTAATGTTAGCGGGACCACAGAGCTTGGGCACCCACAGGCTGAGTCTTACGCACCCAACGGACGTATTATGGTGATATTGATACGCAAGCATGCGCTTGTATCTCCCAGATACAACCGTGAAACCGGCCTAAGGACAATATTACTTAGAGGACACAAGAAACACTTGTTCCGACCGTAATACACGCACGCGCACTAAGGTATCTTATGATGGACAAGGCGGCTAGACAGACTTGACTAGCGAACGAATGTCATTAGTCGTCGACCGTGGCATTATGATTGTATCGACTCTCGTTGAGGGTCCTGCGATC";
        String str2 = "TACGGGTTCTACGTCAGCCCCCGAGAATGGCTACCATATCATGGCGACAGGGATACCGATTGAGTTCGGCTGGCTTCCGAAAGTAATAAAACCCATTTGGGAATGGTCTCCGTGCATGGAGCTCGGTCATCCGTCCAAAAACGGGGCGTTCTAACCCTTTGTATTCCCCCTCAGTTCGTCGGGTACAAGGCGAACAGCGGAATTTCAGCCGCCGCGAGCGGGAGGCACGCCTACTCGGGCAGGGAGTCAGAGCTGTTTTCTAGGCGACATGGCCCCAGCTTTCTAAAAGTAGCATCTCCTAAGCGCGATTCCACGATACGTGCATTCCAGAGCGGTTCGCGACATATCAAAGAGCTATACACTGTATTGACCGCCCGCAGATCACGGTGGGACCTAGTGTATTTGGGGAGTACAGTGCATTTAATGTGCACGGGAACTGGACTGTGATTGCCCGAAGTCTGCCGGATCAGTCCACTACACGAATCTATGCTCGGCTTATCAGTATGGCCGGACCCCTCAATACTAGCGGGTGAAAGAGGCCTAAGATGGAAATAGGCTTTGCTTTACACAAATGAGGTATCTCAGTGTCAGACCGTAGGTACCCCAACACTTGACCTTTGAATGCATAGGGGACCCTAACTCTCGAGTGTAATTTTTATGTCCAGGGAGTCAAGATTGTCTTGGGGCCACTGTACAAAGCTGAGAAGCGTTCGCCTAGTCTGTCCCCTCTATCTGTCTAGTTCCAAGAGCCACTATTCGGAGCCTAGTCAGCATCGACTACAGAGAACCCCTGTCCTGTAGAGACAGTCCAAATCTGGAGTTTACGTAGAACTCCGAATGCTGAACTGATAGCAGCCGAAAACGAATCTAATTAAAGTCTCATCTACACGATATAGGAGACGATCGACGTTCGCTGAAGCCGTGGACGCGGTAATCGCGTTTTGCATGTTGCAAGGCTATCGCTGTTTCACTCGAGCAATCCCGTACCT";

        String[][] backtrack = longestCommonSubsequence(str1, str2);

        track(backtrack, str1, str1.length()-1, str2.length()-1);
        Dump.println("");

    }

}
