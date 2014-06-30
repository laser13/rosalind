package ru.pavlenov.bio.graph2;

import lombok.Getter;

import java.util.*;

/**
 * Какой сам ⚝
 * Author: Pavlenov Semen
 * Date: 16.01.14
 * <p>
 * E = mc^2
 */
public abstract class AbstractGraph<N, E extends IBaseEdge<?, N>> implements IBaseGraph<N, E> {

    @Getter
    protected List<E> edges;

    @Getter
    protected Map<N, List<E>> nodes;

    public AbstractGraph() {
        edges = new ArrayList<>();
        nodes = new HashMap<>();
    }

    public AbstractGraph(int nodeSize) {
        edges = new ArrayList<>();
        nodes = new HashMap<>();
    }

    @Override
    public boolean addNode(N node) {

        if (!nodes.containsKey(node)) {
            nodes.put(node, new ArrayList<E>());
        }

        return true;
    }

    public boolean addNode(N node, E edge) {

        if (nodes.containsKey(node)) {
            nodes.get(node).add(edge);
        }
        else {
            ArrayList<E> list = new ArrayList<>();
            list.add(edge);
            nodes.put(node, list);
        }

        return true;
    }

    @Override
    public boolean addEdge(E edge) {
        return edges.add(edge);
    }

    @Override
    public boolean addEdge(N sourceNode, N targetNode) {

        return false;
    }

    @Override
    public boolean addEdge(N sourceNode, N targetNode, E edge) {

        edge.setIndex(edges.size());
        edge.setTargetNode(targetNode);
        edge.setSourceNode(sourceNode);

        addNode(sourceNode, edge);
        addNode(targetNode, edge);

        return addEdge(edge);
    }

    @Override
    public Set<E> edgesOf(N node) {
        Set<E> result = new HashSet<>();
        for (E edge: edges) {
            if (edge.contains(node)) result.add(edge);
        }
        return result;
    }

    @Override
    public E edgeOf(N node) {
        E result = null;
        if (nodes.get(node) != null) {
            for (E edge : nodes.get(node)) {
                if (edge.isVirgin()) {
                    result = edge;
                    break;
                }
            }
        }
        return result;
    }

    @Override
    public E edgeOfAndPassed(N node) {
        E edge = edgeOf(node);
        edgePassed(edge);
        return edge;
    }

    @Override
    public void edgePassed(E edge) {

        if (edge == null) return;

        // Удаляем пройденыый путь у соответствующих вершин
        nodes.get(edge.getSourceNode()).remove(edge);
        if (nodes.get(edge.getSourceNode()).isEmpty()) nodes.remove(edge.getSourceNode());

        nodes.get(edge.getTargetNode()).remove(edge);
        if (nodes.get(edge.getTargetNode()).isEmpty()) nodes.remove(edge.getTargetNode());

        // Помечаем вершину как пройденную
        edge.passed();
    }

    @Override
    public N getEdgeSource(E edge) {
        return edge.getSourceNode();
    }

    @Override
    public N getEdgeTarget(E edge) {
        return edge.getTargetNode();
    }

    @Override
    public boolean removeAllEdges(Collection<? extends E> edges) {
        boolean modified = false;
        for (E edge : edges) {
            modified |= removeEdge(edge);
        }
        return modified;
    }

    @Override
    public Set<E> removeAllEdges(N sourceNode, N targetNode) {
        Set<E> deletedEdges = new HashSet<>();
        for (E edge : edges) {
            if (edge.contains(sourceNode) && edge.contains(targetNode)) {
                deletedEdges.add(edge);
            }
        }
        return deletedEdges;
    }

    @Override
    public boolean removeAllNodes(Collection<? extends N> nodes) {
        boolean modified = false;
        for (N node : nodes) {
            modified |= removeNode(node);
        }
        return modified;
    }

    @Override
    public E removeEdge(N sourceNode, N targetNode) {
        E deletedEdge = null;
        for (E edge : edges) {
            if (edge.contains(sourceNode) && edge.contains(targetNode)) {
                deletedEdge = edge;
                break;
            }
        }
        return deletedEdge;
    }

    @Override
    public boolean removeEdge(E edge) {
        nodes.get(edge.getSourceNode()).remove(edge);
        nodes.get(edge.getTargetNode()).remove(edge);
        return edges.remove(edge);
    }

    @Override
    public boolean removeNode(N node) {
        nodes.remove(node);
        return true;
    }

    @Override
    public double getEdgeWeight(E edge) {
        return 0;
    }

    @Override
    // @todo
    public Integer countSub() {
        int countNodes = nodes.size();
        return 0;
    }
}
