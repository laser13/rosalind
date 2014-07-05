package ru.pavlenov.bio.utils;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by semen on 10.12.13.
 */
public class Gen {

    public static final byte[] bs = new byte[]{1, 2, 4, 8, 16, 32, 64};

    public static byte[][] permutation(int base, int len) {

        int size = (int) Math.pow(base, len);
        byte[][] result = new byte[size][len];
        for(int i = 0 ; i < size; i++){
            byte[] strBytes = Integer.toString(i, base).getBytes();
            for(int j = 0; j < len; j++){
                if (j >= strBytes.length){
                    result[i][j] = bs[0];
                    continue;
                }
                byte value = (byte) (strBytes[strBytes.length-j-1] - 48); //48 - "0", 49 - "1"
                byte bit = (byte) Math.pow(2, value);
                result[i][j] = bit;
            }
        }
        return result;

    }

}
