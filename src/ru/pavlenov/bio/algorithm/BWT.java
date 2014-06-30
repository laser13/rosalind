package ru.pavlenov.bio.algorithm;

import ru.pavlenov.bio.utils.Dump;

import java.util.*;

/**
 * Какой сам ⚝
 * Author: Pavlenov Semen
 * Date: 27.01.14
 * <p>
 * E = mc^2
 */
public class BWT {

    private int len;
    private byte[] firstColumn;
    private byte[] lastColumn;
    private int[] suffix;
    private int[] pos1;
    private int[] pos2;
    private byte[] bytes;
    private Integer[] map;
    private Map<Byte, List<Integer>> indexes = new HashMap<>();
    {
        indexes.put((byte) 0, new ArrayList<>());
        indexes.put((byte) 1, new ArrayList<>());
        indexes.put((byte) 2, new ArrayList<>());
        indexes.put((byte) 3, new ArrayList<>());
        indexes.put((byte) 4, new ArrayList<>());
    }

    public BWT() {}

    public BWT(String lastString) {
        len = lastString.length();
        lastColumn = lastString.getBytes();
        firstColumn = lastString.getBytes();
        Arrays.sort(firstColumn);
        mapping();
    }

    public static BWT getByText(String text) {

        BWT bwt = new BWT();
        return bwt;

    }

    public byte[] minim(String text) {

        byte[] result = new byte[text.length()];

        int counter = 0;
        for (char c : text.toCharArray()) {
            if (c == 'A') result[counter] = 1;
            else if (c == 'C') result[counter] = 2;
            else if (c == 'G') result[counter] = 3;
            else if (c == 'T') result[counter] = 4;
            else if (c == '$') result[counter] = 0;
            else result[counter] = (byte) c;
            counter++;
        }
        return result;
    }

    public void setText(String text) {

        len = text.length();

        firstColumn = new byte[len];
        lastColumn = new byte[len];
        suffix = new int[len];

        pos1 = new int[len];
        pos2 = new int[len];
        bytes = minim(text);

        for (int i = 0; i < len; i++) {
            pos1[i] = i;
            pos2[i] = i;
        }

        Dump.println("Arrays complite");

        Dump.print("First sorting");
        long sTime = System.nanoTime();
        qSort1(0, len-1);
        Dump.println(" complite: " + (System.nanoTime() - sTime) / 1_000_000f + " ms.");

        Dump.print("Second sorting");
        sTime = System.nanoTime();
        qSort2(0, len-1);
        Dump.println(" complite: " + (System.nanoTime() - sTime) / 1_000_000f + " ms.");

        int[] _pos1 = new int[len];
        int[] _pos2 = new int[len];

        for (int i = 0; i < len; i++) {
            _pos1[i] = pos1[len-i-1];
            _pos2[i] = pos2[len-i-1];
        }

        pos1 = _pos1;
        pos2 = _pos2;
        suffix = pos2;

        for (int i = 0; i < len; i++) {

            firstColumn[i] = bytes[pos1[i]];

            if (pos1[i] == 0) {
                lastColumn[i] = bytes[len-1];
            }
            else {
                lastColumn[i] = bytes[pos1[i]-1];
            }

            indexes.get(lastColumn[i]).add(i);

        }

        mapping();
    }

    public int[] getSuffix() {
        return suffix;
    }

    public byte[] getLastColumn() { return lastColumn; }

    public byte[] getFirstColumn() { return firstColumn; }

    public Integer[] getMap() { return map; }

    public void mapping() {

        map = new Integer[len];

        Dump.print("Mapping");
        long sTime = System.nanoTime();

        List<String> firstList = new ArrayList<>();
        List<String> lastList = new ArrayList<>();
        Map<Byte, Integer> counterMapFirst = new HashMap<>();
        counterMapFirst.put((byte) 0, 0);
        counterMapFirst.put((byte) 1, 0);
        counterMapFirst.put((byte) 2, 0);
        counterMapFirst.put((byte) 3, 0);
        counterMapFirst.put((byte) 4, 0);

        Map<Byte, Integer> counterMapLast = new HashMap<>();
        counterMapLast.put((byte) 0, 0);
        counterMapLast.put((byte) 1, 0);
        counterMapLast.put((byte) 2, 0);
        counterMapLast.put((byte) 3, 0);
        counterMapLast.put((byte) 4, 0);

        int fCount;
        int lCount;

        for (int i = 0; i < len; i++) {

            byte fc = firstColumn[i];
            fCount = counterMapFirst.get(fc) + 1;
            counterMapFirst.put(fc, fCount);
            firstList.add("" + fc + fCount);

            byte lc = lastColumn[i];
            lCount = counterMapLast.get(lc) + 1;
            counterMapLast.put(lc, lCount);
            lastList.add("" + lc + lCount);

        }

        for (int i = 0; i < len; i++) {
            map[i] = firstList.indexOf(lastList.get(i));
        }
        Dump.println(" complite: " + (System.nanoTime() - sTime) / 1_000_000f + " ms.");

    }

