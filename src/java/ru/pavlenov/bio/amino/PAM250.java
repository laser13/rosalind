package ru.pavlenov.bio.amino;

/**
 * Какой сам ☭
 * User: Павленов Семён
 * Date: 21.12.13 20:07
 */

final public class PAM250{

    /*
     * Array representation of Blosum-62 matrix
     * Refer to above matrix for corrseponding amino acids
     * i.e. score(A, R) corresponds to  matrix[0][1]=matrix[1][0]=-1
    */
    private static final int[][] matrix = {
               /*A   B   C   D   E   F   G   H   I   J   K   L   M   N   O   P   Q   R   S   T   U   V   W   X   Y   Z  * */
        /*A*/ {  2,  0, -2,  0,  0, -3,  1, -1, -1, -8, -1, -2, -1,  0, -8,  1,  0, -2,  1,  1, -8,  0, -6,  0, -3,  0, -8 },
        /*B*/ {  0,  3, -4,  3,  3, -4,  0,  1, -2, -8,  1, -3, -2,  2, -8, -1,  1, -1,  0,  0, -8, -2, -5, -1, -3,  2, -8 },
        /*C*/ { -2, -4, 12, -5, -5, -4, -3, -3, -2, -8, -5, -6, -5, -4, -8, -3, -5, -4,  0, -2, -8, -2, -8, -3,  0, -5, -8 },
        /*D*/ {  0,  3, -5,  4,  3, -6,  1,  1, -2, -8,  0, -4, -3,  2, -8, -1,  2, -1,  0,  0, -8, -2, -7, -1, -4,  3, -8 },
        /*E*/ {  0,  3, -5,  3,  4, -5,  0,  1, -2, -8,  0, -3, -2,  1, -8, -1,  2, -1,  0,  0, -8, -2, -7, -1, -4,  3, -8 },
        /*F*/ { -3, -4, -4, -6, -5,  9, -5, -2,  1, -8, -5,  2,  0, -3, -8, -5, -5, -4, -3, -3, -8, -1,  0, -2,  7, -5, -8 },
        /*G*/ {  1,  0, -3,  1,  0, -5,  5, -2, -3, -8, -2, -4, -3,  0, -8,  0, -1, -3,  1,  0, -8, -1, -7, -1, -5,  0, -8 },
        /*H*/ { -1,  1, -3,  1,  1, -2, -2,  6, -2, -8,  0, -2, -2,  2, -8,  0,  3,  2, -1, -1, -8, -2, -3, -1,  0,  2, -8 },
        /*I*/ { -1, -2, -2, -2, -2,  1, -3, -2,  5, -8, -2,  2,  2, -2, -8, -2, -2, -2, -1,  0, -8,  4, -5, -1, -1, -2, -8 },
        /*J*/ { -8, -8, -8, -8, -8, -8, -8, -8, -8,  1, -8, -8, -8, -8, -8, -8, -8, -8, -8, -8, -8, -8, -8, -8, -8, -8, -8 },
        /*K*/ { -1,  1, -5,  0,  0, -5, -2,  0, -2, -8,  5, -3,  0,  1, -8, -1,  1,  3,  0,  0, -8, -2, -3, -1, -4,  0, -8 },
        /*L*/ { -2, -3, -6, -4, -3,  2, -4, -2,  2, -8, -3,  6,  4, -3, -8, -3, -2, -3, -3, -2, -8,  2, -2, -1, -1, -3, -8 },
        /*M*/ { -1, -2, -5, -3, -2,  0, -3, -2,  2, -8,  0,  4,  6, -2, -8, -2, -1,  0, -2, -1, -8,  2, -4, -1, -2, -2, -8 },
        /*N*/ {  0,  2, -4,  2,  1, -3,  0,  2, -2, -8,  1, -3, -2,  2, -8,  0,  1,  0,  1,  0, -8, -2, -4,  0, -2,  1, -8 },
        /*O*/ { -8, -8, -8, -8, -8, -8, -8, -8, -8, -8, -8, -8, -8, -8,  1, -8, -8, -8, -8, -8, -8, -8, -8, -8, -8, -8, -8 },
        /*P*/ {  1, -1, -3, -1, -1, -5,  0,  0, -2, -8, -1, -3, -2,  0, -8,  6,  0,  0,  1,  0, -8, -1, -6, -1, -5,  0, -8 },
        /*Q*/ {  0,  1, -5,  2,  2, -5, -1,  3, -2, -8,  1, -2, -1,  1, -8,  0,  4,  1, -1, -1, -8, -2, -5, -1, -4,  3, -8 },
        /*R*/ { -2, -1, -4, -1, -1, -4, -3,  2, -2, -8,  3, -3,  0,  0, -8,  0,  1,  6,  0, -1, -8, -2,  2, -1, -4,  0, -8 },
        /*S*/ {  1,  0,  0,  0,  0, -3,  1, -1, -1, -8,  0, -3, -2,  1, -8,  1, -1,  0,  2,  1, -8, -1, -2,  0, -3,  0, -8 },
        /*T*/ {  1,  0, -2,  0,  0, -3,  0, -1,  0, -8,  0, -2, -1,  0, -8,  0, -1, -1,  1,  3, -8,  0, -5,  0, -3, -1, -8 },
        /*U*/ { -8, -8, -8, -8, -8, -8, -8, -8, -8, -8, -8, -8, -8, -8, -8, -8, -8, -8, -8, -8,  1, -8, -8, -8, -8, -8, -8 },
        /*V*/ {  0, -2, -2, -2, -2, -1, -1, -2,  4, -8, -2,  2,  2, -2, -8, -1, -2, -2, -1,  0, -8,  4, -6, -1, -2, -2, -8 },
        /*W*/ { -6, -5, -8, -7, -7,  0, -7, -3, -5, -8, -3, -2, -4, -4, -8, -6, -5,  2, -2, -5, -8, -6, 17, -4,  0, -6, -8 },
        /*X*/ {  0, -1, -3, -1, -1, -2, -1, -1, -1, -8, -1, -1, -1,  0, -8, -1, -1, -1,  0,  0, -8, -1, -4, -1, -2, -1, -8 },
        /*Y*/ { -3, -3,  0, -4, -4,  7, -5,  0, -1, -8, -4, -1, -2, -2, -8, -5, -4, -4, -3, -3, -8, -2,  0, -2, 10, -4, -8 },
        /*Z*/ {  0,  2, -5,  3,  3, -5,  0,  2, -2, -8,  0, -3, -2,  1, -8,  0,  3,  0,  0, -1, -8, -2, -6, -1, -4,  3, -8 },
        /***/ { -8, -8, -8, -8, -8, -8, -8, -8, -8, -8, -8, -8, -8, -8, -8, -8, -8, -8, -8, -8, -8, -8, -8, -8, -8, -8,  1 },
    };

