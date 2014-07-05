package ru.pavlenov.bio.amino;

import org.apache.commons.lang.StringUtils;
import ru.pavlenov.bio.utils.Dump;

/**
 * Какой сам ☭
 * User: Павленов Семён
 * Date: 05.01.14 16:57
 */
public class LinearSpaceAlignment {

    String str1;
    String str2;
    Integer σ;

    private Move[][] trackMatrix;

    public class Node {

        private int i;
        private int j;
        private Move moveFrom;
        private Move moveTo;

        public Node(int i, int j, Move moveFrom, Move moveTo) {
            this.i = i;
            this.j = j;
            this.moveFrom = moveFrom;
            this.moveTo = moveTo;
        }

        public int getI() {
            return i;
        }

        public int getJ() {
            return j;
        }

        public Move getMoveTo() {
            return moveTo;
        }

        public String toString() {
            return moveFrom + "[" + i + ":" + j + "] " + moveTo;
        }

        public Move getMoveFrom() {
            return moveFrom;
        }
    }

    public LinearSpaceAlignment(String str1, String str2) {
        this.str1 = str1;
        this.str2 = str2;
        trackMatrix = new Move[str1.length()][str2.length()];
    }

    public void setPenalty(int σ) {
        this.σ = σ;
    }

    public void run(int top, int bottom, int left, int right) throws InvalidAlphabetException {

        Dump.println("===");
        Dump.println(top + ", " + bottom + ", " + left + ", " + right);

        if (left >= right) return;

        Node middleNode = getMiddleNode(top, bottom, left, right);
        int mI = middleNode.getI();
        int mJ = middleNode.getJ();
        Move moveTo = middleNode.getMoveTo();

        Dump.println(middleNode);

        trackMatrix[mI][mJ] = middleNode.getMoveFrom();
        if (moveTo == Move.RIGHT || moveTo == Move.MIS || moveTo == Move.MATCH) {
            mJ += 1;
        }
        if (moveTo == Move.DOWN || moveTo == Move.MIS || moveTo == Move.MATCH) {
            mI += 1;
        }
        trackMatrix[mI][mJ] = moveTo;

        run(mI, bottom, mJ, right);


    }


    public Node getMiddleNode(int top, int bottom, int left, int right) throws InvalidAlphabetException {

        int middle = ((left + right) / 2) + 1;

        int len1 = bottom - top + 1;
        int len2 = right - left + 1;

        Dump.println(len1);
        Dump.println(len2);
        Dump.println(middle);

        String str1_1 = str1.substring(top, bottom+1);
        String str2_1 = str2.substring(left, middle);
        String str2_2 = str2.substring(middle, right+1);

        Dump.println(str1_1);
        Dump.println(str2_1 + " | " + str2_2);

        GlobalAlignment alignment1 = new GlobalAlignment(str1_1, str2_1);
        alignment1.setScoringMatrix(GlobalAlignment.ScoryngMatrixType.BLOSUM62).setPenalty(this.σ);
        alignment1.make();

        GlobalAlignment alignment2 = new GlobalAlignment(StringUtils.reverse(str1_1), StringUtils.reverse(str2_2));
        alignment2.setScoringMatrix(GlobalAlignment.ScoryngMatrixType.BLOSUM62).setPenalty(this.σ);
        alignment2.make();

        Integer[][] m1 = alignment1.getWeightMatrix();
        Integer[][] m2 = alignment2.getWeightMatrix();
        Move[][] t1 = alignment1.getTrackMatrix();
        int m1Len = m1[0].length;
        int m2Len = m2[0].length;

//        Dump.println(m1);
//        Dump.println("--");
//        Dump.println(m2);
//        Dump.println("--");

        int max = -1000000;
        int mJ = middle;
        int mI = 0;
        Move moveTo;
        Move moveFrom;
        for (int i = 0; i <= len1; i++ ) {
            int v1 = m1[i][m1Len-1];
            int v2 = m2[len1-i][m2Len-1];
            int s = v1 + v2;
//            Dump.println(i + " > " + v1 + " + " + v2 + " = " + s);
            if (max < s) {
                max = s;
                mI = i;
            }
        }

        moveFrom = t1[mI-1][mJ-1];

        int d = m1[mI+1][m1Len-1] + m2[len1-mI-1][m2Len-1];
        int r = m2[len1-mI][m2Len-2];
        int b = m2[len1-mI-1][m2Len-2];

        int m;
        if (d > r) {
            moveTo = Move.DOWN;
            m = d;
        }
        else {
            moveTo = Move.RIGHT;
            m = r;
        }
        if (b > m) {
            moveTo = Move.MIS;
        }

        return new Node(mI+top-1, mJ-1, moveFrom, moveTo);
    }

    public String trackToString() {

        StringBuilder result = new StringBuilder();

        result.append(" x ");
        for (Character x : str2.toCharArray()) {
            result.append(x).append(" ");
        }
        result.append("\n");

        int i = 0;
        for (Move[] moveVector : trackMatrix) {

            result.append(str1.charAt(i)).append("[ ");
            int j = 0;
            for (Move move : moveVector) {

                if (move == null) move = Move.NULL;
                result.append(move).append(" ");

                j++;
            }
            result.append("]\n");
            i++;

        }
        return result.toString();

    }


}
