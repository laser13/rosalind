package ru.pavlenov.bio.graph2;

/**
 * Какой сам ⚝
 * Author: Pavlenov Semen
 * Date: 17.01.14
 * <p>
 * E = mc^2
 */
public class TrieEdge extends AbstractEdge<String, Integer> implements IBaseEdge<String, Integer> {

    protected int level;
    protected boolean isLeaf = false;
    protected int leafIndex;

    public TrieEdge(String data) {
        setData(data);
    }

    public TrieEdge(String data, int level) {
        setData(data);
        setLevel(level);
    }

    public TrieEdge(String data, int level, boolean isLeaf) {
        setData(data);
        setLevel(level);
        setLeaf(isLeaf);
    }

    public TrieEdge(String data, int level, boolean isLeaf, int leafIndex) {
        setData(data);
        setLevel(level);
        setLeaf(isLeaf);
        this.leafIndex = leafIndex;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int compareTo(TrieEdge edge) {
        int result = 0;
        result = this.level - edge.getLevel();
        if (result == 0) {
            result = this.index - edge.getIndex();
        }
//        return result;
        return this.index - edge.getIndex();
    }

    public String toString() {
//        return "E" + level + "("+ data + "|" + sourceNode + "," + targetNode + ")" + ((isLeaf) ? "+" : "-");
        return data;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean isLeaf) {
        this.isLeaf = isLeaf;
    }

}
