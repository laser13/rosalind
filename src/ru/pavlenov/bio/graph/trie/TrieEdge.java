package ru.pavlenov.bio.graph.trie;


/**
 * Какой сам ☭
 * User: Павленов Семён
 * Date: 15.01.14 19:24
 */
public class TrieEdge<E> extends ru.pavlenov.bio.graph.Edge<E> {

    protected Integer level;

    public TrieEdge(Integer index, TrieNode fromTrieNode, TrieNode toTrieNode) {
        super(index, fromTrieNode, toTrieNode);
        setLevel(fromTrieNode.getLevel());
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }


}