    // quick and dirty equivalent of typesafe enum pattern, can also use HashMap
    // or even better, EnumMap in Java 5.
    // This code is for Java 1.4.2, so we will stick to the simple implementation
    private static int getIndex(char a) throws InvalidAlphabetException {
        // check for upper and lowercase characters
        switch ((String.valueOf(a)).toUpperCase().charAt(0)) {
            case 'A': return 0;
            case 'B': return 1;
            case 'C': return 2;
            case 'D': return 3;
            case 'E': return 4;
            case 'F': return 5;
            case 'G': return 6;
            case 'H': return 7;
            case 'I': return 8;
            case 'J': return 9;
            case 'K': return 10;
            case 'L': return 11;
            case 'M': return 12;
            case 'N': return 13;
            case 'O': return 14;
            case 'P': return 15;
            case 'Q': return 16;
            case 'R': return 17;
            case 'S': return 18;
            case 'T': return 19;
            case 'U': return 20;
            case 'V': return 21;
            case 'W': return 22;
            case 'X': return 23;
            case 'Y': return 24;
            case 'Z': return 25;
            case '*': return 26;
            default: throw new InvalidAlphabetException("Invalid amino acid character " + a);
        }
    }

    private static int getDistance(int i, int j) throws InvalidAlphabetException {
        if (i < 0 || i > matrix[0].length) {
            throw new InvalidAlphabetException("Invalid amino acid character at string1, position " + i);
        }
        if (j < 0 || j > matrix[0].length) {
            throw new InvalidAlphabetException("Invalid amino acid character at string2, position " + j);
        }

        return matrix[i][j];
    }

    public static int getDistance(char a1, char a2) throws InvalidAlphabetException {
        // toUpper
        return getDistance(getIndex(a1), getIndex(a2));
    }
}
