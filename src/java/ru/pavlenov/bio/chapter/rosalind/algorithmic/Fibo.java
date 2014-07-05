package ru.pavlenov.bio.chapter.rosalind.algorithmic;

import ru.pavlenov.bio.algorithm.Fibonacci;
import ru.pavlenov.bio.utils.Dump;

/**
 * Created by laser13 on 27.06.14.
 *
 * http://rosalind.info/problems/fibo/
 * Fibonacci Numbers
 *
 * Given: A positive integer n≤25.
 * Return: The value of Fn.
 *
 */
public class Fibo {


    public static void start() {

        int n = 23;

        Long f = Fibonacci.get(n);

        Dump.println(f);


    }



}
