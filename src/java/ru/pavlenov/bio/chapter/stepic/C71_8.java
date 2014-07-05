package ru.pavlenov.bio.chapter.stepic;

import ru.pavlenov.bio.utils.Dump;

import java.util.*;

/**
 * Какой сам ⚝
 * Author: Pavlenov Semen
 * Date: 19.12.13
 * <p>
 * E = mc^2
 */
public class C71_8 {

    public static class MoneyChange {

        private HashMap<Integer, Integer> minNumCoins = new HashMap<>();
        private Integer money;
        private Integer[] coins;

        public MoneyChange(Integer money, Integer[] coins) {
            this.money = money;
            this.coins = coins;
            Arrays.sort(this.coins);
            genMinNumCoins();
        }

        public void genMinNumCoins() {

            minNumCoins.put(0, 0);
            for (int i = 1; i <= money; i++) {
                List<Integer> list = new ArrayList<>();
                for (Integer coin : coins) {
                    int m = i - coin;
                    if (m < 0 && list.isEmpty()) m = 0;
                    if (minNumCoins.containsKey(m)) {
                        list.add(minNumCoins.get(m));
                    }
                }
                minNumCoins.put(i, Collections.min(list) + 1);
            }
        }

        public HashMap<Integer, Integer> getMinNumCoins() {
            return minNumCoins;
        }

    }

    public static void start() {

        Integer money = 19463;
        Integer[] coins = new Integer[]{22,18,7,5,3,1};

        MoneyChange moneyChange = new MoneyChange(money, coins);

        Dump.println(moneyChange.getMinNumCoins().get(money));

    }

}
