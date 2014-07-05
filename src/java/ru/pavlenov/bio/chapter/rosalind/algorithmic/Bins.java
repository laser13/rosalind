package ru.pavlenov.bio.chapter.rosalind.algorithmic;

import ru.pavlenov.bio.utils.Convert;
import ru.pavlenov.bio.utils.Dump;
import ru.pavlenov.bio.utils.File;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * Created by laser13 on 27.06.14.
 * Binary Search
 * http://rosalind.info/problems/bins/
 *
 * The problem is to find a given set of keys in a given array.
 *
 * Given: Two positive integers n≤105 and m≤105, a sorted array A[1..n] of integers from −105 to 105 and a list of m integers −105≤k1,k2,…,km≤105.
 *
 * Return: For each ki, output an index 1≤j≤n s.t. A[j]=ki or "-1" if there is no such index.
 *
 */
public class Bins {

    public static void start() throws IOException {

        String[] data = File.readFile("/home/laser13/IdeaProjects/bio/src/ru/pavlenov/bio/chapter/rosalind/algorithmic/Bins.data", Charset.defaultCharset()).split("\n");

        Integer[] arr = Convert.from(data[2]).toIntArray(" ");
        Integer[] val = Convert.from(data[3]).toIntArray(" ");

//        Dump.println(arr);
//        Dump.println(val);

        for (Integer v : val) {

            int i = Arrays.binarySearch(arr, v);

            Dump.print(((i >= 0) ? i+1 : -1) + " ");

        }

        Dump.ln();



    }

}
