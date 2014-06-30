package ru.pavlenov.bio.algorithm;

/**
 * Created by laser13 on 27.06.14.
 */
public class Fibonacci {

    /**
     * Отдаёт значение i-го елемента последовательности фибоначчи
     *
     * @param i
     * @return
     */
    public static Long get(int i) {

        if (i == 0) return 0l;
        if (i == 1) return 1l;

        long f1 = 0l;
        long f2 = 1l;

        for (int j = 2; j <= i; j++) {

            long _f = f2;
            f2 += f1;
            f1 = _f;

        }

        return f2;

    }

}
