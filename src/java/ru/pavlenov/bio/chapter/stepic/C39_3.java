package ru.pavlenov.bio.chapter.stepic;

import ru.pavlenov.bio.genome.DNAArray;
import ru.pavlenov.bio.genome.Motif;
import ru.pavlenov.bio.utils.Dump;

/**
 * Created by semen on 25.11.13.
 */
public class C39_3 {

    public static void start() {

        String tmp = "GGCGTTCAGGCA\n" +
                "AAGAATCAGTCA\n" +
                "CAAGGAGTTCGC\n" +
                "CACGTCAATCAC\n" +
                "CAATAATATTCG";

        String[] dnaList = tmp.split("\n");
        DNAArray dnaArray = new DNAArray(dnaList);

        Motif bestMotif = dnaArray.greedyMotifSearch(3, 5);

        Dump.println("");
        for (String kmer : bestMotif.getKmerList()) {

            Dump.print(kmer); Dump.println("");

        }
        Dump.println("");

    }

}
