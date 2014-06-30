package ru.pavlenov.bio.chapter.rosalind.string_algoritm;

import com.google.common.base.Joiner;
import com.google.common.collect.Collections2;
import net.sf.jfasta.impl.FASTAFileReaderImpl;
import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;
import ru.pavlenov.bio.genome.DNA;
import ru.pavlenov.bio.genome.DNAArray;
import ru.pavlenov.bio.utils.Dump;
import ru.pavlenov.bio.utils.File;
import ru.pavlenov.bio.utils.Sort;

import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * ⓭
 * Какой сам? by Pavlenov Semen 29.06.14.
 *
 * k-Mer Composition
 * http://rosalind.info/problems/kmer/
 *
 * Given: A DNA string s in FASTA format (having length at most 100 kbp).
 * Return: The 4-mer composition of s.
 *
 */
public class Kmer {

    public static void start() throws IOException {

        int k = 4;
        String data = File.readFile("/home/laser13/IdeaProjects/bio/src/ru/pavlenov/bio/chapter/rosalind/string_algoritm/Kmer.data", Charset.defaultCharset());

        FASTAFileReaderImpl fasta = new FASTAFileReaderImpl(new StringReader(data));
        String text = fasta.getIterator().next().getSequence();
        DNA dna = new DNA(text);

        List<Character> alph = new ArrayList<>();
        Collections.addAll(alph, 'A', 'T', 'C', 'G');
        ICombinatoricsVector<Character> initialVector = Factory.createVector(alph);
        Generator<Character> perms = Factory.createPermutationWithRepetitionGenerator(initialVector, k);

        List<String> kmers = new ArrayList<>();
        for (ICombinatoricsVector<Character> perm : perms) {
            kmers.add(Joiner.on("").join(perm));
        }

        Collections.sort(kmers);

        List<Integer> compos = new ArrayList<>();
        for (String kmer : kmers) {
            int count = dna.getMotifIndeces(kmer).length;
            compos.add(count);
        }

        Dump.println(Joiner.on(" ").join(compos));


    }

}