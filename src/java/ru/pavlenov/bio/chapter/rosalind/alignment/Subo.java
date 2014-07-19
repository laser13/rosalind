package ru.pavlenov.bio.chapter.rosalind.alignment;

import net.sf.jfasta.impl.FASTAElementIterator;
import net.sf.jfasta.impl.FASTAFileReaderImpl;
import org.apache.commons.lang.StringUtils;
import ru.pavlenov.bio.amino.InvalidAlphabetException;
import ru.pavlenov.bio.amino.LocalAlignment;
import ru.pavlenov.bio.genome.Kmer;
import ru.pavlenov.bio.utils.Dump;
import ru.pavlenov.bio.utils.File;

import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.Charset;

/**
 * Created by laser13 on 28.06.14.
 * Suboptimal Local Alignment
 * http://rosalind.info/problems/subo/
 *
 * Given: Two DNA strings s and t in FASTA format that share some short inexact repeat r of 32-40 bp. By "inexact"
 * we mean that r may appear with slight modifications (each repeat differ by ≤3 changes/indels).
 *
 * Return: The total number of occurrences of r as a substring of s, followed by the total number
 * of occurrences of r as a substring of t.
 *
 *
 */
public class Subo {

    public static void start() throws IOException, InvalidAlphabetException {


        String data = File.readFile("/home/laser13/IdeaProjects/bio/src/ru/pavlenov/bio/chapter/rosalind/alignment/Subo.data", Charset.defaultCharset());

        FASTAFileReaderImpl fasta = new FASTAFileReaderImpl(new StringReader(data));
        final FASTAElementIterator it = fasta.getIterator();

        String text1 = it.next().getSequence();
        String text2 = it.next().getSequence();

        Dump.println(text1);
        Dump.println(text2);

        LocalAlignment lalign = new LocalAlignment(text1, text2);
        lalign
                .setMatch(5)
                .setPenalty(4);
        lalign.make();
        Dump.println("[" + lalign.getStartI() + ":" + lalign.getStartJ() + "]=" + lalign.getGlobalMaxWeight());

        lalign.track(lalign.getStartI()-1, lalign.getStartJ()-1);

        Dump.println(StringUtils.join(lalign.getResult2(), ""));
        Dump.println(StringUtils.join(lalign.getResult1(), ""));

        Dump.println("==============================================");
        String k = "TCTGCGCAGGGGCACGGGGTGTCGCCTTGCTGGGAAGG";

        Dump.println(k.length());
        int c1 = 0;
        int c2 = 0;
        for (int i = 0; i < text1.length() - k.length() + 1; i++) {

//            Dump.println(k);
//            Dump.println(text1.substring(i, i + k.length()));
//            Dump.line();

            if (Kmer.isMismatch(k, text1.substring(i, i + k.length()), 3))
                c1++;
        }

        for (int i = 0; i < text2.length() - k.length() + 1; i++) {
            if (Kmer.isMismatch(k, text2.substring(i, i + k.length()), 3))
                c2++;
        }

        Dump.println(c1 + " " + c2);


    }

}
