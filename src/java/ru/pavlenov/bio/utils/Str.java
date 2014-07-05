package ru.pavlenov.bio.utils;

/**
 * ⓭ + 44
 * Какой сам? by Pavlenov Semen 01.07.14.
 */
public class Str {

    public static String prefix(String kmer, int k) {
        return kmer.substring(0, k);
    }

    public static String suffix(String kmer, int k) {
        return kmer.substring(kmer.length()-k, kmer.length());
    }

}
