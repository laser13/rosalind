package ru.pavlenov.bio.amino;

import lombok.Getter;
import org.apache.commons.lang.StringUtils;
import ru.pavlenov.bio.genome.DNA;
import ru.pavlenov.bio.genome.Nucleotide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Какой сам ☭
 * User: Павленов Семён
 * Date: 16.11.13 17:17
 */
public class RNA {

    private String text;
    private String reverse;

    public RNA(String text) {
        this.text = text;
        reverse = reverse();
    }

    public String get() {
        return text;
    }

    public String getReverse() {
        return reverse;
    }

    public String reverse() {
        StringBuilder sb = new StringBuilder();
        for (Character ch : text.toCharArray()) {
            if (ch == 'A') sb.append('U');
            if (ch == 'U') sb.append('A');
            if (ch == 'C') sb.append('G');
            if (ch == 'G') sb.append('C');
        }
        return sb.reverse().toString();
    }

    public class Translate implements Iterable<Peptide> {

        @Getter
        private Peptide peptide1;
        @Getter
        private Peptide peptide2;
        @Getter
        private Peptide peptide3;

        @Getter
        private Peptide peptide4;
        @Getter
        private Peptide peptide5;
        @Getter
        private Peptide peptide6;


        public Peptide getPep1() {
            return peptide1;
        }

        public void setPep1(String text, String rnaText) {
            this.peptide1 = new Peptide(text, rnaText);
        }

        public Peptide getPep2() {
            return peptide2;
        }

        public void setPep2(String text, String rnaText) {
            this.peptide2 = new Peptide(text, rnaText);
        }

        public Peptide getPep3() {
            return peptide3;
        }

        public void setPep3(String text, String rnaText) {
            this.peptide3 = new Peptide(text, rnaText);
        }

        public Peptide getPep4() {
            return peptide4;
        }

        public void setPep4(String text, String rnaText) {
            this.peptide4 = new Peptide(text, rnaText);
        }

        public Peptide getPep5() {
            return peptide5;
        }

        public void setPep5(String text, String rnaText) {
            this.peptide5 = new Peptide(text, rnaText);
        }

        public Peptide getPep6() {
            return peptide6;
        }

        public void setPep6(String text, String rnaText) {
            this.peptide6 = new Peptide(text, rnaText);
        }

        /**
         * Find substrings of a genome encoding a given amino acid sequence.
         * Ищет подстроки в геноме которые могут кодировать указанную последовательность аминокислот
         *
         * http://rosalind.info/problems/2b/
         *
         * @param peptideText
         * @return
         */
        public ArrayList<String> findArea(String peptideText) {

            ArrayList<String> result = new ArrayList<>();

            ArrayList<Peptide> area1 = peptide1.getArea(peptideText);
            ArrayList<Peptide> area2 = peptide2.getArea(peptideText);
            ArrayList<Peptide> area3 = peptide3.getArea(peptideText);
            ArrayList<Peptide> area4 = peptide4.getArea(peptideText);
            ArrayList<Peptide> area5 = peptide5.getArea(peptideText);
            ArrayList<Peptide> area6 = peptide6.getArea(peptideText);

            if (area1 != null) {
                result.addAll(area1.stream().map(p -> p.getRna().get()).collect(Collectors.toList()));
            }
            if (area2 != null) {
                result.addAll(area2.stream().map(p -> p.getRna().get()).collect(Collectors.toList()));
            }
            if (area3 != null) {
                result.addAll(area3.stream().map(p -> p.getRna().get()).collect(Collectors.toList()));
            }
            if (area4 != null) {
                result.addAll(area4.stream().map(p -> p.getRna().getReverse()).collect(Collectors.toList()));
            }
            if (area5 != null) {
                result.addAll(area5.stream().map(p -> p.getRna().getReverse()).collect(Collectors.toList()));
            }
            if (area6 != null) {
                result.addAll(area6.stream().map(p -> p.getRna().getReverse()).collect(Collectors.toList()));
            }

            return result;

        }

        public String toString() {
            return "1-" + peptide1 + "\n" +
                   "2-" + peptide2 + "\n" +
                   "3-" + peptide3 + "\n" +
                   "4-" + peptide4 + "\n" +
                   "5-" + peptide5 + "\n" +
                   "6-" + peptide6;
        }

        public String toShortString() {

            return "1-" + peptide1.toShortString() + "\n" +
                    "2-" + peptide2.toShortString() + "\n" +
                    "3-" + peptide3.toShortString() + "\n" +
                    "4-" + peptide4.toShortString() + "\n" +
                    "5-" + peptide5.toShortString() + "\n" +
                    "6-" + peptide6.toShortString();
        }

        @Override
        public void forEach(Consumer action) {

        }

        @Override
        public Iterator<Peptide> iterator() {
            ArrayList<Peptide> list = new ArrayList<>();
            list.add(peptide1);
            list.add(peptide2);
            list.add(peptide3);
            list.add(peptide4);
            list.add(peptide5);
            list.add(peptide6);
            return list.iterator() ;
        }

        @Override
        public Spliterator<Peptide> spliterator() {
            return null;
        }
    }

    public Translate translate() {

        Translate translate = new Translate();
        HashMap<String, Character> codonMap = Codon.getHmP2A();
        int textLen = text.length();

        String[] peptideText1 = new String[]{"", "", ""};
        String[] rnaText1 = new String[3];
        for (int j = 0; j < 3; j++) {

            int end = (textLen - j) % 3;
            String text0 = text.substring(j, textLen - end);
            rnaText1[j] = text0;

            StringBuilder peptideText = new StringBuilder(text0.length() / 3);

            for (int i = 0; i < text0.length() - 1; i += 3) {
                peptideText.append(codonMap.get(text0.substring(i, i + 3)));
            }
            peptideText1[j] = peptideText.toString();
        }
        translate.setPep1(peptideText1[0], rnaText1[0]);
        translate.setPep2(peptideText1[1], rnaText1[1]);
        translate.setPep3(peptideText1[2], rnaText1[2]);

        String[] peptideText2 = new String[]{"", "", ""};
        String[] rnaText2 = new String[3];
        for (int j = 0; j < 3; j++) {

            int end = (textLen - j) % 3;
            String reverse0 = reverse.substring(j, textLen - end);
            rnaText2[j] = reverse0;

            StringBuilder peptideText = new StringBuilder(reverse0.length() / 3);

            for (int i = 0; i < reverse0.length() - 1; i += 3) {
                peptideText.append(codonMap.get(reverse0.substring(i, i + 3)));
            }

            peptideText2[j] = peptideText.toString();
        }
        translate.setPep4(peptideText2[0], rnaText2[0]);
        translate.setPep5(peptideText2[1], rnaText2[1]);
        translate.setPep6(peptideText2[2], rnaText2[2]);

        return translate;

    }

    /**
     * Ищет кол-во вхождений нуклеотида
     * @param needle
     * @return
     */
    public int count(char needle) {
        int c = 0;
        for (Character ch : text.toCharArray()) {
            if (ch == needle) c++;
        }
        return c;
    }

    public DNA toDNA() {
        return new DNA(toDNA(text));
    }

    public static String toDNA(String text) {
        return text.replaceAll("U", "T");
    }

    public String toString() {
        return text;
    }

}
