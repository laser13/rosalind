package ru.pavlenov.bio.graph2;

import java.util.ArrayList;
import java.util.List;

/**
 * Какой сам ⚝
 * Author: Pavlenov Semen
 * Date: 17.01.14
 * <p>
 * E = mc^2
 */
public class DirectedGraph<N, E extends IBaseEdge<?, N>> extends AbstractGraph<N, E> implements IDirectedGraph<N, E> {

    public DirectedGraph(int nodeSize) {
        super(nodeSize);
    }

    @Override
    public List<E> startOf(N node) {
        List<E> result = new ArrayList<>();
        for (E edge : edges) {
            if (node.equals(edge.getSourceNode())) result.add(edge);
        }
        return result;
    }

    @Override
    public List<E> finishOf(N node) {
        List<E> result = new ArrayList<>();
        for (E edge : edges) {
            if (node.equals(edge.getTargetNode())) result.add(edge);
        }
        return result;
    }

    @Override
    public int countStartOf(N node) {
        return 0;
    }

    @Override
    public int countFinishOf(N node) {
        return 0;
    }

}
