package ru.pavlenov.bio.graph2;

/**
 * Какой сам ⚝
 * Author: Pavlenov Semen
 * Date: 16.01.14
 * <p>
 * E = mc^2
 */
public class UndirectedGraph<N, E extends IBaseEdge<?, N>> extends AbstractGraph<N, E> implements IUndirectedGraph<N, E> {

    @Override
    public int countOf(N node) {
        return nodes.get(node).size();
    }

    public String toString() {
        return this.getNodes().toString();
    }

}
