package ru.pavlenov.bio.graph2;

/**
 * Какой сам ⚝
 * Author: Pavlenov Semen
 * Date: 16.01.14
 * <p>
 * E = mc^2
 */
public interface IUndirectedGraph<N, E extends IBaseEdge> extends IBaseGraph<N, E> {

    /**
     * Кол-во рёбер исходящих из данной вершины
     *
     * @param node
     * @return
     */
    public int countOf(N node);

}
