package ru.pavlenov.bio.graph2;

/**
 * Какой сам ⚝
 * Author: Pavlenov Semen
 * Date: 16.01.14
 * <p>
 * E = mc^2
 */
public abstract class AbstractEdge<T, N> implements IBaseEdge<T, N> {

    protected int index;
    protected Status status = Status.VIRGIN;
    protected T data;
    protected N sourceNode;
    protected N targetNode;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public N getSourceNode() {
        return sourceNode;
    }

    public void setSourceNode(N node) {
        this.sourceNode = node;
    }

    public N getTargetNode() {
        return targetNode;
    }

    public void setTargetNode(N node) {
        this.targetNode = node;
    }
    @Override
    public N getOtherNode(N node) {

//        Dump.println("<" + sourceNode + " - " + sourceNode.equals(node) + " - " + node + ">");
//        Dump.println("<" + targetNode + " - " + targetNode.equals(node) + " - " + node + ">");
        N result = null;
        if (!sourceNode.equals(node)) result = sourceNode;
        if (!targetNode.equals(node)) result = targetNode;
        return result;
    }

    @Override
    public boolean contains(N node) {
        return (sourceNode == node || targetNode == node);
    }

    @Override
    public void passed() {
        status = Status.PASSED;
    }

    @Override
    public void virgin() {
        status = Status.VIRGIN;
    }

    @Override
    public boolean isVirgin() {
        return status == Status.VIRGIN;
    }

    @Override
    public boolean isPassed() {
        return status == Status.PASSED;
    }

    public String toString() {
        return "E("+ data + "|" + sourceNode + "," + targetNode + ")";
    }
}