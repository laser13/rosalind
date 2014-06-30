package ru.pavlenov.bio.chapter.rosalind.string_algoritm;

import com.sun.java.swing.plaf.motif.resources.motif;
import net.sf.jfasta.impl.FASTAElementIterator;
import net.sf.jfasta.impl.FASTAFileReaderImpl;
import org.apache.commons.lang.StringUtils;
import ru.pavlenov.bio.genome.Motif;
import ru.pavlenov.bio.utils.Dump;
import ru.pavlenov.bio.utils.File;

import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Какой сам ☭
 * User: Павленов Семён
 * Date: 11.01.14 14:56
 */
public class Cons {

    /**
     * Consensus and Profile
     * http://rosalind.info/problems/cons/
     *
     * Given: A collection of at most 10 DNA strings of equal length (at most 1 kbp) in FASTA format.
     * Return: A consensus string and profile matrix for the collection.
     * (If several possible consensus strings exist, then you may return any one of them.)
     *
     */
    public static void start() throws IOException {

        String data = File.readFile("/home/laser13/IdeaProjects/bio/src/ru/pavlenov/bio/chapter/rosalind/string_algoritm/Cons.data", Charset.defaultCharset());

        FASTAFileReaderImpl fasta = new FASTAFileReaderImpl(new StringReader(data));
        final FASTAElementIterator it = fasta.getIterator();

        List<String> kmerList = new ArrayList<>();

        while (it.hasNext()) {
            kmerList.add(it.next().getSequence());
        }

        Motif motif = new Motif(kmerList.toArray(new String[kmerList.size()]));

        Dump.println(motif.getConsensus());
        Dump.print("A: "); Dump.println(StringUtils.join(motif.getCount()[0], " "));
        Dump.print("C: "); Dump.println(StringUtils.join(motif.getCount()[1], " "));
        Dump.print("G: "); Dump.println(StringUtils.join(motif.getCount()[2], " "));
        Dump.print("T: "); Dump.println(StringUtils.join(motif.getCount()[3], " "));

    }

}
