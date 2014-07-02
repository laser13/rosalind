package ru.pavlenov.bio.chapter.rosalind.assembly;

import net.sf.jfasta.impl.FASTAElementIterator;
import net.sf.jfasta.impl.FASTAFileReaderImpl;
import ru.pavlenov.bio.assemble.Base;
import ru.pavlenov.bio.utils.Convert;
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
 */
public class Long {

    public static void start() throws IOException {

        String data = File.readFile("/home/laser13/IdeaProjects/bio/src/ru/pavlenov/bio/chapter/rosalind/assembly/Long.data", Charset.defaultCharset());

        FASTAFileReaderImpl fasta = new FASTAFileReaderImpl(new StringReader(data));
        List<String> reads = Convert.from(fasta).toStringList();

        String ss = Base.getShortestSuperstring(reads, (reads.get(0).length() / 3));

        Dump.println(ss);
        Dump.println("ATTAGACCTGCCGGAATAC");


    }

}