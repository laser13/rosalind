package ru.pavlenov.bio.genome;

import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import ru.pavlenov.bio.amino.RNA;
import ru.pavlenov.bio.utils.Dump;

import java.util.ArrayList;

/**
 * Какой сам ☭
 * User: Павленов Семён
 * Date: 17.11.13 16:43
 */
public class DNA {

    private String text;

    public DNA(String text) {
        this.text = text.toUpperCase().trim();
    }

    public String get() {
        return text;
    }

    public String reverse() {
         return reverse(text);
    }

    public static String reverse(String text) {
        StringBuilder sb = new StringBuilder();
        for (Character ch : text.toCharArray()) {
            if (ch == 'A') sb.append('T');
            if (ch == 'T') sb.append('A');
            if (ch == 'C') sb.append('G');
            if (ch == 'G') sb.append('C');
        }
        return sb.reverse().toString();
    }

    public RNA transcribe() {
        return new RNA(transcribe(text));
    }

    public static String transcribe(String text) {
        return text.replaceAll("T", "U");
    }

    /**
     * Подсчитывает кол-во нуклеотидов C и G в строке
     *
     * @return
     */
    public int GCContent() {
        int gc = 0;
        for (Character ch : text.toCharArray()) {
            if (ch == 'C' || ch == 'G') gc++;
        }
        return gc;
    }

    public Integer[] getMotifIndeces(String motif) {

        ArrayList<Integer> result = Lists.newArrayList();
        int i = text.indexOf(motif, 0);
        while (i >= 0) {
            result.add(i);
            i = text.indexOf(motif, i+1);
        }
        return result.toArray(new Integer[result.size()]);

    }

    public void getReversePolindrome(int minLen, int maxLen) {

        for (int len = minLen; len <= maxLen; len++) {

            for (int i = 0; i < text.length() - len + 1; i++) {

                String sub = text.substring(i, i + len);
                String reverse = reverse(sub);

                if (reverse.equals(sub)) {
                    Dump.println((i+1) + " " + len);
//                    Dump.println(i + " " + len + " > " + sub + " | " + reverse);
                }

            }

        }

    }

}
