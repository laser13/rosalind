package ru.pavlenov.bio.chapter.rosalind.string_algoritm;

import net.sf.jfasta.FASTAElement;
import net.sf.jfasta.impl.FASTAElementIterator;
import net.sf.jfasta.impl.FASTAFileReaderImpl;
import ru.pavlenov.bio.utils.Dump;
import ru.pavlenov.bio.utils.File;

import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Какой сам ☭
 * User: Павленов Семён
 * Date: 11.01.14 19:51
 */
public class Lcsm {

    /**
     * Finding a Shared Motif
     * http://rosalind.info/problems/lcsm/
     *
     * Given: A collection of k (k≤100) DNA strings of length at most 1 kbp each in FASTA format.
     * Return: A longest common substring of the collection.
     * (If multiple solutions exist, you may return any single solution.)
     *
     */
    public static void start() throws IOException {

        String data = File.readFile("/home/laser13/IdeaProjects/bio/src/ru/pavlenov/bio/chapter/rosalind/string_algoritm/Lcsm.data", Charset.defaultCharset());

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
        int len = text.get(0).length();

        String maxSub = "";

        for (int l = 1; l <= len; l++) {

//            Dump.println(">>" + l + " | " + text.get(0));
            for (int s = 0; s <= len - l; s++) {

                int currCount = 1;
                String currSub = text.get(0).substring(s, s + l);
//                Dump.println(currSub);

                for (int i = 1; i < count; i++) {

                    if (text.get(i).contains(currSub)) {
                        currCount++;
                    }

                }

                if (currCount == count) {
                    maxSub = currSub;
                    break;
                }

            }

        }

        Dump.println(maxSub);

    }

}
