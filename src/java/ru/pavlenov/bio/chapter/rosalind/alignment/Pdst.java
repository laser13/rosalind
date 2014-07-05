package ru.pavlenov.bio.chapter.rosalind.alignment;

import net.sf.jfasta.impl.FASTAFileReaderImpl;
import ru.pavlenov.bio.genome.Kmer;
import ru.pavlenov.bio.utils.Convert;
import ru.pavlenov.bio.utils.Dump;
import ru.pavlenov.bio.utils.File;

import java.io.IOException;
import java.util.List;

/**
 * ⓭ + 34
 * Какой сам? by Pavlenov Semen 02.07.14.
 * Creating a Distance Matrix
 * http://rosalind.info/problems/pdst/
 *
 * Given: A collection of n (n≤10) DNA strings s1,…,sn of equal length (at most 1 kbp). Strings are given in FASTA format.
 * Return: The matrix D corresponding to the p-distance dp on the given strings.
 *         As always, note that your answer is allowed an absolute error of 0.001.
 *
 */
public class Pdst {

    public static void start() throws IOException {

        FASTAFileReaderImpl fasta = File.readFasta(Pdst.class);
        List<String> list = Convert.from(fasta).toStringList();

        int len = list.size();
        int count = list.get(0).length();

        Float[][] m = new Float[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                int mis = Kmer.calcMismatch(list.get(i), list.get(j));
                m[i][j] = m[j][i] = (mis / (count * 1.00000f));
            }
        }
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {

                Dump.print(String.format("%.5f ", m[i][j]).replace(",", "."));

            }
            Dump.ln();
        }

    }

}