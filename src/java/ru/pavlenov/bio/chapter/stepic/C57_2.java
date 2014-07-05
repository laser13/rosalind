package ru.pavlenov.bio.chapter.stepic;

import org.apache.commons.lang.StringUtils;
import ru.pavlenov.bio.assemble.Euler;
import ru.pavlenov.bio.assemble.Graph;
import ru.pavlenov.bio.utils.Dump;
import ru.pavlenov.bio.utils.File;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;

/**
 * Created by semen on 05.12.13.
 */
public class C57_2 {

    public static void start() throws IOException {

        String input = File.readFile("/home/laser13/IdeaProjects/bio/genome/dataset_57_5.txt", Charset.defaultCharset());
        String[] inputList = input.split("\n");

        Graph.Directed  directedGraph = new Graph.Directed();


        SortedSet<Integer> sortedSet = new TreeSet<>();
        for (int i = 0; i < inputList.length; i++) {
            String[] pair = inputList[i].split(" -> ");
            String[] outStr = pair[1].split(",");
            ArrayList<Integer> out = new ArrayList<>();
            for (String anOutStr : outStr) {
                out.add(Integer.parseInt(anOutStr));
                sortedSet.add(Integer.parseInt(anOutStr));
            }
            int index = Integer.parseInt(pair[0]);

            directedGraph.addNode(new Graph.Directed.Node(index, out));

            sortedSet.add(index);

        }

        for (Integer index : sortedSet) {
            if (!directedGraph.containsKey(index)) {
                directedGraph.addNode(new Graph.Directed.Node(index));
            }
        }
        directedGraph.build();

        Dump.println("Power: " + directedGraph.getPower());

        Integer[] path = Euler.findFullPath(directedGraph);

        Dump.println("RESULT: ");
        String resultStr = StringUtils.join(path, "->");
        Dump.println(resultStr);

        File.writeFile("/home/laser13/IdeaProjects/bio/genome/57_5_answer.txt", resultStr);

        Dump.println(path.length);

    }


}
