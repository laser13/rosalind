package ru.pavlenov.bio.chapter.stepic;

import org.apache.commons.lang.StringUtils;
import ru.pavlenov.bio.amino.AffineGlobalAlignment;
import ru.pavlenov.bio.amino.InvalidAlphabetException;
import ru.pavlenov.bio.utils.Dump;

import java.util.ArrayList;

/**
 * Какой сам ☭
 * User: Павленов Семён
 * Date: 02.01.14 15:59
 */
public class C249_8 {

    public static void start() throws InvalidAlphabetException {

//        String str1 = "PRTEINS";
//        String str2 = "PRTWPSEIN";

//        String str1 = "GCYTVVK";
//        String str2 = "DVVK";

        String str1 = "YAFDLGYTCMFPVLLGGGELHIVQKETYTAPDEIAHYIKEHGITYIKLTPSLFHTIVNTASFAFDANFESLRLIVLGGEKIIPIDVIAFRKMYGHTEFINHYGPTEATIGA";
        String str2 = "AFDVSAGDFARALLTGGQLIVCPNEVKMDPASLYAIIKKYDITIFEATPALVIPLMEYIYEQKLDISQLQILIVGSDSCSMEDFKTLVSRFGSTIRIVNSYGVTEACIDS";

//        String str1 = "YHTEVWE";
//        String str2 = "YHTDQPERMRVWE";

        AffineGlobalAlignment alignment = new AffineGlobalAlignment(str1, str2);
        alignment
                .setGapOpenPenalty(11)
                .setGapExtPenalty(1)
                .setScoringMatrix(AffineGlobalAlignment.ScoryngMatrixType.BLOSUM62);

        alignment.make();

//        Dump.println("RightMatrix");
//        Dump.println(alignment.getRightMatrix());
//        Dump.println("------------");
//        Dump.println("DownMatrix");
//        Dump.println(alignment.getDownMatrix());
//        Dump.println("------------");
//        Dump.println("BiasMatrix");
//        Dump.matrix(alignment.getBiasMatrix());
//        Dump.println("------------");
//        Dump.println(alignment.getTrackDownMatrix());
//        Dump.println("------------");
//        Dump.println(alignment.getTrackRightMatrix());
//        Dump.println("------------");
//        Dump.println(alignment.getTrackBiasMatrix());

        alignment.track(str1.length()-1, str2.length()-1, alignment.getTrackBiasMatrix());

        ArrayList<Character> result1 = alignment.getResult1();
        ArrayList<Character> result2 = alignment.getResult2();

        Dump.println(alignment.getBiasMatrix()[str1.length()][str2.length()]);
        Dump.println(alignment.getDownMatrix()[str1.length()][str2.length()]);
        Dump.println(alignment.getRightMatrix()[str1.length()][str2.length()]);


        Dump.println(StringUtils.join(result1, ""));
        Dump.println(StringUtils.join(result2, ""));

        Dump.line();

        Dump.println("YHFDVPDCWAHRYWVENPQAIAQME-------QICFNWFPSMMMK-------QPHVFKV---DHHMSCRWLPIRGKKCSSCCTRMRVRTVWE\n" +
                "YHEDV----AHE------DAIAQMVNTFGFVWQICLNQFPSMMMKIYWIAVLSAHVADRKTWSKHMSCRWLPI----ISATCARMRVRTVWE");

    }

}
