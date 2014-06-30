package ru.pavlenov.bio.genome;

import java.util.HashMap;

/**
 * Какой сам ☭
 * User: Павленов Семён
 * Date: 09.11.13 13:17
 */
public class Nucleotide {

    public static Character A = 'A';
    public static Character T = 'T';
    public static Character U = 'U';
    public static Character C = 'C';
    public static Character G = 'G';

    private static final HashMap<Character, Character> mapDNA;

    static {
        mapDNA = new HashMap<>();
        mapDNA.put(A, T);
        mapDNA.put(T, A);
        mapDNA.put(C, G);
        mapDNA.put(G, C);
    }

    private static final HashMap<Character, Character> mapRNA;

    static {
        mapRNA = new HashMap<>();
        mapRNA.put(A, U);
        mapRNA.put(U, A);
        mapRNA.put(C, G);
        mapRNA.put(G, C);
    }

    public static Character getA() {
    return A;
    }

    public static Character getT() {
    return T;
    }

    public static Character getU() {
        return U;
    }

    public static Character getC() {
    return C;
    }

    public static Character getG() {
    return G;
    }

    public static Character getDNAComplement(Character nucleotide) {
        return mapDNA.get(nucleotide);
    }

    public static Character getRNAComplement(Character nucleotide) {
        return mapRNA.get(nucleotide);
    }

    public static HashMap<Character, Character> getMapDNA() {
        return mapDNA;
    }

    public static HashMap<Character, Character> getMapRNA() {
        return mapRNA;
    }

    public static Character[] asDNAArray() {
        return new Character[]{A, C, G, T};
    }

    public static Character[] asRNAArray() {
        return  new Character[]{A, C, G, U};
    }

}
