package ru.pavlenov.bio.chapter.rosalind.graph_algoritm;

import net.sf.jfasta.FASTAElement;
import net.sf.jfasta.impl.FASTAElementIterator;
import net.sf.jfasta.impl.FASTAFileReaderImpl;
import ru.pavlenov.bio.utils.Dump;
import ru.pavlenov.bio.utils.File;

import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Какой сам ☭
 * User: Павленов Семён
 * Date: 11.01.14 19:04
 */
public class Grph {

    /**
     * Overlap Graphs
     * http://rosalind.info/problems/grph/
     *
     * Given: A collection of DNA strings in FASTA format having total length at most 10 kbp.
     * Return: The adjacency list corresponding to O3. You may return edges in any order.
     *
     */
    public static void start() throws IOException {

        int k = 3;
        String data = File.readFile("/home/laser13/IdeaProjects/bio/src/ru/pavlenov/bio/chapter/rosalind/graph_algoritm/Grph.data", Charset.defaultCharset());

        FASTAFileReaderImpl fasta = new FASTAFileReaderImpl(new StringReader(data));
        final FASTAElementIterator it = fasta.getIterator();

        ArrayList<String> name = new ArrayList<>();
        ArrayList<String> text = new ArrayList<>();

        while (it.hasNext()) {
            FASTAElement el = it.next();
            name.add(el.getHeader());
            text.add(el.getSequence());
        }
        int count = name.size();

        for (int i = 0; i < count; i++) {
            String suffix = suffix(k, text.get(i));
//            Dump.println(suffix);
            for (int j = 0; j < count; j++) {

                if (j == i) continue;

                String prefix = prefix(k, text.get(j));
//                Dump.println(prefix);
                if (suffix.equals(prefix)) {
                    Dump.println(name.get(i) + " " + name.get(j));
                }
            }
        }

    }

    public static String prefix(int k, String text) {
        return text.substring(0, k);
    }

    public static String suffix(int k, String text) {
        return text.substring(text.length() - k, text.length());
    }

}
