package ru.pavlenov.bio.chapter.rosalind.string_algoritm;

import net.sf.jfasta.impl.FASTAFileReaderImpl;
import ru.pavlenov.bio.amino.InvalidAlphabetException;
import ru.pavlenov.bio.amino.LocalAlignment;
import ru.pavlenov.bio.utils.Convert;
import ru.pavlenov.bio.utils.Dump;
import ru.pavlenov.bio.utils.File;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.SplittableRandom;

/**
 * ⓭ + 16
 * Какой сам? by Pavlenov Semen 02.07.14.
 *
 * Finding a Shared Spliced Motif
 * http://rosalind.info/problems/lcsq/
 *
 * Given: Two DNA strings s and t (each having length at most 1 kbp) in FASTA format.
 * Return: A longest common subsequence of s and t. (If more than one solution exists, you may return any one.)
 *
 */
public class Lcsq {

    public static void start() throws IOException, InvalidAlphabetException {

        FASTAFileReaderImpl fasta = File.readFasta(Lcsq.class);
        List<String> list = Convert.from(fasta).toStringList();

        char[] text1 = list.get(0).toCharArray();
        char[] text2 = list.get(1).toCharArray();

        LocalAlignment lalign = new LocalAlignment(list.get(0), list.get(1));

//        lalign.setMatch(1).setPenalty(0);
        lalign.make();
        lalign.track(lalign.getStartI()-1, lalign.getStartJ()-1);

        ArrayList<Character> r1 = lalign.getResult1();
        ArrayList<Character> r2 = lalign.getResult2();

//        Dump.println(lalign.getResult1());
//        Dump.println(lalign.getResult2());

        for (int i = 0; i < r1.size(); i++) {
            if (r1.get(i) == r2.get(i)) Dump.print(r1.get(i));
        }
        Dump.ln();


//        Integer[][] m = new Integer[text1.length+1][text2.length+1];
//
//        for (int i = 0; i < text1.length+1; i++) {
//            m[i][0] = 0;
//        }
//        for (int j = 0; j < text2.length+1; j++) {
//            m[0][j] = 0;
//        }
//
//        for (int i = 0; i < text1.length; i++) {
//
//            for (int j = 0; j < text2.length; j++) {
//
//                if (text1[i] == text2[j]) m[i+1][j+1] = m[i][j]+1;
//                else m[i+1][j+1] = Math.max(m[i+1][j], m[i][j+1]);
//
//            }
//
//        }
//
//        int i = text1.length;
//        int j = text2.length;
//        String sub = "";


//        while (i*j > 0) {
//
//
//
//        }

//        Dump.println(m);


    }

}