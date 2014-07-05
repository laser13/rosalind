package ru.pavlenov.bio.genome;

import ru.pavlenov.bio.utils.Dump;
import ru.pavlenov.bio.utils.Sort;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by semen on 25.11.13.
 */
public class Motif {

    private String[] kmerList;
    private int rowCnt = 0;
    private int colCnt = 0;
    private Integer score;
    private Integer[][] count;
    private Float[][] profile;
    private String consensus;

    public Motif(String[] kmerList) {
        this.kmerList = kmerList;
        this.colCnt = kmerList.length;
        if (this.colCnt > 0) {
            this.rowCnt = kmerList[0].length();
        }
        this.count = new Integer[4][rowCnt];
        this.profile = new Float[4][rowCnt];
        this.score = 0;
        this.consensus = "";
        calc(0);
    }

    public Motif(String[] kmerList, int pseudoCount) {
        this.kmerList = kmerList;
        this.colCnt = kmerList.length;
        if (this.colCnt > 0) {
            this.rowCnt = kmerList[0].length();
        }
        this.count = new Integer[4][rowCnt];
        this.profile = new Float[4][rowCnt];
        this.score = 0;
        this.consensus = "";
        calc(pseudoCount);
    }

    public int getScore() {
        return score;
    }

    public Float[][] getProfile() {
        return profile;
    }

    public Integer[][] getCount() {
        return count;
    }

    public String getConsensus() {
        return consensus;
    }

    public String[] getKmerList() {
        return kmerList;
    }

    public void calc(int pseudoCount) {

        float total = (float) colCnt;
        int delta = 0;
        if (pseudoCount > 0) {
            total += pseudoCount;
            delta = 1;
        }

        for (int i = 0; i < rowCnt; i++) {

            HashMap<Character, Integer> column = new HashMap<>();
            column.put('A', delta);
            column.put('C', delta);
            column.put('G', delta);
            column.put('T', delta);

            for (String kmer : kmerList) {
                char n = kmer.charAt(i);
                int w = column.get(n);
                column.put(n, w + 1);
            }

            count[0][i] = column.get('A');
            count[1][i] = column.get('C');
            count[2][i] = column.get('G');
            count[3][i] = column.get('T');

            profile[0][i] = column.get('A') / 1f;
            profile[1][i] = column.get('C') / 1f;
            profile[2][i] = column.get('G') / 1f;
            profile[3][i] = column.get('T') / 1f;

            HashMap<Character, Integer> sortColumn = (HashMap<Character, Integer>) Sort.byValue(column, -1);

            Map.Entry<Character, Integer> bestPair = sortColumn.entrySet().iterator().next();

            score += (colCnt - bestPair.getValue());
            consensus += bestPair.getKey();

        }

    }



}
