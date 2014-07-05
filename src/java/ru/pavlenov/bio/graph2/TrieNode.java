package ru.pavlenov.bio.graph2;

import java.util.ArrayList;

/**
 * Какой сам ☭
 * User: Павленов Семён
 * Date: 20.01.14 19:11
 */
public class TrieNode {

    private int[][] edges;
    private int parent;
    private int level;

    public static int NULL = -1;

    public TrieNode(int level, int parent) {
        this.edges = new int[][]{
            {NULL, NULL},
            {NULL, NULL},
            {NULL, NULL},
            {NULL, NULL},
            {NULL, NULL}
        };
        this.level = level;
        this.parent = parent;
    }

    public void addEdge(int[] edge) {

//        if (edge[0] == 'A') edge[0] = 1;
//        else if (edge[0] == 'C') edge[0] = 2;
//        else if (edge[0] == 'G') edge[0] = 3;
//        else if (edge[0] == 'T') edge[0] = 4;
//        else if (edge[0] == '$') edge[0] = 0;

        for (int i = 0; i < 5; i++) {
            if (edges[i][1] == NULL) {
                edges[i] = edge;
                break;
            }
        }
    }

    public int[][] getEdges() {
        return edges;
    }

    public int countEdges() {
        int count = 0;
        for (int[] edge : edges) {
            if (edge[0] != NULL && edge[1] != NULL) count += 1;
        }
        return count;
    }

    public void setEdges(int[][] edges) {
        this.edges = edges;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(level).append(" {");
        for (int[] edge : edges) {
            if (edge[1] > 0)
                sb.append("[").append((char) edge[0]).append(":").append(edge[1]).append("]");
        }

        return sb.append("}").toString();
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public int hasLeaf() {
        int has = NULL;
        for (int[] edge : edges) {
            if (edge[0] == 36) {
                has = edge[1];
            }
        }
        return has;
    }
}
