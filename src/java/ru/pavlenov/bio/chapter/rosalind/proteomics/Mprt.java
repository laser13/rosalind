package ru.pavlenov.bio.chapter.rosalind.proteomics;

import net.sf.jfasta.FASTAElement;
import net.sf.jfasta.impl.FASTAElementIterator;
import net.sf.jfasta.impl.FASTAFileReaderImpl;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import ru.pavlenov.bio.utils.Dump;
import ru.pavlenov.bio.utils.File;

import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by laser13 on 23.06.14.
 *
 * Finding a Protein Motif
 * http://rosalind.info/problems/mprt/
 *
 * For example, the data for protein B5ZC00 can be found at http://www.uniprot.org/uniprot/B5ZC00.
 * Given: At most 15 UniProt Protein Database access IDs.
 * Return: For each protein possessing the N-glycosylation motif, output its given access ID followed by a list of
 * locations in the protein string where the motif can be found.
 *
 * To allow for the presence of its varying forms, a protein motif is represented by a shorthand as follows:
 * [XY] means "either X or Y" and {X} means "any amino acid except X."
 * For example, the N-glycosylation motif is written as N{P}[ST]{P}.
 *
 * N-glycosylation motif = N{P}[ST]{P}
 */
public class Mprt {

    public static void start() throws IOException {

        HttpClient client = new HttpClient();

        String data = File.readFile("/home/laser13/IdeaProjects/bio/src/ru/pavlenov/bio/chapter/rosalind/proteomics/Mprt.data", Charset.defaultCharset());

        for (String code : data.split("\n")) {

            HttpMethod method = new GetMethod("http://www.uniprot.org/uniprot/"+code+".fasta");

            // Execute the method.
            int statusCode = client.executeMethod(method);

            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method failed: " + method.getStatusLine());
            }

            // Read the response body.
            byte[] responseBody = method.getResponseBody();

            // Deal with the response.
            // Use caution: ensure correct character encoding and is not binary data
            String fastaText = new String(responseBody);

            FASTAFileReaderImpl fasta = new FASTAFileReaderImpl(new StringReader(fastaText));
            final FASTAElementIterator it = fasta.getIterator();

            ArrayList<Integer> index = new ArrayList<>();

            while (it.hasNext()) {
                FASTAElement el = it.next();
                char[] acids = el.getSequence().toCharArray();
                for (int i = 0; i < acids.length-3; i++) {
                    if (acids[i] == 'N' && acids[i + 1] != 'P' && (acids[i + 2] == 'S' || acids[i + 2] == 'T') && acids[i + 3] != 'P') {
                        index.add(i + 1);
                    }
                }
            }

            if (!index.isEmpty()) {
                Dump.println(code);
                for (Integer j : index) {
                    Dump.print(j + " ");
                }
                Dump.ln();
            }

        }
    }
}
