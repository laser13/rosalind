package ru.pavlenov.bio.amino;

import java.util.HashMap;
import java.util.function.BiConsumer;

/**
 * Какой сам ☭
 * User: Павленов Семён
 * Date: 16.11.13 17:00
 */
public class Codon {

    public static class Acid {

        private String shortName;
        private String fullName;
        private String formula;
        private double mass;
        private String[] triplet;

        public Acid(String shortName, String fullName, String formula, double mass, String[] triplet) {

            this.shortName = shortName;
            this.fullName = fullName;
            this.formula = formula;
            this.mass = mass;
            this.triplet = triplet;

        }

        public String getShortName() {
            return shortName;
        }

        public String getFullName() {
            return fullName;
        }

        public String[] getTriplet() {
            return triplet;
        }

        public double getTrueMass() {
            return mass;
        }

        public int getMass() {
            return (int) mass;
        }
    }

    private static final HashMap<String, Character> hmP2A;

    static {
        hmP2A = new HashMap<>();
        hmP2A.put("AAA", 'K');
        hmP2A.put("AAC", 'N');
        hmP2A.put("AAG", 'K');
        hmP2A.put("AAU", 'N');
        hmP2A.put("ACA", 'T');
        hmP2A.put("ACC", 'T');
        hmP2A.put("ACG", 'T');
        hmP2A.put("ACU", 'T');
        hmP2A.put("AGA", 'R');
        hmP2A.put("AGC", 'S');
        hmP2A.put("AGG", 'R');
        hmP2A.put("AGU", 'S');
        hmP2A.put("AUA", 'I');
        hmP2A.put("AUC", 'I');
        hmP2A.put("AUG", 'M');
        hmP2A.put("AUU", 'I');
        hmP2A.put("CAA", 'Q');
        hmP2A.put("CAC", 'H');
        hmP2A.put("CAG", 'Q');
        hmP2A.put("CAU", 'H');
        hmP2A.put("CCA", 'P');
        hmP2A.put("CCC", 'P');
        hmP2A.put("CCG", 'P');
        hmP2A.put("CCU", 'P');
        hmP2A.put("CGA", 'R');
        hmP2A.put("CGC", 'R');
        hmP2A.put("CGG", 'R');
        hmP2A.put("CGU", 'R');
        hmP2A.put("CUA", 'L');
        hmP2A.put("CUC", 'L');
        hmP2A.put("CUG", 'L');
        hmP2A.put("CUU", 'L');
        hmP2A.put("GAA", 'E');
        hmP2A.put("GAC", 'D');
        hmP2A.put("GAG", 'E');
        hmP2A.put("GAU", 'D');
        hmP2A.put("GCA", 'A');
        hmP2A.put("GCC", 'A');
        hmP2A.put("GCG", 'A');
        hmP2A.put("GCU", 'A');
        hmP2A.put("GGA", 'G');
        hmP2A.put("GGC", 'G');
        hmP2A.put("GGG", 'G');
        hmP2A.put("GGU", 'G');
        hmP2A.put("GUA", 'V');
        hmP2A.put("GUC", 'V');
        hmP2A.put("GUG", 'V');
        hmP2A.put("GUU", 'V');
        hmP2A.put("UAA", '*');
        hmP2A.put("UAC", 'Y');
        hmP2A.put("UAG", '*');
        hmP2A.put("UAU", 'Y');
        hmP2A.put("UCA", 'S');
        hmP2A.put("UCC", 'S');
        hmP2A.put("UCG", 'S');
        hmP2A.put("UCU", 'S');
        hmP2A.put("UGA", '*');
        hmP2A.put("UGC", 'C');
        hmP2A.put("UGG", 'W');
        hmP2A.put("UGU", 'C');
        hmP2A.put("UUA", 'L');
        hmP2A.put("UUC", 'F');
        hmP2A.put("UUG", 'L');
        hmP2A.put("UUU", 'F');
    }

    private static final HashMap<Character, Acid> hmA2P;

