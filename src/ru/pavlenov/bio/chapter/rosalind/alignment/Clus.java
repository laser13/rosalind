package ru.pavlenov.bio.chapter.rosalind.alignment;

import net.sf.jfasta.impl.FASTAElementIterator;
import net.sf.jfasta.impl.FASTAFileReaderImpl;
import ru.pavlenov.bio.utils.File;

import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.Charset;

/**
 * Created by laser13 on 28.06.14.
 *
 * Global Multiple Alignment
 * http://rosalind.info/problems/clus/
 *
 * Given: Set of nucleotide strings in FASTA format.
 * Return: ID of the string most different from the others.
 *
 */
public class Clus {

    public static void start() throws IOException {

        String data = File.readFile("/home/laser13/IdeaProjects/bio/src/ru/pavlenov/bio/chapter/rosalind/alignment/Clus.data", Charset.defaultCharset());

        FASTAFileReaderImpl fasta = new FASTAFileReaderImpl(new StringReader(data));
        FASTAElementIterator it = fasta.getIterator();

    }

}
