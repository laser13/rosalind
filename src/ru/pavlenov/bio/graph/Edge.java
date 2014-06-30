package ru.pavlenov.bio.graph;

/**
 * Какой сам ☭
 * User: Павленов Семён
 * Date: 12.01.14 20:42
 */
public class Edge<E> {

    protected E value;
    protected STATUS status = STATUS.VIRGIN;

    protected Integer index;
    protected Integer order;

    protected Node toNode;
    protected Node fromNode;

    public enum STATUS {VIRGIN, PASSED}

    public Edge(Integer index) {
        setIndex(index);
        setOrder(index);
    }

    public Edge(Integer index, Node fromNode, Node toNode) {
        setIndex(index);
        setOrder(index);
        setFromNode(fromNode);
        setToNode(toNode);
    }

    public void setValue(E value) {
        this.value = value;
    }

    public E getValue() {
        return value;
    }

    public Node getToNode() {
        return toNode;
    }

    public Node getFromNode() {
        return fromNode;
    }

    public void setToNode(Node node) {
        toNode = node;
        toNode.addEdge(this);
    }

    public void setToNode(Integer nodeIndex) {

    }

    public void setFromNode(Node node) {
        fromNode = node;
        fromNode.addEdge(this);
    }

    public void setFromNode(Integer nodeIndex) {

    }

    public STATUS getStatus() {
        return status;
    }

    public void passed() {
        status = STATUS.PASSED;
    }

    public void clear() {
        status = STATUS.VIRGIN;
    }

    public boolean equals(Edge edge) {
        return this.index.equals(edge.getIndex());
    }

    public int compareTo(Edge edge) {
        return this.order - edge.getOrder();
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String toString() {
        return "edge" + index + "(" + value + ")={" + fromNode.getIndex() + "," + toNode.getIndex() + "}";
    }
}
