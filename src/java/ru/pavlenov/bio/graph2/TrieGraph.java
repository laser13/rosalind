package ru.pavlenov.bio.graph2;

import java.util.*;

/**
 * Какой сам ⚝
 * Author: Pavlenov Semen
 * Date: 17.01.14
 * <p>
 * E = mc^2
 */
public class TrieGraph extends DirectedGraph<Integer, TrieEdge> {

    public TrieGraph() {
        super(Integer.MAX_VALUE / 1_000);
        nodes.put(0, new ArrayList<TrieEdge>());
    }

    public TrieGraph(int nodeSize) {
        super(nodeSize);
        nodes.put(0, new ArrayList<TrieEdge>());
    }

    public Set<TrieEdge> getEdges(int level) {

        Set<TrieEdge> result = new HashSet<>();
        for (TrieEdge edge : edges) {
            if (level == edge.getLevel()) result.add(edge);
        }
        return result;

    }

    public List<TrieEdge> getEdges(Integer node, int level) {

        List<TrieEdge> result = new ArrayList<>();
        List<TrieEdge> edges = nodes.get(node);

        for (TrieEdge edge : edges) {
            if (level == edge.getLevel()) result.add(edge);
        }
        return result;

    }

    public List<TrieEdge> upFrom(Integer node) {

        List<TrieEdge> list = new ArrayList<>();
        Integer currNode = node;
        while (!currNode.equals(0)) {
            TrieEdge edge = this.finishOf(currNode).get(0);
            list.add(edge);
            currNode = edge.getSourceNode();
        }
        return list;

    }

    public void compact() {

        List<TrieEdge> edges = this.edges;

        Set<TrieEdge> edgesForRemove = new HashSet<>();
        List<Integer> nodesForRemove = new ArrayList<>();

        for (TrieEdge edge : edges) {

            if (edge.isLeaf() || startOf(edge.getTargetNode()).size() > 1) {

                TrieEdge currEdge = edge;
                Integer nodeSource = currEdge.getSourceNode();
                List<TrieEdge> list = finishOf(nodeSource);

                // У исходящего узла должно быть только 2 ребра и входящее ребро не должно быть листом(leaf)
                while (nodes.get(nodeSource).size() == 2 && nodeSource != 0 && !finishOf(nodeSource).get(0).isLeaf()) {

                    // Получили следующее ребро
                    currEdge = finishOf(nodeSource).get(0);
                    // Удалили вершину между ними
                    nodesForRemove.add(nodeSource);

                    // Полусили вершину стартовую для полученного ребра
                    nodeSource = currEdge.getSourceNode();

                    // Добавили значение
                    edge.setData(currEdge.getData() + edge.getData());
                    edge.setSourceNode(nodeSource);

                    edgesForRemove.add(currEdge);

                }

            }

        }

        removeAllEdges(edgesForRemove);
        removeAllNodes(nodesForRemove);

    }

    public void insert(String substring) {

//        Dump.println("=-=-=-=");
        int nodeSource = 0;
        for (int level = 0; level < substring.length(); level++) {

            boolean isLeaf = (level == substring.length() - 1);

            String c = String.valueOf(substring.charAt(level));
            List<TrieEdge> edges = startOf(nodeSource);

//            Dump.print("Level" + level + ": " + c + " > size:" + edges.size() + " | ");

            boolean exist = false;

            if (!edges.isEmpty()) {
                for (TrieEdge edge : edges) {
                    if (edge.getData().equals(c)) {
//                        Dump.print(nodeSource + ">" + edge.getTargetNode() + " [" + edge.getData() + "]");
                        nodeSource = edge.getTargetNode();
                        exist = true;
                        break;
                    }
                }
            }

            if (!exist) {
                int nodeTarget = this.nodes.size();
                addEdge(nodeSource, nodeTarget, new TrieEdge(c, level, isLeaf));
//                Dump.print(nodeSource + ">" + nodeTarget);
                nodeSource = nodeTarget;
            }
//            Dump.println("");
        }

    }

}
