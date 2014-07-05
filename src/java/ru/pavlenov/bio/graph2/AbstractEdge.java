package ru.pavlenov.bio.graph2;

import lombok.Getter;
import lombok.Setter;
import ru.pavlenov.bio.utils.Dump;

/**
 * Какой сам ⚝
 * Author: Pavlenov Semen
 * Date: 16.01.14
 * <p>
 * E = mc^2
 */
public abstract class AbstractEdge<T, N> implements IBaseEdge<T, N> {

    @Getter
    @Setter
    protected int index;

    @Getter
    @Setter
    protected Status status = Status.VIRGIN;

    @Getter
    @Setter
    protected T data;

    @Getter
    @Setter
    protected N sourceNode;

    @Getter
    @Setter
    protected N targetNode;

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