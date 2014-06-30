package ru.pavlenov.bio.chapter.rosalind.probability;

import ru.pavlenov.bio.utils.Dump;

/**
 * Какой сам ☭
 * User: Павленов Семён
 * Date: 08.01.14 20:28
 */
public class Iev {

    /**
     * Calculating Expected Offspring
     * http://rosalind.info/problems/iev/
     *
     * Given: Six positive integers, each of which does not exceed 20,000. The integers correspond to the number
     * of couples in a population possessing each genotype pairing for a given factor. In order, the six given
     * integers represent the number of couples having the following genotypes:
     * 1. AA-AA
     * 2. AA-Aa
     * 3. AA-aa
     * 4. Aa-Aa
     * 5. Aa-aa
     * 6. aa-aa
     *
     * Return: The expected number of offspring displaying the dominant phenotype in the next generation, under the assumption that every couple has exactly two offspring.
     *
     */
    public static void start() {

        Float[] probA = { 1f, 1f, 1f, 0.75f, 0.5f, 0f };

        Integer[] count = {19915, 19043, 17663, 17290, 19329, 17572};

        Float expected = 0f;
        for (int i = 0; i < 6; i++) {
            expected += 2 * count[i] * probA[i];
        }


        Dump.println(expected);

    }

}
