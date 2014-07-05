package ru.pavlenov.bio.amino;

import ru.pavlenov.bio.utils.Dump;

import java.util.ArrayList;

/**
 * Какой сам ☭
 * User: Павленов Семён
 * Date: 02.01.14 14:44
 */
public class AffineGlobalAlignment {

    private String str1;
    private String str2;

    private ArrayList<Character> result1;
    private ArrayList<Character> result2;

    private int σ;
    private int ε;
    private int match;
    private ScoryngMatrixType scoryngMatrixType;

    private Move[][] trackDownMatrix;
    private Move[][] trackRightMatrix;
    private Move[][] trackBiasMatrix;
    private Integer[][] downMatrix;
    private Integer[][] rightMatrix;
    private Integer[][] biasMatrix;

    public AffineGlobalAlignment setMatch(int match) {
        this.match = match;
        return this;
    }

    public enum ScoryngMatrixType { BLOSUM62 }


    public AffineGlobalAlignment(String str1, String str2) {

        this.str1 = str1;
        this.str2 = str2;

        this.ε = 1;
        this.σ = 10;
        this.match = 0;

        result1 = new ArrayList<>();
        result2 = new ArrayList<>();

        trackDownMatrix = new Move[str1.length()][str2.length()];
        trackRightMatrix = new Move[str1.length()][str2.length()];
        trackBiasMatrix = new Move[str1.length()][str2.length()];

        downMatrix = new Integer[str1.length()+1][str2.length()+1];
        rightMatrix = new Integer[str1.length()+1][str2.length()+1];
        biasMatrix = new Integer[str1.length()+1][str2.length()+1];

    }

    public AffineGlobalAlignment setScoringMatrix(ScoryngMatrixType scoryngMatrixType) {
        this.scoryngMatrixType = scoryngMatrixType;
        return this;
    }

    public AffineGlobalAlignment setGapOpenPenalty(int σ) {
        this.σ = σ;
        return this;
    }

    public AffineGlobalAlignment setGapExtPenalty(int ε) {
        this.ε = ε;
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

        biasMatrix[0][0] = 0;
        rightMatrix[0][0] = 0;
        downMatrix[0][0] = 0;

        for (int i = 1; i <= s1.length; i++) {
            biasMatrix[i][0] = -(σ + ε*i);
            rightMatrix[i][0] = -(σ + ε*i);
        }
        for (int j = 1; j <= s2.length; j++) {
            biasMatrix[0][j] = -(σ + ε*j);
            downMatrix[0][j] = -(σ + ε*j);
        }

        for (int i = 1; i <= s1.length; i++) {

            for (int j = 1; j <= s2.length; j++) {

                int upperRight = rightMatrix[i][j-1] - ε;
                int upperBias = biasMatrix[i][j-1] - σ;

                Move rightMove;
                int rightMax;
                if (upperRight >= upperBias) {
                    rightMove = Move.RIGHT;
                    rightMax = upperRight;
                }
                else {
                    if (s1[i-1] == s2[j-1]) {
                        rightMove = Move.MATCH;
                    }
                    else {
                        rightMove = Move.MIS;
                    }
                    rightMax = upperBias;
                }

                trackRightMatrix[i-1][j-1] = rightMove;
                rightMatrix[i][j] = rightMax;

                int lowerDown = downMatrix[i-1][j] - ε;
                int lowerBias = biasMatrix[i-1][j] - σ;

                Move downMove;
                int downMax;
                if (lowerDown >= lowerBias) {
                    downMove = Move.DOWN;
                    downMax = lowerDown;
                }
                else {
                    if (s1[i-1] == s2[j-1]) {
                        downMove = Move.MATCH;
                    }
                    else {
                        downMove = Move.MIS;
                    }
                    downMax = lowerBias;
                }

                trackDownMatrix[i-1][j-1] = downMove;
                downMatrix[i][j] = downMax;

                int scope = getScore(s1[i-1], s2[j-1]);
                int middleDown = downMatrix[i][j];
                int middleRight = rightMatrix[i][j];
                int middleBias = biasMatrix[i-1][j-1] + scope;

                Move move;
                int max;
                if (middleDown > middleRight) {
                    move = Move.DOWN;
                    max = middleDown;
                }
                else {
                    move = Move.RIGHT;
                    max = middleRight;
                }

                if (middleBias > max) {
                    if (s1[i-1] == s2[j-1]) {
                        move = Move.MATCH;
                    }
                    else {
                        move = Move.MIS;
                    }
                    max = middleBias;
                }

//                Dump.println("[" + i + ":" + j +"][" + s1[i-1] + ":" + s2[j-1] + "] " + scope);
//                Dump.print("D:" + middleDown + ", ");
//                Dump.print("R:" + middleRight + ", ");
//                Dump.print("B:" + middleBias + ", ");
//                Dump.println("max: " + max + ", " + move);
//                Dump.println("---");

                biasMatrix[i][j] = max;
                trackBiasMatrix[i-1][j-1] = move;

            }

        }

    }

    public void track(int i, int j, Move[][] trackMatrix) {

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
            trackMatrix = trackDownMatrix;
        }
        else if (trackMatrix[i][j].equals(Move.RIGHT)) {
            trackMatrix = trackRightMatrix;
        }
        else {
            trackMatrix = trackBiasMatrix;
        }

        if (trackMatrix[i][j].equals(Move.DOWN)) {
            track(i - 1, j, trackDownMatrix);
            result2.add('-');
            result1.add(str1.charAt(i));
        }
        else if (trackMatrix[i][j].equals(Move.RIGHT)) {
            track(i, j-1, trackRightMatrix);
            result2.add(str2.charAt(j));
            result1.add('-');
        }
        else {
            track(i-1, j-1, trackBiasMatrix);
            result1.add(str1.charAt(i));
            result2.add(str2.charAt(j));
        }
    }

    public Integer[][] getDownMatrix() {
        return downMatrix;
    }

    public Integer[][] getRightMatrix() {
        return rightMatrix;
    }

    public Integer[][] getBiasMatrix() {
        return biasMatrix;
    }

    public Move[][] getTrackDownMatrix() {
        return trackDownMatrix;
    }

    public Move[][] getTrackRightMatrix() {
        return trackRightMatrix;
    }

    public Move[][] getTrackBiasMatrix() {
        return trackBiasMatrix;
    }

    public String biasTrackToString() {

        StringBuilder result = new StringBuilder();

        result.append(" x ");
        for (Character x : str2.toCharArray()) {
            result.append(x).append(" ");
        }
        result.append("\n");

        int i = 0;
        for (Move[] moveVector : trackBiasMatrix) {

            result.append(str1.charAt(i)).append("[ ");
            int j = 0;
            for (Move move : moveVector) {

                 result.append(move).append(" ");

                j++;
            }
            result.append("]\n");
            i++;

        }
        return result.toString();

    }

}
