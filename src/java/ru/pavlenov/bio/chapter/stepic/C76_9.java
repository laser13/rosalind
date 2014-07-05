package ru.pavlenov.bio.chapter.stepic;

import org.apache.commons.lang.StringUtils;
import ru.pavlenov.bio.amino.LocalAlignment;
import ru.pavlenov.bio.amino.InvalidAlphabetException;
import ru.pavlenov.bio.utils.Dump;

/**
 * Какой сам ☭
 * User: Павленов Семён
 * Date: 21.12.13 20:14
 */
public class C76_9 {

    public static void start() throws InvalidAlphabetException {

        int σ = 5;

        String str2 = "PLEASANTLY";
        String str1 = "MEANLY";

        Dump.println(str1.toCharArray());
        Dump.println(str2.toCharArray());

        LocalAlignment alignment = new LocalAlignment(str1, str2);
        alignment
                .setScoringMatrix(LocalAlignment.ScoryngMatrixType.PAM250)
                .setPenalty(σ);

        alignment.make();

//        Dump.println(str1.toCharArray());
//        Dump.println(str2.toCharArray());
//        Dump.println(alignment.getTrackMatrix());
//        Dump.println(alignment.getWeightMatrix());

        Dump.println("[" + alignment.getStartI() + ":" + alignment.getStartJ() + "]=" + alignment.getGlobalMaxWeight());

        alignment.track(alignment.getStartI()-1, alignment.getStartJ()-1);

        Dump.println(StringUtils.join(alignment.getResult2(), ""));
        Dump.println(StringUtils.join(alignment.getResult1(), ""));


        Dump.println("");


    }

}
