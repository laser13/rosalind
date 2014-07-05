package ru.pavlenov.bio.graph2;

/**
 * Какой сам ⚝
 * Author: Pavlenov Semen
 * Date: 16.01.14
 * <p>
 * E = mc^2
 */
public interface IBaseEdge<T, N> {

    public enum Status { VIRGIN, PASSED }

    public Status getStatus();

    public void setStatus(Status status);

    public void passed();

    public void virgin();

    public boolean isVirgin();

    public boolean isPassed();

    public int getIndex();

    public void setIndex(int index);

    public T getData();

    public void setData(T data);

    public N getSourceNode();

    public void setSourceNode(N node);

    public N getTargetNode();

    public void setTargetNode(N node);

    public N getOtherNode(N node);

    public boolean contains(N node);

}
