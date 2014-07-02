package ru.pavlenov.bio.amino;

import java.util.ArrayList;

/**
 * Какой сам ☭
 * User: Павленов Семён
 * Date: 21.12.13 20:12
 */
public class LocalAlignment {

    private String str1;
    private String str2;

    private ArrayList<Character> result1;
    private ArrayList<Character> result2;

    private int match = 1;
    private int σ = 0;
    private ScoryngMatrixType scoryngMatrixType;

    private Move[][] trackMatrix;
    private Integer[][] weightMatrix;
    private Integer globalMaxWeight;
    private Integer startI;
    private Integer startJ;


    public Integer getStartI() {
        return startI;
    }

    public Integer getStartJ() {
        return startJ;
    }

    public Integer getGlobalMaxWeight() {
        return globalMaxWeight;
    }

    public enum ScoryngMatrixType { BLOSUM62, PAM250 }


    public LocalAlignment(String str1, String str2) {
        this.str1 = str1;
        this.str2 = str2;

        result1 = new ArrayList<>();
        result2 = new ArrayList<>();

        trackMatrix = new Move[str1.length()][str2.length()];
        weightMatrix= new Integer[str1.length()+1][str2.length()+1];

    }

    public int getMatch() {
        return match;
    }

    public LocalAlignment setMatch(int match) {
        this.match = match;
        return this;
    }

    public LocalAlignment setScoringMatrix(ScoryngMatrixType scoryngMatrixType) {
        this.scoryngMatrixType = scoryngMatrixType;
        return this;
    }

    public LocalAlignment setPenalty(int σ) {
        this.σ = σ;
        return this;
    }

    private int getScore(Character c1, Character c2) throws InvalidAlphabetException {

        int result = match;

        if (c1 != c2) {
            result = -σ;
        }

        if (scoryngMatrixType == ScoryngMatrixType.BLOSUM62) {
            result = Blosum62.getDistance(c1, c2);
        }

        if (scoryngMatrixType == ScoryngMatrixType.PAM250) {
            result = PAM250.getDistance(c1, c2);
        }

        return result;

    }

    public ArrayList<Character> getResult1() {
        return result1;
    }

    public ArrayList<Character> getResult2() {
        return result2;
    }

    public void make() throws InvalidAlphabetException {

        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();

        weightMatrix[0][0] = 0;
        globalMaxWeight = 0;
        for (int i = 1; i <= s1.length; i++) {
            weightMatrix[i][0] = weightMatrix[i-1][0] - σ;
        }
        for (int j = 1; j <= s2.length; j++) {
            weightMatrix[0][j] = weightMatrix[0][j-1] - σ;
        }

        for (int i = 1; i <= s1.length; i++) {

            for (int j = 1; j <= s2.length; j++) {

                int down = weightMatrix[i-1][j] - σ;
                int right = weightMatrix[i][j-1] - σ;
                int bias = weightMatrix[i-1][j-1];

                bias += getScore(s1[i-1], s2[j-1]);

                Move move;
                int max;
                if (down >= right) {
                    move = Move.DOWN;
                    max = down;
                }
                else {
                    move = Move.RIGHT;
                    max = right;
                }

                if (bias > max) {
                    if (s1[i-1] == s2[j-1]) {
                        move = Move.MATCH;
                    }
                    else {
                        move = Move.MIS;
                    }
                    max = bias;
                }

                if (max < 0) max = 0;
                if (max > globalMaxWeight) {
                    globalMaxWeight = max;
                    startI = i;
                    startJ = j;
                }

                weightMatrix[i][j] = max;
                trackMatrix[i-1][j-1] = move;

            }

        }

    }

    public void track(int i, int j) {

        if (weightMatrix[i+1][j+1] == 0) return;

        if (i == -1 || j == -1) {
            if (j == 0) {
                result2.add(str2.charAt(j));
                result1.add('-');
                return;
            }
            if (i == 0) {
                result2.add('-');
                result1.add(str1.charAt(i));
                return;
            }

            return;
        }

//        Dump.println(i + ":" + j + "" + trackMatrix[i][j]);

        if (trackMatrix[i][j].equals(Move.DOWN)) {
            track(i - 1, j);
            result2.add('-');
            result1.add(str1.charAt(i));
        }
        else if (trackMatrix[i][j].equals(Move.RIGHT)) {
            track(i, j-1);
            result2.add(str2.charAt(j));
            result1.add('-');
        }
        else {
            track(i-1, j-1);
            result1.add(str1.charAt(i));
            result2.add(str2.charAt(j));

        }



    }

    public Move[][] getTrackMatrix() {
        return trackMatrix;
    }

    public Integer[][] getWeightMatrix() {
        return weightMatrix;
    }

}
