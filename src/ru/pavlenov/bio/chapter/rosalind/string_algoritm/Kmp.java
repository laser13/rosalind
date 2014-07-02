package ru.pavlenov.bio.chapter.rosalind.string_algoritm;

import com.google.common.base.Joiner;
import net.sf.jfasta.impl.FASTAFileReaderImpl;
import ru.pavlenov.bio.algorithm.KmpStringMatcher;
import ru.pavlenov.bio.utils.Convert;
import ru.pavlenov.bio.utils.Dump;
import ru.pavlenov.bio.utils.File;
import ru.pavlenov.bio.utils.Str;

import java.io.IOException;
import java.util.List;

/**
 * ⓭ + 26
 * Какой сам? by Pavlenov Semen 01.07.14.
 *
 * Speeding Up Motif Finding
 * http://rosalind.info/problems/kmp/
 *
 * Given: A DNA string s (of length at most 100 kbp) in FASTA format.
 * Return: The failure array of s.
 *
 */
public class Kmp {

    public static void start() throws IOException {

        FASTAFileReaderImpl fasta = File.readFasta(Kmp.class);
        String text = fasta.getIterator().next().getSequence();

        int n = text.length();

        KmpStringMatcher kmp = new KmpStringMatcher();

        Dump.println(n);

        Integer[] p = new Integer[n];
        p[0] = 0;


        int j = 0;
        for (int k = 1; k < n; k++) {

            while (j > 0 && text.charAt(j) != text.charAt(k)) {
                j = p[j-1];
            }

            if (text.charAt(j) == text.charAt(k)) {
                j++;
            }

            p[k] = j;

        }

        Dump.println(Joiner.on(" ").join(p));


    }

}