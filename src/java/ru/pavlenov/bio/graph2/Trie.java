package ru.pavlenov.bio.graph2;

import ru.pavlenov.bio.utils.Dump;

import java.util.ArrayList;
import java.util.List;

/**
 * Какой сам ☭
 * User: Павленов Семён
 * Date: 20.01.14 18:08
 */
public class Trie {

    private List<TrieNode> nodes;
    private int nodeIndex = 0;
    private int root = 0;

    public Trie() {
        nodes = new ArrayList<>();
        nodes.add(new TrieNode(root, TrieNode.NULL));
        nodeIndex++;
    }

    public Trie(int size) {
        nodes = new ArrayList<>(size);
        nodes.add(new TrieNode(root, TrieNode.NULL));
        nodeIndex++;
    }

    public List<TrieNode> getNodes() {
        return nodes;
    }

    public int[] upFrom(int index) {

        TrieNode currNode = nodes.get(index);
        int currNodeIndex = index;

        int[] result = new int[currNode.getLevel()];
        int pos = 0;

        while (currNode.getParent() != TrieNode.NULL) {

            int parent = currNode.getParent();
            currNode = nodes.get(parent);
            int[][] edges = currNode.getEdges();

            for (int[] edge : edges) {
                if (edge[1] == currNodeIndex) {
                    result[pos] = edge[0];
                    pos++;
                    break;
                }
            }

            currNodeIndex = parent;

        }

        return result;
    }

    public List<Character> upFrom(int index, boolean leaf) {

        List<Character> result = new ArrayList<>();

        TrieNode currNode = nodes.get(index);
        int currNodeIndex = index;

//        Dump.println("===");
//        Dump.println(currNode);

        while (currNode.getParent() != TrieNode.NULL) {

            int parent = currNode.getParent();
            currNode = nodes.get(parent);
            int[][] edges = currNode.getEdges();

            for (int[] edge : edges) {
                if (edge[1] == currNodeIndex) {
                    result.add((char) edge[0]);
                    break;
                }
            }

//            Dump.println(currNode + " >>> " + currNode.hasLeaf());

            if (currNode.countEdges() > 1) {
                break;
            }

            currNodeIndex = parent;

        }

        return result;
    }

    public String toString(List<Character> path) {
        String answer = "";
        for (int val : path) {
            answer = (char) val + answer;
        }
        return answer;
    }

    public void addString(String str) {
        byte[] bytes = str.getBytes();

        int node = root;
        int level = 1;

        for (byte b : bytes) {

            int[][] edges = nodes.get(node).getEdges();

            boolean hasEdge = false;
            for (int[] edge : edges) {
                if (edge[0] == b) {
                    node = edge[1];
                    hasEdge = true;
                    break;
                }
            }

            if (!hasEdge) {
                nodes.get(node).addEdge(new int[]{b, nodeIndex});
                nodes.add(new TrieNode(level, node));
                node = nodeIndex;
                nodeIndex++;
            }
            level++;

        }

    }

    public void compact() {

        for (int nodeIndex = 0; nodeIndex < nodes.size(); nodeIndex++) {

            TrieNode currNode = nodes.get(nodeIndex);
            int parent = currNode.getParent();

            // Если это конечная нода, то найдём родителя
            if (currNode.countEdges() > 0) continue;

            currNode = nodes.get(currNode.getParent());
            TrieNode parentNoda = nodes.get(currNode.getParent());

            // Пока поднимаемся по нодам которые не являются узловыми
            while (nodes.get(currNode.getParent()).hasLeaf() != TrieNode.NULL) {



            }

        }

    }

    public String toString() {
        return nodes.toString();
    }

}
