package ru.pavlenov.bio.chapter.rosalind.combinatorics;

import net.sf.jfasta.impl.FASTAElementIterator;
import net.sf.jfasta.impl.FASTAFileReaderImpl;
import ru.pavlenov.bio.amino.Peptide;
import ru.pavlenov.bio.amino.RNA;
import ru.pavlenov.bio.genome.DNA;
import ru.pavlenov.bio.utils.Dump;
import ru.pavlenov.bio.utils.File;

import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Set;

/**
 * ⓭
 * Какой сам?
 * by Pavlenov Semen 29.06.14.
 *
 * Open Reading Frames
 * http://rosalind.info/problems/orf/
 *
 *
 * Given: A DNA string s of length at most 1 kbp in FASTA format.
 * Return: Every distinct candidate protein string that can be translated from ORFs of s.
 * Strings can be returned in any order.
 *
 */
public class Orf {

    public static void start() throws IOException {


        String data = File.readFile("/home/laser13/IdeaProjects/bio/src/ru/pavlenov/bio/chapter/rosalind/combinatorics/Orf.data", Charset.defaultCharset());

        FASTAFileReaderImpl fasta = new FASTAFileReaderImpl(new StringReader(data));
        FASTAElementIterator it = fasta.getIterator();

        String text = it.next().getSequence();

        RNA.Translate translate = new DNA(text).transcribe().translate();

        Set<String> set = new HashSet<>();

        for (Peptide peptide : translate) {
            String[] m = peptide.toString().split("\\*");
            for (int i = 0; i < m.length-1; i++) {
                int mIndex = m[i].indexOf("M");
                while (mIndex >= 0) {
                    set.add(m[i].substring(mIndex));
                    mIndex = m[i].indexOf("M", mIndex+1);
                }
            }
        }
        for (String s : set) {
            Dump.println(s);
        }
    }

}