
import ru.pavlenov.bio.amino.InvalidAlphabetException;
import ru.pavlenov.bio.chapter.rosalind.algorithmic.*;
import ru.pavlenov.bio.chapter.rosalind.alignment.*;
import ru.pavlenov.bio.chapter.rosalind.assembly.*;
import ru.pavlenov.bio.chapter.rosalind.combinatorics.*;
import ru.pavlenov.bio.chapter.rosalind.graph_algoritm.*;
import ru.pavlenov.bio.chapter.rosalind.probability.*;
import ru.pavlenov.bio.chapter.rosalind.proteomics.*;
import ru.pavlenov.bio.chapter.rosalind.sequence.*;
import ru.pavlenov.bio.chapter.rosalind.string_algoritm.Kmer;
import ru.pavlenov.bio.chapter.rosalind.string_algoritm.Lcsq;
import ru.pavlenov.bio.chapter.rosalind.textbook.*;
import ru.pavlenov.bio.chapter.rosalind.assembly.Long;
import ru.pavlenov.bio.chapter.stepic.*;
import ru.pavlenov.bio.utils.Dump;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InvalidAlphabetException, InterruptedException {

        Dump.println("START JOB!!");

        long start = System.nanoTime();

//        Dump.println(112 % 9);
//        Dump.println(((-5 % 9) + 9) % 9);

        Rear.start();

        long end = System.nanoTime();

        Dump.line();
        Dump.println("Time: " + (end - start) / 1_000_000f + " ms.");

    }
}
