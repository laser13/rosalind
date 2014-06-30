package ru.pavlenov.bio.assemble;

import ru.pavlenov.bio.genome.Kmer;
import ru.pavlenov.bio.utils.Dump;

import java.util.*;

/**
 * Какой сам ☭
 * User: Павленов Семён
 * Date: 30.11.13 8:54
 */
public class Base {

    public static String[] composition(String text, int kmerLen) {

        String[] result = new String[text.length() - kmerLen + 1];
        for (int i = 0; i < text.length() - kmerLen + 1; i++) {
            result[i] = text.substring(i, i + kmerLen);
        }

        Arrays.sort(result);

        return result;

    }

    /**
     * Пытаемся сопоставить кусочки ДНК (read) различной длины и перехлёстов
     * @param reads
     */
    public static String getShortestSuperstring(ArrayList<String> reads, int minLenOverlap) {

        // Говорим что суперстрока сейчас равна первому риду
        String superstring = reads.get(0);
        reads.remove(0);

        while (!reads.isEmpty()) {

            ArrayList<String> removes = new ArrayList<>();
            for (int i = 0; i < reads.size(); i++) {

                String read = reads.get(i);

                // Если рид уже есть в суперстроке, то идём дальше
                if (superstring.contains(read)) {
                    removes.add(read);
                    continue;
                }

                String maxOverlap = findMaxOverlap(superstring, read, minLenOverlap);

                // Если перехлёст не пустой, то добавим остаточек в суперстроку
                if (!maxOverlap.isEmpty()) {

                    removes.add(read);

                    int index = superstring.indexOf(maxOverlap);

                    if (index == 0) {
                        superstring = read.substring(0, read.indexOf(maxOverlap)) + superstring;
                    } else {
                        superstring += read.substring(maxOverlap.length(), read.length());
                    }
                }
            }

            if (!removes.isEmpty()) {
                for (String remove : removes) {
                    reads.remove(remove);
                }
                removes.clear();
            }

        }

        return superstring;

    }

    public static String findMaxOverlap(String read1, String read2, int minLenOverlap) {

        int len1 = read1.length();
        int len2 = read2.length();
        int minLen = Math.min(len1, len2);

        String maxOverlap = "";

        for (int currLen = minLenOverlap; currLen < minLen; currLen++) {

            String suffix1 = read1.substring(len1-currLen, len1);
            String preffix1 = read1.substring(0, currLen);

            String suffix2 = read2.substring(len2-currLen, len2);
            String preffix2 = read2.substring(0, currLen);

            if (suffix1.equals(preffix2)) {
                maxOverlap = suffix1;
            }
            if (suffix2.equals(preffix1)) {
                maxOverlap = suffix2;
            }

        }

        return maxOverlap;
    }

    public static HashMap<String, ArrayList<String>> getOverlapGraph(String[] kmerList) {

        HashMap<String, ArrayList<String>> result = new HashMap<>();
        for (String kmer : kmerList) {

            ArrayList<String> list = new ArrayList<>();
            if (result.isEmpty()) {
                result.put(kmer, list);
                continue;
            }

            for (Map.Entry<String, ArrayList<String>> pair : result.entrySet()) {
                if (suffix(kmer).equals(prefix(pair.getKey()))) {
                    list.add(pair.getKey());
                }
                if (suffix(pair.getKey()).equals(prefix(kmer))) {
                    pair.getValue().add(kmer);
                }
            }
            result.put(kmer, list);
        }
        return result;

    }

    public static HashMap<String[], ArrayList<String[]>> getOverlapGraph(String[] read1, String[] read2) {

        HashMap<String[], ArrayList<String[]>> result = new HashMap<>();
        for (int i = 0; i < read1.length; i++) {

            String kmer1 = read1[i];
            String kmer2 = read2[i];
            String[] kmer = new String[]{kmer1, kmer2};
            ArrayList<String[]> list = new ArrayList<>();

            if (result.isEmpty()) {
                result.put(kmer, list);
                continue;
            }

            for (Map.Entry<String[], ArrayList<String[]>> pair : result.entrySet()) {
                if (suffix(kmer1).equals(prefix(pair.getKey()[0])) && suffix(kmer2).equals(prefix(pair.getKey()[1]))) {
                    list.add(pair.getKey());
                }
                if (suffix(pair.getKey()[0]).equals(prefix(kmer1)) && suffix(pair.getKey()[1]).equals(prefix(kmer2))) {
                    pair.getValue().add(kmer);
                }
            }
            result.put(kmer, list);
        }
        return result;

    }

    public static HashMap<String, ArrayList<String>> getDeBruijnGraph(String text, int kmerLen) {

        kmerLen -= 1;

        HashMap<String, ArrayList<String>> result = new HashMap<>();
        String prevKmer = null;
        for (int i = 0; i < text.length() - kmerLen + 1; i++) {

            String kmer = text.substring(i, i + kmerLen);

            ArrayList<String> list = new ArrayList<>();

            if (prevKmer == null) {
                result.put(kmer, list);
            }
            else {
                result.get(prevKmer).add(kmer);
                if (!result.containsKey(kmer)) {
                    result.put(kmer, list);
                }
            }

            prevKmer = kmer;

        }

        return result;

    }

    public static HashMap<String, ArrayList<String>> getDeBruijnGraph(String[] kmerList) {

        HashMap<String, ArrayList<String>> result = new HashMap<>();
        for (String kmer : kmerList) {
            String prefix = prefix(kmer);
            String suffix = suffix(kmer);
            if (result.containsKey(prefix)) {
//                if (!result.get(prefix).contains(suffix)) {
//                    result.get(prefix).add(suffix);
//                }
                result.get(prefix).add(suffix);
            }
            else {
                ArrayList<String> list = new ArrayList<>();
                list.add(suffix);
                result.put(prefix, list);
            }
        }
        return result;
    }

    public static String prefix(String kmer) {
        return kmer.substring(0, kmer.length()-1);
    }

    public static String suffix(String kmer) {
        return kmer.substring(1, kmer.length());
    }

}
