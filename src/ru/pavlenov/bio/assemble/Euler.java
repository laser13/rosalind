package ru.pavlenov.bio.assemble;

import ru.pavlenov.bio.utils.Dump;

import java.util.*;

/**
 * Created by semen on 03.12.13.
 */
public class Euler {

    public static ArrayList<Integer[]> findNonBranchPath(Graph.Directed directedGraph) {

        ArrayList<Integer[]> result = new ArrayList<>();
        ArrayList<Graph.Directed.Node> startedNode = directedGraph.findStarted();

        while (!startedNode.isEmpty()) {

//            Dump.println(startedNode);

            ArrayList<Graph.Directed.Node> path = new ArrayList<>();

            Graph.Directed.Node node = startedNode.get(0);
            path.add(node);

//            Dump.println(node);

            Graph.Directed.Node nextNode = directedGraph.get(node.getOutEdge(0));
            path.add(nextNode);

            while (nextNode.isNonBranching()) {
                directedGraph.removeEdge(node.getIndex(), nextNode.getIndex());
                node = nextNode;

//                Dump.println(node);

                nextNode = directedGraph.get(node.getOutEdge(0));
                path.add(nextNode);
            }

            directedGraph.removeEdge(node.getIndex(), nextNode.getIndex());

            Integer[] pathArray = new Integer[path.size()];
            for (int i = 0; i < path.size(); i++) {
                pathArray[i] = path.get(i).getIndex();
            }

            result.add(pathArray);


            startedNode = directedGraph.findStarted();


        }

        return result;


    }

    public static Integer[] findFullPath(Graph.Directed directedGraph) {

        ArrayList<Graph.Directed.Node> unbalancedNode = directedGraph.findUnbalanced();

        Graph.Directed.Node startNode;
        Graph.Directed.Node endNode;

        if (!unbalancedNode.isEmpty()) {
            if (unbalancedNode.get(0).edgeDiff() > 0) {
                startNode = unbalancedNode.get(0);
                endNode = unbalancedNode.get(1);
            }
            else {
                startNode = unbalancedNode.get(1);
                endNode = unbalancedNode.get(0);
            }
            startNode.getInEdgeList().add(endNode.getIndex());
            endNode.getOutEdgeList().add(startNode.getIndex());
        }
        else {
            startNode = directedGraph.get(0);
            endNode = directedGraph.get(startNode.getOutEdge(0));
        }

        ArrayList<Graph.Directed.Node> fullCycle = findFullCycle(directedGraph, endNode, endNode.getOutEdgeList().indexOf(startNode.getIndex()));

        ArrayList<Integer> tail = new ArrayList<>();
        ArrayList<Integer> head = new ArrayList<>();

        boolean toHead = false;
        Graph.Directed.Node prev = null;
        for (Graph.Directed.Node node : fullCycle) {

            if (node.equals(startNode) && prev != null && prev.equals(endNode)) {
                toHead = true;
            }
            prev = node;

            if (toHead) {
                head.add(node.getIndex());
            }
            else{
                tail.add(node.getIndex());
            }

        }
        head.addAll(tail);

        return head.toArray(new Integer[head.size()]);

    }

    public static ArrayList<Graph.Directed.Node> findFullCycle(Graph.Directed directedGraph, Graph.Directed.Node startNode, Integer nextIndex) {

        ArrayList<Graph.Directed.Node> fullCycle = new ArrayList<>();
        while (startNode != null) {

            ArrayList<Graph.Directed.Node> cycle = new ArrayList<>();
            while (cycle.isEmpty()) cycle = findCycle(directedGraph, startNode, nextIndex);

            if (fullCycle.isEmpty()) {
                fullCycle = cycle;
            }
            else {

                boolean added = false;
                ArrayList<Graph.Directed.Node> tmpCycle = new ArrayList<>();
                for (Graph.Directed.Node node : fullCycle) {
                    if (!added && node.getIndex().equals(startNode.getIndex())) {
                        tmpCycle.addAll(cycle);
                        added = true;
                    }
                    tmpCycle.add(node);
                }
                fullCycle = tmpCycle;
            }

            startNode = null;
            nextIndex = null;
            for (Graph.Directed.Node node : fullCycle) {
                if (!node.getOutEdgeList().isEmpty()) {
                    startNode = node;
                    break;
                }
            }
        }

        return fullCycle;

    }

    public static ArrayList<Graph.Directed.Node> findCycle(Graph.Directed directedGraph, Graph.Directed.Node startNode, Integer nextIndex) {

        ArrayList<Graph.Directed.Node> cycle = new ArrayList<>();
        Integer start = startNode.getIndex();
        Integer next;
        if (nextIndex != null) {
            next = startNode.getOutAndRemove(nextIndex);
        }
        else next = startNode.getOutAndRemove(0);
        cycle.add(startNode);

        Graph.Directed.Node currNode = directedGraph.get(next);

        while (!currNode.getOutEdgeList().isEmpty() && !next.equals(start)) {
            cycle.add(directedGraph.get(next));
            next = currNode.getOutAndRemove(0);
            currNode = directedGraph.get(next);
        }
        return cycle;

    }

}
