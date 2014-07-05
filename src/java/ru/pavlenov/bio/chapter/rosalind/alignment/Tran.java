package ru.pavlenov.bio.chapter.rosalind.alignment;

import net.sf.jfasta.impl.FASTAElementIterator;
import net.sf.jfasta.impl.FASTAFileReaderImpl;
import ru.pavlenov.bio.utils.Dump;
import ru.pavlenov.bio.utils.File;

import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.Charset;

/**
 * ⓭
 * Какой сам? by Pavlenov Semen 29.06.14.
 * Transitions and Transversions
 * http://rosalind.info/problems/tran/
 *
 * Given: Two DNA strings s1 and s2 of equal length (at most 1 kbp).
 * Return: The transition/transversion ratio R(s1,s2).
 *
 * transition:
 * A - G purine
 * C - T pyrimidine
 *
 */
public class Tran {

    public static void start() throws IOException {

        String data = File.readFile("/home/laser13/IdeaProjects/bio/src/ru/pavlenov/bio/chapter/rosalind/alignment/Tran.data", Charset.defaultCharset());

        FASTAFileReaderImpl fasta = new FASTAFileReaderImpl(new StringReader(data));
        final FASTAElementIterator it = fasta.getIterator();

        String text1 = it.next().getSequence();
        String text2 = it.next().getSequence();

        int transition = 0;
        int transversion = 0;
        for (int i = 0; i < text1.toCharArray().length; i++) {

            char c1 = text1.charAt(i);
            char c2 = text2.charAt(i);

            if (c1 == c2) continue;

            if (
                (c1 == 'A' && c2 == 'G') ||
                (c1 == 'G' && c2 == 'A') ||
                (c1 == 'C' && c2 == 'T') ||
                (c1 == 'T' && c2 == 'C')) {
                transition++;
            }
            else {
                transversion++;
            }

        }

        Dump.println(transition);
        Dump.println(transversion);
        Dump.println(String.format("%.11f", transition/(transversion*1d)).replace(",", "."));

    }

}