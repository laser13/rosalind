package ru.pavlenov.bio.utils;

import ru.pavlenov.bio.genome.DNAArray;

/**
 * Created by semen on 28.11.13.
 */
public class Random {

    public static java.util.Random random = new java.util.Random();

    public static int range(int min, int max) {
        return random.nextInt(max - min) + min;
    }

    public static class RndWeightItem<T> {
        private T value;
        private Float weight;

        public RndWeightItem(T value, Float weight) {
            this.value = value;
            this.weight = weight;
        }

        public T getValue() {
            return value;
        }

        public Float getWeight() {
            return weight;
        }

        public void setWeight(Float weight) {
            this.weight = weight;
        }
    }

    public static RndWeightItem weightRange(RndWeightItem[] items) {

        float totalWeight = 0.0f;
        for (RndWeightItem i : items) {
            totalWeight += i.getWeight();
        }

        int randomIndex = -1;
        double random = Math.random() * totalWeight;
        for (int i = 0; i < items.length; ++i) {
            random -= items[i].getWeight();
            if (random <= 0.0f) {
                randomIndex = i;
                break;
            }
        }

        return items[randomIndex];

    }

}
