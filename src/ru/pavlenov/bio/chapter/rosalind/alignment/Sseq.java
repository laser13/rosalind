package ru.pavlenov.bio.chapter.rosalind.alignment;

import com.google.common.base.Joiner;
import net.sf.jfasta.impl.FASTAElementIterator;
import net.sf.jfasta.impl.FASTAFileReaderImpl;
import ru.pavlenov.bio.genome.DNA;
import ru.pavlenov.bio.utils.Dump;
import ru.pavlenov.bio.utils.File;

import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * ⓭
 * Какой сам? by Pavlenov Semen 29.06.14.
 *
 * Finding a Spliced Motif
 * http://rosalind.info/problems/sseq/
 *
 * Given: Two DNA strings s and t (each of length at most 1 kbp) in FASTA format.
 * Return: One collection of indices of s in which the symbols of t appear as a subsequence of s.
 * If multiple solutions exist, you may return any one.
 */
public class Sseq {

    public static void start() throws IOException {

        String data = File.readFile("/home/laser13/IdeaProjects/bio/src/ru/pavlenov/bio/chapter/rosalind/alignment/Sseq.data", Charset.defaultCharset());

        FASTAFileReaderImpl fasta = new FASTAFileReaderImpl(new StringReader(data));
        FASTAElementIterator it = fasta.getIterator();

        String text = it.next().getSequence();
        String kmer = it.next().getSequence();

        List<Integer> indices = new ArrayList<>();
        int i = 0, j = 0;
        while (i < text.length() && j < kmer.length()) {
            if (text.charAt(i) == kmer.charAt(j)) {
                indices.add(i + 1);
                j += 1;
            }
            i += 1;
        }

        Dump.println(Joiner.on(" ").join(indices));

    }

}