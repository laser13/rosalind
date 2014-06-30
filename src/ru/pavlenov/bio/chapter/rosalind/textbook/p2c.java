package ru.pavlenov.bio.chapter.rosalind.textbook;

import ru.pavlenov.bio.amino.Peptide;
import ru.pavlenov.bio.amino.RNA;
import ru.pavlenov.bio.genome.DNA;
import ru.pavlenov.bio.utils.Dump;

import java.util.ArrayList;

/**
 * Created by laser13 on 23.06.14.
 *
 * Generating Theoretical Spectrum Problem
 * http://rosalind.info/problems/2c/
 *
 * Generate the theoretical spectrum of a cyclic peptide.
 * Given: An amino acid string Peptide.
 * Return: Cyclospectrum(Peptide).
 *
 */
public class p2c {

    public static void start() {

        String acid = "WPTCDCDCIMDED";

        Peptide peptide = new Peptide(acid);

        Dump.print(0 + " ");
        for (Integer mass : peptide.cyclospectrum()) {
            Dump.print(mass + " ");
        }
        Dump.ln();

    }

}
