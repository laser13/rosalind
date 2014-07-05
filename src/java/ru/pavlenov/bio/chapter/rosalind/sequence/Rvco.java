package ru.pavlenov.bio.chapter.rosalind.sequence;

import net.sf.jfasta.impl.FASTAElementIterator;
import net.sf.jfasta.impl.FASTAFileReaderImpl;
import org.biojava3.core.sequence.DNASequence;
import org.biojava3.core.sequence.compound.NucleotideCompound;
import org.biojava3.core.sequence.template.SequenceView;
import ru.pavlenov.bio.utils.Convert;
import ru.pavlenov.bio.utils.Dump;
import ru.pavlenov.bio.utils.File;

import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Created by laser13 on 28.06.14.
 *
 * Complementing a Strand of DNA
 * http://rosalind.info/problems/rvco/
 *
 * Given: A collection of n (n≤10) DNA strings.
 * Return: The number of given strings that match their reverse complements.
 *
 */
public class Rvco {

    public static void start() throws IOException {

        String data = File.readFile("/home/laser13/IdeaProjects/bio/src/ru/pavlenov/bio/chapter/rosalind/sequence/Rvco.data", Charset.defaultCharset());

        FASTAFileReaderImpl fasta = new FASTAFileReaderImpl(new StringReader(data));
        final FASTAElementIterator it = fasta.getIterator();

        int count = 0;

        while (it.hasNext()) {
            DNASequence dna = new DNASequence(it.next().getSequence());
            SequenceView<NucleotideCompound> rc = dna.getReverseComplement();

            Dump.println(dna.toString());
            Dump.println(rc.getSequenceAsString());

            if (dna.toString().equals(rc.getSequenceAsString())) {
                count++;
            }
        }

        Dump.println(count);

    }

}
