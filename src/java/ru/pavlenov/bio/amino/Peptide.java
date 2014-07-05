package ru.pavlenov.bio.amino;

import org.apache.commons.lang.ArrayUtils;
import ru.pavlenov.bio.utils.Dump;
import ru.pavlenov.bio.utils.IntegerArray;
import ru.pavlenov.bio.utils.StringArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Какой сам ☭
 * User: Павленов Семён
 * Date: 16.11.13 18:20
 */
public class Peptide {

    private String text;
    private String rnaText;
    private char[] acids;
    private Integer mass;
    private HashMap<Character, String> localMap;
    private static HashMap<Character,Codon.Acid> hmA2N = Codon.getHmA2P();
    private static HashMap<Integer, Character> hmM2A = Codon.getHmM2A();

    public Peptide() {
        this.text = "";
        this.acids = text.toCharArray();
        this.localMap = null;
    }

    public Peptide(String text) {
        this.text = text;
        this.acids = text.toCharArray();
        this.localMap = null;
    }

    public Peptide(ArrayList<Integer> mass) {

        String text = "";
        for (Integer m : mass) {
            text += hmM2A.get(m);
        }

        this.text = text;
        this.acids = text.toCharArray();
        this.localMap = null;
    }

    public Peptide(String text, String rnaText) {
        this.text = text;
        this.rnaText = rnaText;
        this.acids = text.toCharArray();
        createLocalMap(rnaText);
    }

    public void createLocalMap(String rnaText) {

        localMap = new HashMap<>();
        for (int i = 0; i < acids.length; i++) {
            localMap.put(acids[i], rnaText.substring(i * 3, i * 3 + 3));
        }
    }

    public String get() {
        return text;
    }

    public String getRnaText() {
        return rnaText;
    }

    public RNA getRna() {
        if (rnaText == null) return null;
        return new RNA(rnaText);
    }

    public Long howMany(int mod) {

        Long result = 3L;
        for (char a : acids) {
            result *= hmA2N.get(a).getTriplet().length;
            if (result > mod) result %= mod;
        }

        return result;
    }

    public Long howMany() {

        Long result = 3L;
        for (char a : acids) {
            result *= hmA2N.get(a).getTriplet().length;
        }
        return result;
    }

    public int getMass() {
        return getMass(acids);
    }

    public double getTrueMass() {
        return getTrueMass(acids);
    }

    public static int getMass(String peptideText) {
        return getMass(peptideText.toCharArray());
    }

    public static int getMass(char[] peptide) {
        int mass = 0;
        for (char c : peptide) {
            mass += hmA2N.get(c).getMass();
        }
        return mass;
    }

    public static double getTrueMass(String peptideText) {
        return getTrueMass(peptideText.toCharArray());
    }

    public static double getTrueMass(char[] peptide) {
        double mass = 0;
        for (char c : peptide) {
            mass += hmA2N.get(c).getTrueMass();
        }
        return mass;
    }

    public String[] getCodon(int index) {
        return hmA2N.get(acids[index]).getTriplet();
    }

    public String toShortString() {
        String result = "";
        for (char c : acids) {
            result += hmA2N.get(c).getShortName();
        }
        return result;
    }

    public String toString() {
        return text;
    }

    public String toMassString() {
        String mass = "";
        for (char c : acids) {
            mass = mass + hmA2N.get(c).getMass() + '-';
        }
        return mass.substring(0, mass.length() - 1);
    }

    /**
     * Ищет в строке белка, подстроку subPeptideText
     *
     * @param subPeptideText
     * @return
     */
    public ArrayList<Peptide> getArea(String subPeptideText) {

        int startIndex = 0;
        ArrayList<Peptide> result = new ArrayList<>();
        while (startIndex != -1) {
            startIndex = text.indexOf(subPeptideText, startIndex);
            if (startIndex != -1) {
                int endIndex = startIndex + subPeptideText.length();
                if (rnaText == null) {
                    result.add(new Peptide(text.substring(startIndex, endIndex)));
                }
                else {
                    result.add(new Peptide(text.substring(startIndex, endIndex), rnaText.substring(startIndex * 3, endIndex * 3)));
                }
                startIndex += subPeptideText.length();
            }
        }
        return result;
    }

    public Integer[] cyclospectrum() {

        return cyclospectrum(text);

    }

    public static Integer[] cyclospectrum(String text) {

        int len = text.length();

        StringArray sub = new StringArray(len * (len - 1) + 1, text);

        for (int i = 1; i < len; i++) {
            int rs = i - 1;
            String text0 = text + text.substring(0, rs);
            for (int j = 0; j <= text0.length() - i; j++) {
                sub.add(text0.substring(j, j + i));
            }
        }

        IntegerArray result = new IntegerArray(sub.size());
        for (String a: sub.asArray()) {
            result.add(Peptide.getMass(a));
        }

        Integer[] r = result.asArray();
        Arrays.sort(r);

        return r;

    }

    public static Integer[] cyclospectrum(Integer[] mass) {

        int len = mass.length;

//        System.out.print("mass: " + Arrays.asList(mass));

        ArrayList<Integer[]> sub = new ArrayList<>();
        sub.add(mass);

        for (int i = 1; i < len; i++) {
            int rs = i - 1;
            Integer[] tail = Arrays.copyOfRange(mass, 0, rs);
            Integer[] mass0 = (Integer[]) ArrayUtils.addAll(mass, tail);
            for (int j = 0; j <= mass0.length - i; j++) {
                sub.add(Arrays.copyOfRange(mass0, j, j + i));
            }
        }

        IntegerArray result = new IntegerArray(sub.size());
        for (Integer[] a: sub) {
            Integer sum = 0;
            for (Integer i : a) {
                sum += i;
            }
            result.add(sum);
        }

        Integer[] r = result.asArray();
        Arrays.sort(r);

        return r;
    }

    public Integer scope(Integer[] experimental) {
        return scope(cyclospectrum(), experimental);
    }

    /**
     * Вычисляет разницу между теоритическими и экперементальными спектрами
     *
     * @param theoretical
     * @param experimental
     * @return
     */
    public static Integer scope(Integer[] theoretical, Integer[] experimental) {

        int exists = 0;
        int j = -1;
        int j0 = 0;
        for (Integer t : theoretical) {

            j0 = Arrays.binarySearch(experimental, j+1, experimental.length, t);

            if (j0 >= 0) {
                j = j0;
                exists++;
            }

            if (j+1 > experimental.length) {
                break;
            }
        }
        return exists;
    }

    public static boolean compare(Integer[] theoretical, Integer[] experimental) {

        boolean ok = true;
        int j = 0;

        for (Integer aTheoretical : theoretical) {
            j = Arrays.binarySearch(experimental, j + 1, experimental.length, aTheoretical);
            if (j < 0) {
                ok = false;
                break;
            }
        }
        return ok;
    }

    public static String mass2text(Integer[] mass) {
        String text = "";
        for (int m : mass) {
            text += hmM2A.get(m);
        }
        return text;
    }

}
