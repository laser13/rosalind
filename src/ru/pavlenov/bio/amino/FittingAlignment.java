package ru.pavlenov.bio.amino;

import java.util.ArrayList;

/**
 * Какой сам ⚝
 * Author: Pavlenov Semen
 * Date: 24.12.13
 * <p>
 * E = mc^2
 */
public class FittingAlignment {

    private String str1;
    private String str2;

    private ArrayList<Character> result1;
    private ArrayList<Character> result2;

    private int σ;
    private int match;
    private ScoryngMatrixType scoryngMatrixType;

    private Move[][] trackMatrix;
    private Integer[][] weightMatrix;


    public int getMatch() {
        return match;
    }

    public FittingAlignment setMatch(int match) {
        this.match = match;
        return this;
    }

    public enum ScoryngMatrixType { BLOSUM62 }


    public FittingAlignment(String str1, String str2) {

        this.str1 = str1;
        this.str2 = str2;

        this.σ = -1;
        this.match = 0;

        result1 = new ArrayList<>();
        result2 = new ArrayList<>();

        trackMatrix = new Move[str1.length()][str2.length()];
        weightMatrix= new Integer[str1.length()+1][str2.length()+1];

    }

    public FittingAlignment setScoringMatrix(ScoryngMatrixType scoryngMatrixType) {
        this.scoryngMatrixType = scoryngMatrixType;
        return this;
    }

    public FittingAlignment setPenalty(int σ) {
        this.σ = σ;
        return this;
    }

    private int getScore(Character c1, Character c2) throws InvalidAlphabetException {

        int result = match;

        if (c1 != c2) {
            result = -σ;
        }

//        if (scoryngMatrixType == ScoryngMatrixType.BLOSUM62) {
//            result = Blosum62.getDistance(c1, c2);
//        }

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

                bias += getScore(s1[i - 1], s2[j - 1]);

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

                weightMatrix[i][j] = max;
                trackMatrix[i-1][j-1] = move;

            }

        }

    }

    public void track(int i, int j) {

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

    public String matrixToString() {

        StringBuilder result = new StringBuilder();

        result.append(" \t\t");
        for (Character x : str2.toCharArray()) {
            result.append(x).append("\t\t");
        }
        result.append("\n");

        int i = 0;
        for (Integer[] weightRow : weightMatrix) {

            if (i > 0) {
                result.append(str1.charAt(i-1)).append("\t\t");
                for (Move move : trackMatrix[i-1]) {
                    result.append(move).append("\t\t");
                }
                result.append("\n");
            }

            result.append("|").append("\t");

            int j = 0;
            for (Integer weight : weightRow) {

                result.append(weight).append("\t\t");
                j++;

            }

            result.append("|").append("\n");
            i++;

        }

        return result.toString();

    }

}
