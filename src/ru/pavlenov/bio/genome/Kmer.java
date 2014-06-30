package ru.pavlenov.bio.genome;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import ru.pavlenov.bio.utils.Dump;
import ru.pavlenov.bio.utils.StringArray;

import java.util.*;

/**
 * Какой сам ⚝
 * Author: Pavlenov Semen
 * Date: 15.11.13
 * Time: 12:21
 * <p>
 * E = mc^2
 */
public class Kmer {

    private String kmer;

    public Kmer(String kmer) {
        this.kmer = kmer.trim().toUpperCase();
    }

    public String get() {
        return kmer;
    }

    public void set(String kmer) {
        this.kmer = kmer;
    }

    public String getReverse() {
        return getReverse(kmer);
    }

    public boolean isMismatch(String kmer, int maxMismatch) {
        return isMismatch(this.kmer, kmer, maxMismatch);
    }

    /**
     * Создаёт обратно-комплементарную последовательность
     *
     * @param kmer
     * @return
     */
    public static String getReverse(String kmer) {
        String reverse = "";
        for (int i = 0; i < kmer.length(); i++) {
            reverse = Nucleotide.getDNAComplement(kmer.charAt(i)).toString().concat(reverse);
        }
        return reverse;
    }

    /**
     * Проверяет что-бы различия были не более чем в maxMismatch позициях
     *
     *
     * @param kmer1
     * @param kmer2
     * @param maxMismatch
     * @return
     */
    public static boolean isMismatch(String kmer1, String kmer2, int maxMismatch) {
        boolean result = true;
        for (int i = 0; i < kmer1.length(); i++) {
            if (kmer1.charAt(i) != kmer2.charAt(i)) {
                maxMismatch--;
            }
            if (maxMismatch < 0) {
                result = false;
                break;
            }
        }
        return result;
    }

    public static int calcMismatch(String kmer1, String kmer2) {
        int result = 0;
        for (int i = 0; i < kmer1.length(); i++) {
            if (kmer1.charAt(i) != kmer2.charAt(i)) {
                result++;
            }
        }
        return result;
    }

    /**
     * Генерит все возможные последовательности длины равной kmerLen
     *
     * @param kmerLen
     * @return
     */
    public static String[] genAll(int kmerLen) {
        Object[] matrix = createKmerArray(kmerLen, null);
        Dump.println("Tensor created!");
        StringArray result = expandKmerArray(matrix, new StringArray((int) Math.pow(4, kmerLen)));
        Dump.println("Expand created!");
        HashSet<String> hs = new HashSet<String>(Arrays.asList(result.asArray()));
        String[] unique = hs.toArray(new String[hs.size()]);
        Arrays.sort(unique, String::compareTo);
        return unique;
    }

    /**
     * Строит k-мерный массив
     *
     * @param kmerLen
     * @param array
     * @return
     */
    private static Object[] createKmerArray(int kmerLen, Object[] array) {

        kmerLen--;
        if (array == null) {
            array = new Object[]{Nucleotide.getA(), Nucleotide.getT(), Nucleotide.getC(), Nucleotide.getG()};
        }

        if (kmerLen > 0) {
            for (int i = 0; i < 4; i++) {
                Object iValue = array[i];
                Object[] iArray = new Object[4];
                iArray[0] = iValue + Nucleotide.getA().toString();
                iArray[1] = iValue + Nucleotide.getT().toString();
                iArray[2] = iValue + Nucleotide.getC().toString();
                iArray[3] = iValue + Nucleotide.getG().toString();
                array[i] = iArray;
                if (kmerLen > 1) {
                    array[i] = createKmerArray(kmerLen, (Object[]) array[i]);
                }
            }
        }

        return array;
    }

    /**
     * Разворачиваем k-мерный массив в одномерный
     *
     * @param kmerArray
     * @param array
     * @return
     */
    private static StringArray expandKmerArray(Object[] kmerArray, StringArray array) {

        for ( Object el: kmerArray ) {
            if (el instanceof Object[]) {
                expandKmerArray((Object[]) el, array);
            }
            else {
                array.add((String) el);
            }
        }

        return array;
    }

