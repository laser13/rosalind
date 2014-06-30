package ru.pavlenov.bio.genome;

import ru.pavlenov.bio.utils.*;
import ru.pavlenov.bio.utils.Random;

import java.util.*;

/**
 * Какой сам ☭
 * User: Павленов Семён
 * Date: 23.11.13 13:26
 */
public class DNAArray {

    private String[] dnaList;

    public DNAArray(String[] dnaList) {
        this.dnaList = dnaList;
    }

    public class MotifItem {

        private String kmer;
        private int count = 0;
        private ArrayList<String> genome = new ArrayList<>();
        private int prevalence = 0;

        public MotifItem(String kmer, int count) {
            this.kmer = kmer;
            this.count = count;
        }

        public MotifItem incCount(int count) {
            this.count += count;
            return this;
        }

        public int getCount() {
            return count;
        }

        public MotifItem addGen(String genome) {
            if (!this.genome.contains(genome)) {
                this.genome.add(genome);
                this.prevalence = this.genome.size();
            }
            return this;
        }

        public String toString() {
            return "{ " + kmer + " : { " + count + ", " + prevalence + " } }";
        }

        public int getPrevalence() {
            return prevalence;
        }
    }


    public HashMap<String, Integer> findMotif(int kLen, int maxMismatch) {


        HashMap<String, MotifItem> result = new HashMap<>();

        for (String genome : dnaList) {

            HashMap<String, ArrayList<Integer>> kmerList = Kmer.findAll(genome, kLen);
            ArrayList<String> mismatchList = new ArrayList<>();

            for (Map.Entry<String, ArrayList<Integer>> pair : kmerList.entrySet()) {
                Collections.addAll(mismatchList, Kmer.genMismatch(pair.getKey(), maxMismatch));
            }

            for (Map.Entry<String, ArrayList<Integer>> pair : kmerList.entrySet()) {

                String kmer = pair.getKey();
                Integer count = pair.getValue().size();

                for (String mismatch : mismatchList) {

                    if (Kmer.isMismatch(kmer, mismatch, maxMismatch)) {

                        if (result.containsKey(mismatch)) {

                            result.get(mismatch).incCount(count).addGen(genome);

                        } else {

                            MotifItem nm = new MotifItem(mismatch, count);
                            nm.addGen(genome);
                            result.put(mismatch, nm);

                        }

                    }

                }

            }

        }

        HashMap<String, Integer> unsortResult = new HashMap<>();
        for (Map.Entry<String, MotifItem> motif : result.entrySet()) {
            if (motif.getValue().getPrevalence() == dnaList.length) {
                unsortResult.put(motif.getKey(), motif.getValue().getCount());
            }
        }

        return (HashMap<String, Integer>) Sort.byValue(unsortResult, -1);

    }

    public static int hammingDistance(String kmer, String text) {

        int kmerLen = kmer.length();
        int textLen = text.length();
        int hd = kmerLen;

        for (int i = 0; i <= textLen - kmerLen; i++) {

            String kmer0 = text.substring(i, i + kmerLen);
            int mm = Kmer.calcMismatch(kmer, kmer0);

            if (mm < hd) {
                hd = mm;
            }
        }
        return hd;

    }

    public ArrayList<String> findMedian(int kmerLen) {

        // Нужно получить все возможные k-mers длиной kmerLen
        String[] kmerList = Kmer.genAll(kmerLen);

        ArrayList<String> bestKmer = new ArrayList<>();
        bestKmer.add(kmerList[0]);
        int bestHD = bestKmer.size() * dnaList.length;

        for (String kmer : kmerList) {

            int currHD = 0;
            for (String dna : dnaList) {
                currHD += hammingDistance(kmer, dna);
            }
            if (bestHD > currHD) {
                bestKmer.clear();
                bestKmer.add(kmer);
                bestHD = currHD;
            }
            else if (bestHD == currHD) {
                bestKmer.add(kmer);
            }

        }

        return bestKmer;

    }

    /**
     *
     * https://beta.stepic.org/Bioinformatics-Algorithms-2/Greedy-Motif-Search-159/#step-4
     *
     * @param kmerLen
     * @param t
     * @return
     */
    public Motif greedyMotifSearch(int kmerLen, int t) {

        Motif bestMotif = new Motif(getMotif(kmerLen, 0, dnaList.length));
        String dna0 = dnaList[0];

        Dump.println(dnaList);
        int step = 60;

        for (int l = 0; l < dna0.length() - kmerLen + 1; l++) {
            // Проходим по всем k-mer первой строки dna

            String[] motifs = new String[t];
            ArrayList<String> motifs0 = new ArrayList<>();
            motifs[0] = dna0.substring(l, l + kmerLen);
            motifs0.add(motifs[0]);

            for (int j = 1; j < t; j++) {

                Motif motif0 = new Motif(motifs0.toArray(new String[motifs0.size()]), l+1);
                HashMap<String, Float> sortList = findMostProbable(j, kmerLen, motif0.getProfile());

                Map.Entry<String, Float> bestPair = sortList.entrySet().iterator().next();

                String kmer0 = bestPair.getKey();

                motifs[j] = kmer0;
                motifs0.add(kmer0);
            }

            Motif rivalMotif = new Motif(motifs);

            if (rivalMotif.getScore() < bestMotif.getScore()) {
                bestMotif = rivalMotif;
            }
        }

        return bestMotif;

    }