    static {
        hmA2P = new HashMap<>();
        hmA2P.put('A', new Acid("Ala", "Alanine", "C3H5NO", 71.03711, new String[]{"GCA", "GCU", "GCC", "GCG"}));
        hmA2P.put('C', new Acid("Cys", "Cysteine", "C3H5NOS", 103.00919, new String[]{"UGC", "UGU"}));
        hmA2P.put('D', new Acid("Asp", "Aspartic acid", "C4H5NO3", 115.02694, new String[]{"GAU", "GAC"}));
        hmA2P.put('E', new Acid("Glu", "Glutamic acid", "C5H7NO3", 129.04259, new String[]{"GAA", "GAG"}));
        hmA2P.put('F', new Acid("Phe", "Phenylalanine", "C9H9NO", 147.06841, new String[]{"UUC", "UUU"}));
        hmA2P.put('G', new Acid("Gly", "Glycine", "C2H3NO", 57.02146, new String[]{"GGA", "GGU", "GGC", "GGG"}));
        hmA2P.put('H', new Acid("His", "Histidine", "C6H7N3O", 137.05891, new String[]{"GAU", "GAC"}));
        hmA2P.put('I', new Acid("Ile", "Isoleucine", "C6H11NO", 113.08406, new String[]{"AUU", "AUC", "AUA"}));
        hmA2P.put('K', new Acid("Lys", "Lysine", "C6H12N2O", 128.09496, new String[]{"AAA", "AAG"}));
        hmA2P.put('L', new Acid("Leu", "Leucine", "C6H11NO", 113.08406, new String[]{"UUG", "UUA", "CUA", "CUU", "CUC", "CUG"}));
        hmA2P.put('M', new Acid("Met", "Methionine", "C5H9NOS", 131.04049, new String[]{"AUG"}));
        hmA2P.put('N', new Acid("Asn", "Asparagine", "C4H6N2O2", 114.04293, new String[]{"AAC", "AAU"}));
        hmA2P.put('P', new Acid("Pro", "Proline", "C5H7NO", 97.05276, new String[]{"CCA", "CCU", "CCC", "CCG"}));
        hmA2P.put('Q', new Acid("Gln", "Glutamine","C5H8N2O", 128.05858, new String[]{"CAA", "CAG"}));
        hmA2P.put('R', new Acid("Arg", "Arginine", "C6H12N4O", 156.10111, new String[]{"AGA", "AGG", "CGA", "CGU", "CGC", "CGG"}));
        hmA2P.put('S', new Acid("Ser", "Serine", "C3H5NO2", 87.03203, new String[]{"AGU", "AGC", "UCU", "UCC", "UCA", "UCG"}));
        hmA2P.put('T', new Acid("Thr", "Threonine", "C4H7NO2", 101.04768, new String[]{"ACA", "ACU", "ACC", "ACG"}));
        hmA2P.put('V', new Acid("Val", "Valine", "C5H9NO", 99.06841, new String[]{"GUA", "GUU", "GUC", "GUG"}));
        hmA2P.put('W', new Acid("Trp", "Tryptophan", "C11H10N20", 186.07931, new String[]{"UGG"}));
        hmA2P.put('Y', new Acid("Tyr", "Tyrosine", "C9H9NO2", 163.06333, new String[]{"UAC", "UAU"}));
        hmA2P.put('*', new Acid("XXX", "Stop", null, 0, new String[]{"UGA", "UAG", "UAA"}));
    }

    private static final Integer[] mass = new Integer[]{57, 71, 87, 97, 99, 101, 103, 113, 114, 115, 128, 129, 131, 137, 147, 156, 163, 186};

    public static HashMap<Integer, Character> getHmM2A() {

        HashMap<Integer, Character> hmM2A = new HashMap<>();
        hmA2P.forEach((character, acid) -> {
            hmM2A.put(acid.getMass(), character);
        });
        return hmM2A;
    }

    public static HashMap<Double, Character> getMass2Acid() {

        HashMap<Double, Character> mass2acid = new HashMap<>();
        hmA2P.forEach((character, acid) -> {
            mass2acid.put(acid.getTrueMass(), character);
        });
        return mass2acid;
    }

    public static Integer[] getMass() {
        return mass;
    }

    public static HashMap<String, Character> getHmP2A() {
        return hmP2A;
    }

    public static HashMap<Character, Acid> getHmA2P() {
        return hmA2P;
    }

}
