package ru.pavlenov.bio.amino;

import ru.pavlenov.bio.utils.Dump;

import java.util.ArrayList;

/**
 * Какой сам ☭
 * User: Павленов Семён
 * Date: 06.01.14 14:11
 */
public class MultipleAlignment {

    private String str1;
    private String str2;
    private String str3;

    private Integer len1;
    private Integer len2;
    private Integer len3;

    private ArrayList<Character> result1;
    private ArrayList<Character> result2;
    private ArrayList<Character> result3;

    private Integer[][][] weightArray;
    private Move[][][] trackArray;

    public ArrayList<Character> getResult1() {
        return result1;
    }

    public ArrayList<Character> getResult2() {
        return result2;
    }

    public ArrayList<Character> getResult3() {
        return result3;
    }

    public enum Move {
        MOVE_I,
        MOVE_J,
        MOVE_L,
        MOVE_IJ,
        MOVE_IL,
        MOVE_JL,
        MOVE_IJL
    }

    public MultipleAlignment(String str1, String str2, String str3) {

        this.str1 = str1;
        this.str2 = str2;
        this.str3 = str3;

        this.len1 = str1.length();
        this.len2 = str2.length();
        this.len3 = str3.length();

        result1 = new ArrayList<>();
        result2 = new ArrayList<>();
        result3 = new ArrayList<>();

        weightArray = new Integer[len1+1][len2+1][len3+1];
        trackArray = new Move[len1][len2][len3];

    }

    public Integer getScore(Character c1, Character c2, Character c3) {
        int score = 0;
        if (c1 == c2 && c2 == c3) score = 1;
        return score;
    }

    public void run() {

        weightArray[0][0][0] = 0;

        for (int i = 0; i <= len1; i++)
            for (int j = 0; j <= len2; j++) weightArray[i][j][0] = 0;
        for (int j = 0; j <= len2; j++)
            for (int l = 0; l <= len3; l++) weightArray[0][j][l] = 0;
        for (int l = 0; l <= len3; l++)
            for (int i = 0; i <= len1; i++) weightArray[i][0][l] = 0;

        for (int i = 1; i <= len1; i++) {

            for (int j = 1; j <= len2; j++) {

                for (int l = 1; l <= len3; l++) {

//                    Dump.println("[" + i + ":" + j + ":" + l + "]");

                    int scoreI = weightArray[i-1][j][l] + getScore(str1.charAt(i-1), ' ', ' ');
                    int scoreIL = weightArray[i-1][j][l-1] + getScore(str1.charAt(i-1), ' ', str3.charAt(l-1));
                    int scoreIJ = weightArray[i-1][j-1][l] + getScore(str1.charAt(i-1), str2.charAt(j-1), ' ');
                    int scoreJ = weightArray[i][j-1][l] + getScore(' ', str2.charAt(j-1), ' ');
                    int scoreJL = weightArray[i][j-1][l-1] + getScore(' ', str2.charAt(j-1), str3.charAt(l-1));
                    int scoreL = weightArray[i][j][l-1] + getScore(' ', ' ', str3.charAt(l-1));
                    int scoreIJL = weightArray[i-1][j-1][l-1] + getScore(str1.charAt(i-1), str2.charAt(j-1), str3.charAt(l-1));

//                    Dump.println(scoreI + " | " + scoreJ + " | " + scoreL);
//                    Dump.println(scoreIJ + " | " + scoreJL + " | " + scoreIL);
//                    Dump.println(scoreIJL);

                    int maxScore;
                    Move move;
                    if (scoreI > scoreJ) {
                        maxScore = scoreI;
                        move = Move.MOVE_I;
                    }
                    else {
                        maxScore = scoreJ;
                        move = Move.MOVE_J;
                    }

                    if (scoreL > maxScore) {
                        maxScore = scoreL;
                        move = Move.MOVE_L;
                    }

                    if (scoreIJ >= maxScore) {
                        maxScore = scoreIJ;
                        move = Move.MOVE_IJ;
                    }

                    if (scoreIL >= maxScore) {
                        maxScore = scoreIL;
                        move = Move.MOVE_IL;
                    }

                    if (scoreJL >= maxScore) {
                        maxScore = scoreJL;
                        move = Move.MOVE_JL;
                    }

                    if (scoreIJL >= maxScore) {
                        maxScore = scoreIJL;
                        move = Move.MOVE_IJL;
                    }

                    weightArray[i][j][l] = maxScore;
                    trackArray[i-1][j-1][l-1] = move;

                }
            }
        }

    }

    public void backTrack(int i, int j, int l) {

//        Dump.println("---");
//        Dump.println("[" + i + ":" + j + ":" + l + "]");

        Character cI = null;
        Character cJ = null;
        Character cL = null;

        if (i < 0) {
            i = 0; cI = '-';
        }
        if (j < 0) {
            j = 0; cJ = '-';
        }
        if (l < 0) {
            l = 0; cL = '-';
        }

        boolean next = true;

        Move move = trackArray[i][j][l];

        if (i == 0 && j == 0 && l == 0) next = false;
        if (cI == null) cI = str1.charAt(i);
        if (cJ == null) cJ = str2.charAt(j);
        if (cL == null) cL = str3.charAt(l);

//        Dump.println("[" + i + ":" + j + ":" + l + "] -> " + move);

        if (move == Move.MOVE_IJL) {
            if (next) backTrack(i-1, j-1, l-1);
            result1.add(cI);
            result2.add(cJ);
            result3.add(cL);
            return;
        }
        if (move == Move.MOVE_IJ) {
            if (next) backTrack(i-1, j-1, l);
            result1.add(cI);
            result2.add(cJ);
            result3.add('-');
            return;
        }
        if (move == Move.MOVE_IL) {
            if (next) backTrack(i-1, j, l-1);
            result1.add(cI);
            result2.add('-');
            result3.add(cL);
            return;
        }
        if (move == Move.MOVE_JL) {
            if (next) backTrack(i, j-1, l-1);
            result1.add('-');
            result2.add(cJ);
            result3.add(cL);
            return;
        }
        if (move == Move.MOVE_I) {
            if (next) backTrack(i-1, j, l);
            result1.add(str1.charAt(i));
            result2.add('-');
            result3.add('-');
            return;
        }
        if (move == Move.MOVE_J) {
            if (next) backTrack(i, j-1, l);
            result1.add('-');
            result2.add(cJ);
            result3.add('-');
            return;
        }
        if (move == Move.MOVE_L) {
            if (next) backTrack(i, j, l-1);
            result1.add('-');
            result2.add('-');
            result3.add(cL);
            return;
        }

    }

    public Integer[][][] getWeightArray() {
        return weightArray;
    }

    public Move[][][] getTrackArray() {
        return trackArray;
    }

}
