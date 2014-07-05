package ru.pavlenov.bio.assemble;

import ru.pavlenov.bio.utils.Dump;
import ru.pavlenov.bio.utils.Random;

import java.util.ArrayList;

/**
 * Created by semen on 05.12.13.
 */
public class Node {

    protected Integer index;
    protected String value;

    public Node(Integer index) {
        this.index = index;
    }

    public Node(Integer index, String value) {
        this.index = index;
        this.value = value;
    }

    public Integer getIndex() {
        return index;
    }

    public String getValue() {
        return value;
    }

    public String toString() {
        return String.valueOf(index);
    }

    public int compareTo(Node node) {
        return this.index - node.getIndex();
    }

    public boolean equals(Node node) {
        return this.index.equals(node.getIndex());
    }

}
