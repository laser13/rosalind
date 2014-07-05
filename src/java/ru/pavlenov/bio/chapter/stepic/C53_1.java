package ru.pavlenov.bio.chapter.stepic;

import org.apache.commons.lang.StringUtils;
import ru.pavlenov.bio.assemble.Base;
import ru.pavlenov.bio.utils.Dump;
import ru.pavlenov.bio.utils.File;
import ru.pavlenov.bio.utils.Sort;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Какой сам ☭
 * User: Павленов Семён
 * Date: 30.11.13 9:47
 */
public class C53_1 {

    public static void start() throws IOException {

        int k = 2;
        String text = "TAATGCCATGGGATGTT";

        HashMap<String, ArrayList<String>> result = Base.getDeBruijnGraph(text, 2);

        Map sortResult = Sort.byKey(result);

        Dump.println(text);
        for (Object pair0 : sortResult.entrySet()) {
            Map.Entry<String, ArrayList<String>> pair = (Map.Entry<String, ArrayList<String>>) pair0;
            if (!pair.getValue().isEmpty()) {
                Collections.sort(pair.getValue());
                Dump.print(pair.getKey() + " -> " + StringUtils.join(pair.getValue(), ",")); Dump.println("");
            }
        }
        Dump.println(result.size());
        Dump.println("");

        HashMap<String, ArrayList<String>> result2 = Base.getDeBruijnGraph(text, 3);

        Map sortResult2 = Sort.byKey(result2);

        Dump.println(text);
        for (Object pair0 : sortResult2.entrySet()) {
            Map.Entry<String, ArrayList<String>> pair = (Map.Entry<String, ArrayList<String>>) pair0;
            if (!pair.getValue().isEmpty()) {
                Collections.sort(pair.getValue());
                Dump.print(pair.getKey() + " -> " + StringUtils.join(pair.getValue(), ",")); Dump.println("");
            }
        }
        Dump.println(result2.size());
        Dump.println("");

        HashMap<String, ArrayList<String>> result3 = Base.getDeBruijnGraph(text, 4);

        Map sortResult3 = Sort.byKey(result3);

        Dump.println(text);
        for (Object pair0 : sortResult3.entrySet()) {
            Map.Entry<String, ArrayList<String>> pair = (Map.Entry<String, ArrayList<String>>) pair0;
            if (!pair.getValue().isEmpty()) {
                Collections.sort(pair.getValue());
                Dump.print(pair.getKey() + " -> " + StringUtils.join(pair.getValue(), ",")); Dump.println("");
            }
        }
        Dump.println(result3.size());
        Dump.println("");

    }

}
