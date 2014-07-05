package ru.pavlenov.bio.graph.trie;

import java.util.List;

/**
 * Какой сам ☭
 * User: Павленов Семён
 * Date: 15.01.14 19:03
 */
public class TrieNode extends ru.pavlenov.bio.graph.Node {

    protected Integer level;
    protected List<TrieEdge> inTrieEdgeList;
    protected List<TrieEdge> outTrieEdgeList;

    public TrieNode(int index, int level) {
        super(index);
        setLevel(level);
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public List<TrieEdge> getOutTrieEdgeList() {
        return outTrieEdgeList;
    }

    public List<TrieEdge> getInTrieEdgeList() {
        return inTrieEdgeList;
    }

}
