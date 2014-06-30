package ru.pavlenov.bio.graph2;

import java.util.List;
import java.util.Set;

/**
 * Какой сам ⚝
 * Author: Pavlenov Semen
 * Date: 16.01.14
 * <p>
 * E = mc^2
 */
public interface IDirectedGraph<N, E extends IBaseEdge> extends IBaseGraph<N, E> {

    /**
     * Набор всех рёбер начинающихся в данной ноде
     *
     *
     * @param node
     * @return
     */
    public List<E> startOf(N node);

    /**
     * Набор всех рёбер заканчивающихся в данной ноде
     *
     *
     * @param node
     * @return
     */
    public List<E> finishOf(N node);

    public int countStartOf(N node);

    public int countFinishOf(N node);

}
