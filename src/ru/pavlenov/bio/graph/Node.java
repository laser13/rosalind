package ru.pavlenov.bio.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Какой сам ☭
 * User: Павленов Семён
 * Date: 12.01.14 20:42
 */
public class Node {

    protected Integer index;
    protected Integer order;
    protected List<Edge> edgeList;

    public Node(int index) {
        setIndex(index);
        setOrder(index);
        edgeList = new ArrayList<>();
    }

    public Node(int index, int order) {
        setIndex(index);
        setOrder(order);
        edgeList = new ArrayList<>();
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

    public void setEdgeList(List<Edge> edgeList) {
        this.edgeList = edgeList;
    }

    public Node addEdge(Edge edge) {
        edgeList.add(edge);
        return this;
    }

    public List<Edge> getEdgeList() {
        return edgeList;
    }

    public List<Edge> getVirginEdgeList() {
        return getEdgeList(Edge.STATUS.VIRGIN);
    }

    public List<Edge> getPassedEdgeList() {
        return getEdgeList(Edge.STATUS.PASSED);
    }

    public List<Edge> getEdgeList(Edge.STATUS status) {
        List<Edge> edges = new ArrayList<>();
        for (Edge edge : edgeList) {
            if (edge.getStatus() == status) edges.add(edge);
        }
        return edges;
    }

    public Edge getEdge(Integer index) {
        return edgeList.get(index);
    }

    public boolean equals(Node node) {
        return this.index.equals(node.getIndex());
    }

    public int compareTo(Node node) {
        return this.order - node.getOrder();
    }

    public String toString() {
        return "node" + index + ": " + getVirginEdgeList();
    }

}