    public int[] matching(String pattern) {

        int top = 0;
        int bottom = lastColumn.length - 1;

        while (top <= bottom) {

            if (!pattern.isEmpty()) {

                char symbol = pattern.charAt(pattern.length()-1);
                pattern = pattern.substring(0, pattern.length()-1);

                Integer topIndex = null;
                Integer bottomIndex = null;
                for (int i = top; i <= bottom; i++) {
                    if (lastColumn[i] == symbol) {
                        if (topIndex == null) topIndex = i;
                        bottomIndex = i;
                    }
                }

                if (topIndex != null) {
                    top = map[topIndex];
                    bottom = map[bottomIndex];
                }
                else {
                    return null;
                }

            }
            else {
                return new int[]{top, bottom};
            }

        }

        return null;

    }

    public Integer[][] matching(String pattern, int k) {

        int pLen = pattern.length() - 1;
        byte[] pBytes = minim(pattern);

        Integer[][] mis = new Integer[len][];
        for (int i = 0; i < len; i++) {
            mis[i] = new Integer[]{i, 0};
        }

        for (int step = pLen; step >= 0; step--) {

            byte symbol = pBytes[step];

            int nullCount = 0;
            for (int y = 0; y < mis.length; y++) {

                Integer[] val = mis[y];
                if (val == null) continue;

                int i = val[0];
                int c = val[1];

                if (lastColumn[i] != symbol) {
                    if (c + 1 <= k) {
                        mis[y] = new Integer[]{map[i], c+1};
                    }
                    else {
                        mis[y] = null;
                        nullCount++;
                    }
                }
                else {
                    mis[y] = new Integer[]{map[i], c};
                }

            }

            Integer[][] mis2 = new Integer[mis.length-nullCount][];
            int i2 = 0;
            for (Integer[] mi : mis) {
                if (mi == null) continue;
                mis2[i2] = mi;
                i2++;
            }
            mis = mis2;

        }
        return mis;

    }

    public void partial(int k) {

        for (int i = 0; i < len; i++) {

            if (suffix[i] % k == 0) {
                Dump.println(i + "," + suffix[i]);
            }

        }

    }

    public String reconstruct() {

        StringBuilder sb = new StringBuilder();
        int counter = 0;
        int index = 0;
        while (counter < firstColumn.length) {

            sb.append(firstColumn[index]);
            index = map[index];

            counter++;
        }

        return sb.reverse().toString();

    }

    public String reconstruct(int index, int count) {

        StringBuilder sb = new StringBuilder();
        int counter = 0;
        index = map[index];
        while (counter < count) {

            sb.append(firstColumn[index]);
            index = map[index];

            counter++;
        }

        return sb.reverse().toString();

    }

    public int compareTo1(int i, int jj) {

        int result = 0;
        int ii = pos1[i];

        for (int k = 0; k < len; k++) {

            result = (bytes[jj] - bytes[ii]);
            if (result != 0) {
                break;
            }

            ii += 1;
            jj += 1;

            if (ii >= len) ii = 0;
            if (jj >= len) jj = 0;

        }
        return result;

    }

    public void qSort1(int low, int high) {
        int i = low;
        int x = pos1[(low + high) / 2];
        int j = high;

//        Dump.println("[" + i + ":" + x + ":" + j + "]");

        do {
            while (compareTo1(i, x) < 0) ++i;
            while (compareTo1(j, x) > 0) --j;
            if (i <= j) {
                int temp = pos1[i];
                pos1[i] = pos1[j];
                pos1[j] = temp;
                i++ ; j--;
            }
        } while(i <= j);
        //рекурсивные вызовы функции qSort
        if (low < j) qSort1(low, j);
        if (i < high) qSort1(i, high);
    }

    public int compareTo2(int i, int jj) {

        int result = 0;
        int ii = pos2[i];
        int diff = jj - ii;

        for (int k = ii; k < len; k++) {
            result = (bytes[k+diff] - bytes[k]);
            if (result != 0) {
                break;
            }
        }
        return result;

    }

    public void qSort2(int low, int high) {
        int i = low;
        int x = pos2[(low + high) / 2];
        int j = high;

//        Dump.println("[" + i + ":" + x + ":" + j + "]");

        do {
            while (compareTo2(i, x) < 0) ++i;
            while (compareTo2(j, x) > 0) --j;
            if (i <= j) {
                int temp = pos2[i];
                pos2[i] = pos2[j];
                pos2[j] = temp;
                i++ ; j--;
            }
        } while(i <= j);
        //рекурсивные вызовы функции qSort
        if (low < j) qSort2(low, j);
        if (i < high) qSort2(i, high);
    }

}
