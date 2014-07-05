package ru.pavlenov.bio.algorithm;
/**
 * Title:     Knuth-Morris-Pratt string matching algorithm
 * Author:    H.W. Lang
 *            Fachhochschule Flensburg, University of Applied Sciences
 *            Flensburg, Germany
 * Date:      2007
 * Mail:      lang@fh-flensburg.de
 * Web:       http://www.inf.fh-flensburg.de/lang/algorithmen/pattern/kmpen.htm
 * Reference: D.E. Knuth, J.H. Morris, V.R. Pratt: Fast Pattern Matching in Strings.
 *            SIAM Journal of Computing 6, 2, 323-350 (1977)
 */

public class KmpStringMatcher {
    private char[] p, t;       // pattern, text
    private int m, n;          // pattern length, text length
    private String matches;    // string of match positions
    private char[] showmatches;// char array that shows matches
    private int[] b;           // used by the KMP algorithm

    /**
     * searches the text tt for the pattern pp
     */
    public void search(String tt, String pp) {
        setText(tt);
        setPattern(pp);
        kmpSearch();
    }

    /**
     * sets the text
     */
    private void setText(String tt) {
        n = tt.length();
        t = tt.toCharArray();
        initmatches();
    }

    /**
     * sets the pattern
     */
    private void setPattern(String pp) {
        m = pp.length();
        p = pp.toCharArray();
        b = new int[m + 1];
        kmpPreprocess();
    }

    /**
     * initializes match positions and the array showmatches
     */
    private void initmatches() {
        matches = "";
        showmatches = new char[n];
        for (int i = 0; i < n; i++)
            showmatches[i] = ' ';
    }

    /**
     * preprocessing of the pattern
     */
    private void kmpPreprocess() {
        int i = 0, j = -1;
        b[i] = j;
        while (i < m) {
            while (j >= 0 && p[i] != p[j])
                j = b[j];
            i++;
            j++;
            b[i] = j;
        }
    }

    /**
     * searches the text for all occurences of the pattern
     */
    private void kmpSearch() {
        int i = 0, j = 0;
        while (i < n) {
            while (j >= 0 && t[i] != p[j])
                j = b[j];
            i++;
            j++;
            if (j == m) // a match is found
            {
                report(i - m);
                j = b[j];
            }
        }
    }

    /**
     * reports a match
     */
    private void report(int i) {
        matches += i + " ";
        showmatches[i] = '^';
    }

    /**
     * returns the match positions after the search
     */
    public String getMatches() {
        return matches;
    }

    // only for test purposes
    public static void main(String[] args) {
        KmpStringMatcher stm = new KmpStringMatcher();
        String name = "Knuth-Morris-Pratt";
        System.out.println(name);
        String tt, pp;
        tt = "abcdabcd";
        pp = "abc";
        stm.search(tt, pp);
        System.out.println(pp);
        System.out.println(tt);
        System.out.println(stm.showmatches);
        System.out.println(stm.getMatches());
        tt = "abababaa";
        pp = "aba";
        stm.search(tt, pp);
        System.out.println(pp);
        System.out.println(tt);
        System.out.println(stm.showmatches);
        System.out.println(stm.getMatches());
    }

}    // end class KmpStringMatcher