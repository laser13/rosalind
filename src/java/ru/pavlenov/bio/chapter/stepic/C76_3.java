package ru.pavlenov.bio.chapter.stepic;

import org.apache.commons.lang.StringUtils;
import ru.pavlenov.bio.amino.GlobalAlignment;
import ru.pavlenov.bio.amino.InvalidAlphabetException;
import ru.pavlenov.bio.utils.Dump;

/**
 * Какой сам ☭
 * User: Павленов Семён
 * Date: 21.12.13 13:06
 */
public class C76_3 {

    public static void start() throws InvalidAlphabetException {

        int σ = 5;

        String str2 = "PLEASANTLY";
        String str1 = "MEANLY";

        Dump.println(str1.toCharArray());
        Dump.println(str2.toCharArray());

        GlobalAlignment globalAlignment = new GlobalAlignment(str1, str2);
        globalAlignment
                .setScoringMatrix(GlobalAlignment.ScoryngMatrixType.BLOSUM62)
                .setPenalty(σ);

        globalAlignment.make();

        Dump.println(str1.toCharArray());
        Dump.println(str2.toCharArray());
        Dump.println(globalAlignment.getTrackMatrix());
        Dump.println(globalAlignment.getWeightMatrix());

        globalAlignment.track(str1.length()-1, str2.length()-1);



        Dump.println(StringUtils.join(globalAlignment.getResult2(), ""));
        Dump.println(StringUtils.join(globalAlignment.getResult1(), ""));


        Dump.println("");


    }

}
