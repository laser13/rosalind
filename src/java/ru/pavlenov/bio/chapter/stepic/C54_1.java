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
 * Date: 02.12.13 19:43
 */
public class C54_1 {

    public static void start() throws IOException {

        String text = File.readFile("/home/laser13/IdeaProjects/bio/genome/dataset_54_7.txt", Charset.defaultCharset());

        HashMap<String, ArrayList<String>> result = Base.getDeBruijnGraph(text.split("\n"));

        Map sortResult = Sort.byKey(result);

//        Dump.println(text);
        for (Object pair0 : sortResult.entrySet()) {
            Map.Entry<String, ArrayList<String>> pair = (Map.Entry<String, ArrayList<String>>) pair0;
            if (!pair.getValue().isEmpty()) {
                Collections.sort(pair.getValue());
                Dump.print(pair.getKey() + " -> " + StringUtils.join(pair.getValue(), ",")); Dump.println("");
            }
        }
        Dump.println(result.size());
        Dump.println("");

    }

}
