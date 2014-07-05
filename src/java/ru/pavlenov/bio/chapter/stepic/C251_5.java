package ru.pavlenov.bio.chapter.stepic;

import org.apache.commons.lang.StringUtils;
import ru.pavlenov.bio.amino.MultipleAlignment;
import ru.pavlenov.bio.utils.Dump;

/**
 * Какой сам ☭
 * User: Павленов Семён
 * Date: 06.01.14 14:08
 */
public class C251_5 {

    public static void start() {

        String str1 = "TCGCTGTCCA";
        String str2 = "ACCATCGACG";
        String str3 = "AGTCCAACCA";

        Dump.println(str1 + ":" + str1.length());
        Dump.println(str2 + ":" + str2.length());
        Dump.println(str3 + ":" + str3.length());
        Dump.line();

        MultipleAlignment align = new MultipleAlignment(str1, str2, str3);
        align.run();
        align.backTrack(str1.length()-1, str2.length()-1, str3.length()-1);

        Integer[][][] weight = align.getWeightArray();

        Dump.println(weight[str1.length()][str2.length()][str3.length()]);

        Dump.println(StringUtils.join(align.getResult1(), ""));
        Dump.println(StringUtils.join(align.getResult2(), ""));
        Dump.println(StringUtils.join(align.getResult3(), ""));

    }

}
