package ru.pavlenov.bio.chapter.stepic;

import ru.pavlenov.bio.assemble.Base;
import ru.pavlenov.bio.utils.Dump;
import ru.pavlenov.bio.utils.File;
import ru.pavlenov.bio.utils.Sort;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Какой сам ☭
 * User: Павленов Семён
 * Date: 30.11.13 9:47
 */
public class C52_1 {

    public static void start() throws IOException {

        String text = "";

        text = File.readFile("/home/laser13/IdeaProjects/bio/genome/dataset_52_7.txt", Charset.defaultCharset());

        HashMap<String, ArrayList<String>> result = Base.getOverlapGraph(text.split("\n"));

        Map sortResult = Sort.byKey(result);

        Dump.println("");
        for (Object pair0 : sortResult.entrySet()) {

            Map.Entry<String, ArrayList<String>> pair = (Map.Entry<String, ArrayList<String>>) pair0;

            if (!pair.getValue().isEmpty()) {
                Dump.print(pair.getKey() + " -> " + pair.getValue().get(0)); Dump.println("");
            }
        }

        Dump.println(result.size());

//        String[] answer = File.readFile("/home/laser13/IdeaProjects/bio/genome/answer.txt", Charset.defaultCharset()).split("\n");

        Dump.println("");

//        Dump.println(answer.length);

    }

}
