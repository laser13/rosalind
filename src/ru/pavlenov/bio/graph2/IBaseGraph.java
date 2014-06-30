package ru.pavlenov.bio.graph2;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Какой сам ⚝
 * Author: Pavlenov Semen
 * Date: 16.01.14
 * <p>
 * E = mc^2
 */
public interface IBaseGraph<N, E extends IBaseEdge> {

    public Map<N,List<E>> getNodes();

    public List<E> getEdges();

    public boolean addNode(N node);

    public boolean addEdge(E edge);

    public boolean addEdge(N sourceNode, N targetNode);

    public boolean addEdge(N sourceNode, N targetNode, E edge);

    /**
     * Отдаёт все ребра связанные с данной вершиной
     *
     * @param node
     * @return
     */
    public Set<E> edgesOf(N node);

    public E edgeOf(N node);

    public E edgeOfAndPassed(N node);

    public void edgePassed(E edge);

    public N getEdgeSource(E edge);

    public N getEdgeTarget(E edge);

    public boolean removeAllEdges(Collection<? extends E> edges);

    public Set<E> removeAllEdges(N sourceNode, N targetNode);

    public boolean removeAllNodes(Collection<? extends N> nodes);

    public E removeEdge(N sourceNode, N targetNode);

    public boolean removeEdge(E edge);

    public boolean removeNode(N node);

    public double getEdgeWeight(E edge);

    /**
     * Считаем сколько у нас отдельных подграфов
     * т.е. несвязанных между собой
     *
     * @return
     */
    public Integer countSub();

}
