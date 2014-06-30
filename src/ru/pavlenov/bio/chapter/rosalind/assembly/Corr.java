package ru.pavlenov.bio.chapter.rosalind.assembly;

import net.sf.jfasta.impl.FASTAElementIterator;
import net.sf.jfasta.impl.FASTAFileReaderImpl;
import ru.pavlenov.bio.genome.DNA;
import ru.pavlenov.bio.genome.Kmer;
import ru.pavlenov.bio.utils.Convert;
import ru.pavlenov.bio.utils.Dump;
import ru.pavlenov.bio.utils.File;

import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * ⓭
 * Какой сам? by Pavlenov Semen 29.06.14.
 *
 * Error Correction in Reads
 * http://rosalind.info/problems/corr/
 *
 * Given: A collection of up to 1000 reads of equal length (at most 50 bp) in FASTA format.
 *        Some of these reads were generated with a single-nucleotide error.
 *        For each read s in the dataset, one of the following applies:
 *        - s was correctly sequenced and appears in the dataset at least twice (possibly as a reverse complement);
 *        - s is incorrect, it appears in the dataset exactly once, and its Hamming distance is 1 with respect to exactly
 *          one correct read in the dataset (or its reverse complement).
 *
 * Return: A list of all corrections in the form "[old read]->[new read]".
 *         (Each correction must be a single symbol substitution, and you may return the corrections in any order.)
 */
public class Corr {

    public static void start() throws IOException {

        String data = File.readFile("/home/laser13/IdeaProjects/bio/src/ru/pavlenov/bio/chapter/rosalind/assembly/Corr.data", Charset.defaultCharset());

        FASTAFileReaderImpl fasta = new FASTAFileReaderImpl(new StringReader(data));
        List<String> reads = Convert.from(fasta).toStringList();

        Map<String, Integer> stats = new HashMap<>();

        for (int i = 0; i < reads.size(); i++) {

            String read1 = reads.get(i);
            String revers1 = DNA.reverse(read1);

            if (!stats.containsKey(read1)) stats.put(read1, 1);
            if (!stats.containsKey(revers1)) stats.put(revers1, 1);

            for (int j = i+1; j < reads.size(); j++) {

                String read2 = reads.get(j);

                if (read1.equals(read2)) {
                    stats.put(read1, stats.get(read1) + 1);
                }

                if (revers1.equals(read2)) {
                    stats.put(read2, stats.get(read2) + 1);
                }

            }

        }

        List<String> correct = new ArrayList<>();
        List<String> errors = new ArrayList<>();

        stats.forEach((s, integer) -> {
            if (integer > 1) correct.add(s);
            else errors.add(s);
        });

        Map<String, String> result = new HashMap<>();

        for (String e : errors) {

            for (String s : correct) {

                if (Kmer.isMismatch(e, s, 1)) {
                    result.put(e, s);
                }

            }

        }

        result.forEach((e, c) -> Dump.println(e + "->" + c));

    }

}