package ru.pavlenov.bio.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Какой сам ☭
 * User: Павленов Семён
 * Date: 12.01.14 20:36
 */
public class Graph {

    protected List<Node> nodeList;
    protected List<Edge> edgeList;

    public Graph() {
        nodeList = new ArrayList<>();
        edgeList = new ArrayList<>();
    }

    public Graph rebuild() {
        for (Node node : nodeList) {
            for (Edge edge : node.getEdgeList()) {
                if (!edgeList.contains(edge)) edgeList.add(edge);
            }
        }
        return this;
    }

    public List<Node> getNodeList() {
        return nodeList;
    }

    public List<Node> getNotEmptyNodeList() {
        List<Node> nodes = new ArrayList<>();
        for (Node node : nodeList) {
            if (!node.getVirginEdgeList().isEmpty()) nodes.add(node);
        }
        return nodes;
    }

    public List<Node> getEmptyNodeList() {
        List<Node> nodes = new ArrayList<>();
        for (Node node : nodeList) {
            if (!node.getPassedEdgeList().isEmpty()) nodes.add(node);
        }
        return nodes;
    }

    public List<Edge> getEdgeList() {
        return edgeList;
    }

    public void setNodeList(List<Node> nodeList) {
        this.nodeList = nodeList;
    }

    public Graph addNode(Node node) {
        if (!nodeList.contains(node)) nodeList.add(node);
        return this;
    }

    public Node getNode(Integer nodeIndex) {
        return nodeList.get(nodeIndex);
    }

}