    public Motif randomizedMotifSearch(int kmerLen, int t, int iterCount) {

        Motif resultMotif = new Motif(getRandomMotif(kmerLen, t), 8);

        int counter = 0;

        int pr = (iterCount / 100);
        long start = System.currentTimeMillis();

        Dump.println("1% = " + pr);

        while (counter < iterCount) {

            Motif bestMotif = new Motif(getRandomMotif(kmerLen, t), 8);
//            Dump.println(bestMotif.getKmerList());

            boolean forever = true;

            while (forever) {

                Float[][] profile = bestMotif.getProfile();
                Motif rivalMotif = new Motif(getMotif(kmerLen, t, profile), 8);

                if (rivalMotif.getScore() < bestMotif.getScore()) {
                    bestMotif = rivalMotif;
                }
                else {
                    forever = false;
                }

            }

            if (bestMotif.getScore() < resultMotif.getScore()) {
                resultMotif = bestMotif;
            }


            if (counter % (pr * 5) == 0) {
                long end = System.currentTimeMillis();
                Dump.println(counter/pr+ "%, " + (end - start) + " millis.");
                start = System.currentTimeMillis();
            }

            counter++;

        }

        return resultMotif;

    }

    public Motif gibbsSampler(int kmerLen, int t, int iterCount) {


        Motif resultMotif = new Motif(getRandomMotif(kmerLen, t), 1);

        for (int l = 0; l < 20; l++) {

            Motif bestMotif = new Motif(resultMotif.getKmerList(), 1);
            for (int i = 0; i < iterCount; i++) {

                int j = Random.range(0, t);
                String[] motifs = bestMotif.getKmerList();
                String[] motifs0 = new String[motifs.length - 1];

                int counter = 0;
                int counter0 = 0;
                for (String kmer : motifs) {
                    if (counter != j) {
                        motifs0[counter0] = kmer;
                        counter0++;
                    }
                    counter++;
                }

                motifs[j] = findRandomlyProbable(j, kmerLen, new Motif(motifs0, 1).getProfile());

//                Dump.println(motifs);

                Motif rivalMotif = new Motif(motifs, 1);

                if (rivalMotif.getScore() < bestMotif.getScore()) {
                    bestMotif = rivalMotif;
                }

            }

            if (bestMotif.getScore() < resultMotif.getScore()) {
                resultMotif = bestMotif;
            }

        }

        return resultMotif;

    }

    public String[] getMotif(int kmerLen, int count, Float[][] profile) {

        String[] result = new String[count];
        for (int i = 0; i < count; i++) {
            result[i] = findMostProbable(i, kmerLen, profile).entrySet().iterator().next().getKey();
        }
        return result;

    }

    public String[] getMotif(int kmerLen, int index, int count) {

        String[] result = new String[count];
        for (int i = 0; i < count; i++) {
            result[i] = dnaList[i].substring(index, index + kmerLen);
        }
        return result;

    }

    public String[] getRandomMotif(int kmerLen, int count) {

        int min = 0;
        int max = dnaList[0].length() - kmerLen;

        String[] result = new String[count];
        for (int i = 0; i < count; i++) {

            int index = Random.range(min, max);

            result[i] = dnaList[i].substring(index, index + kmerLen);
        }
        return result;

    }

    public HashMap<String, Float> findMostProbable(int index, int kmerLen, Float[][] profile) {

        String dna = dnaList[index];
        Character[] nucleatide = Nucleotide.asDNAArray();
        HashMap<String, Float> probableList = new LinkedHashMap<>();
        for (int l = 0; l < dna.length() - kmerLen + 1; l++) {

            String kmer = dna.substring(l, l + kmerLen);

//            Dump.print(kmer); Dump.print(", ");

            char[] charList = kmer.toCharArray();
            float p = 1;
            for (int i = 0; i < charList.length; i++) {
                int j = Arrays.binarySearch(nucleatide, charList[i]);
                p *= profile[j][i];
            }
            probableList.put(kmer, p);
        }

        HashMap<String, Float> sortList = (HashMap<String, Float>) Sort.byValue(probableList, -1);

//        Dump.println(profile);
//        Dump.println("mostProbable: " + bestPair.getKey() + " - " + bestPair.getValue());

        return sortList;

    }

    public String findRandomlyProbable(int index, int kmerLen, Float[][] profile) {

        String dna = dnaList[index];
        Character[] nucleatide = Nucleotide.asDNAArray();
        Random.RndWeightItem[] probableList = new Random.RndWeightItem[dna.length() - kmerLen + 1];
        for (int l = 0; l < dna.length() - kmerLen + 1; l++) {

            String kmer = dna.substring(l, l + kmerLen);

            char[] charList = kmer.toCharArray();
            float p = 1;
            for (int i = 0; i < charList.length; i++) {
                int j = Arrays.binarySearch(nucleatide, charList[i]);
                p *= profile[j][i];
            }
            probableList[l] = new ru.pavlenov.bio.utils.Random.RndWeightItem<>(kmer, p);
        }

        return (String) Random.weightRange(probableList).getValue();

    }

}
