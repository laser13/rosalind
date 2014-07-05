package ru.pavlenov.bio.chapter.rosalind.string_algoritm;

import net.sf.jfasta.FASTAElement;
import net.sf.jfasta.impl.FASTAElementIterator;
import net.sf.jfasta.impl.FASTAFileReaderImpl;
import ru.pavlenov.bio.amino.RNA;
import ru.pavlenov.bio.genome.DNA;
import ru.pavlenov.bio.utils.Dump;
import ru.pavlenov.bio.utils.File;

import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Какой сам ☭
 * User: Павленов Семён
 * Date: 11.01.14 21:25
 */
public class Splc {

    /**
     * RNA Splicing
     * http://rosalind.info/problems/splc/
     *
     * Given: A DNA string s (of length at most 1 kbp) and a collection of substrings of s acting as introns. All strings are given in FASTA format.
     * Return: A protein string resulting from transcribing and translating the exons of s. (Note: Only one solution will exist for the dataset provided.)
     *
     */
    public static void start() throws IOException {

        String data = File.readFile("/home/laser13/IdeaProjects/bio/src/ru/pavlenov/bio/chapter/rosalind/string_algoritm/Splc.data", Charset.defaultCharset());

        FASTAFileReaderImpl fasta = new FASTAFileReaderImpl(new StringReader(data));
        final FASTAElementIterator it = fasta.getIterator();

        String text = it.next().getSequence();

        while (it.hasNext()) {
            FASTAElement el = it.next();
            text = text.replaceAll(el.getSequence(), "");
        }

        Dump.println(text);

        DNA dna = new DNA(text);

        RNA rna = dna.transcribe();

        RNA.Translate translate = rna.translate();

        Dump.println(translate.getPep1());



    }

}