    public static String[] genMismatch(String kmer, int mismatch) {

        Integer[][] mask = createMismatchMask(kmer.length(), mismatch, null);

//        System.out.println("Mask created: " + mask.length);

        int size = (int) Math.pow(4, mismatch);

        StringArray result = new StringArray((size * mask.length) - mask.length + 1, kmer);
        for (Integer[] vector : mask) {
            genMismatchByMask(vector, new StringArray(size, kmer), result);
        }

        return result.asArray();
    }

    private static StringArray genMismatchByMask(Integer[] vector, StringArray start, StringArray result) {

        String[] start0 = start.clone();
        Character[] nucleotideList = Nucleotide.asDNAArray();
        Integer pos = vector[0];

        for (String kmer : start0) {

            if (kmer == null) {
                continue;
            }

            Character n0 = kmer.charAt(pos);

            for (Character n : nucleotideList) {
                if (n0 != n) {
                    start.add(kmer.substring(0, pos) + n + kmer.substring(pos + 1));
                    result.add(kmer.substring(0, pos) + n + kmer.substring(pos + 1));
                }
            }
        }

        Integer[] vector1 = Arrays.copyOfRange(vector, 1, vector.length);

        if (vector1.length > 0) {
            start = genMismatchByMask(vector1, start, result);
        }

        return start;
    }

    /**
     * Создаёт матрицу всех возможных позиций замен для kmer длиной kmerLen
     *
     * @param kmerLen
     * @param maxMismatch
     * @param matrix
     * @return
     */
    public static Integer[][] createMismatchMask(int kmerLen, int maxMismatch, Integer[][] matrix) {

        if (matrix == null) {
            matrix = new Integer[0][maxMismatch];
            for (int i = 0; i < kmerLen; i++) {
                matrix = (Integer[][]) ArrayUtils.add(matrix, new Integer[]{i});
            }
        }
        else {
            Integer[][] matrix0 = new Integer[0][maxMismatch];
            for (Integer[] vector : matrix) {
                for (int i = 0; i < kmerLen; i++) {
                    if (Arrays.binarySearch(vector, i) == -1) {
                        Integer[] vector0 = vector.clone();
                        vector0 = (Integer[]) ArrayUtils.add(vector0, i);
                        Arrays.sort(vector0);
                        matrix0 = (Integer[][]) ArrayUtils.add(matrix0, vector0);
                    }
                }
            }
            matrix = matrix0;
        }

        maxMismatch -= 1;
        if (maxMismatch > 0) {
            matrix = createMismatchMask(kmerLen, maxMismatch, matrix);
        }

        return matrix;
    }

    public static String[] genAllv2(int kmerLen) {

        String[] result = new String[(int) Math.pow(4, kmerLen) - 1];

        result = (String[]) ArrayUtils.add(result, StringUtils.repeat("0", kmerLen));

        String maskStart = StringUtils.repeat("3", kmerLen - 1);
        String maskEnd = StringUtils.repeat("3", kmerLen);

        Integer start = Integer.parseInt(maskStart, 4) + 1;
        Integer end = Integer.parseInt(maskEnd, 4);

        System.out.println(Integer.toString(start, 4) + " | " + Integer.toString(end, 4) +  " | " + (end - start));

        int index = 0;

        for (Integer i = start; i <= end; i++) {

            String kmer = Integer.toString(i, 4);
            result[index] = kmer;
            index++;

            if (StringUtils.endsWith(kmer, "0")) {
                result[index] = StringUtils.reverse(kmer);
                index++;
            }

        }

        HashSet<String> hs = new HashSet<>(Arrays.asList(result));
        result = hs.toArray(new String[hs.size()]);
//        Arrays.sort(result, String::replace);

        return result;

    }

    public static HashMap<String, ArrayList<Integer>> findAll(String genome, int k) {

        int genomeLen = genome.length();
        HashMap<String, ArrayList<Integer>> kmers = new HashMap<>();
        for (int i = 0; i < genomeLen - k + 1; i++) {
            String kmer = genome.substring(i, i + k);
            ArrayList<Integer> posMap = new ArrayList<>();
            if (kmers.containsKey(kmer)) {
                posMap = kmers.get(kmer);
            }
            posMap.add(i);
            kmers.put(genome.substring(i, i + k), posMap);
        }
        return kmers;
    }

}
