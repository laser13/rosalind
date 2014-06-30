package ru.pavlenov.bio.assemble;

import ru.pavlenov.bio.utils.Dump;
import ru.pavlenov.bio.utils.Random;
import ru.pavlenov.bio.utils.Sort;

import java.util.*;

/**
 * Created by semen on 04.12.13.
 */
public class Graph {

    public static class Euler {
    }

    public static class Directed {

        public static class Node extends ru.pavlenov.bio.assemble.Node {

            private ArrayList<Integer> inEdgeList;
            private ArrayList<Integer> outEdgeList;
            private Boolean isNonBranching = null;

            public Node(Integer index) {
                super(index);
                this.outEdgeList = new ArrayList<>();
                this.inEdgeList = new ArrayList<>();
            }

            public Node(Integer index, ArrayList<Integer> out) {
                super(index);
                this.outEdgeList = out;
                this.inEdgeList = new ArrayList<>();
            }

            public ArrayList<Integer> getOut() {
                return outEdgeList;
            }

            public void addOutEdge(int out) {
                outEdgeList.add(out);
            }

            public void removeOutEdge(int indexOut) {
                outEdgeList.remove(indexOut);
            }

            public void removeOutEdge(Object out) {
                this.outEdgeList.remove(out);
            }

            public Integer getRndOut() {
                return outEdgeList.get(Random.range(0, outEdgeList.size()));
            }

            public Integer getOutEdge(int indexOut) {
                return outEdgeList.get(indexOut);
            }

            public Integer getOutAndRemove(int indexOut) {
                Integer result = outEdgeList.get(indexOut);
                outEdgeList.remove(indexOut);
                return result;
            }

            public ArrayList<Integer> getInEdgeList() {
                return inEdgeList;
            }

            public void setInEdgeList(ArrayList<Integer> inEdgeList) {
                this.inEdgeList = inEdgeList;
            }

            public ArrayList<Integer> getOutEdgeList() {
                return outEdgeList;
            }

            public String toString(boolean full) {
                return inEdgeList + " -> { " + index + " } -> " + outEdgeList;
            }

            public String toString() {
                return inEdgeList + "→{" + index + "}→" + outEdgeList;
            }

            public boolean isBalanced() {
                return edgeDiff() == 0;
            }

            public void calcBranch() {
                isNonBranching = outEdgeList.size() == 1 && inEdgeList.size() == 1;
            }

            public boolean isNonBranching() {
                return isNonBranching;
            }

            public int edgeDiff() {
                return outEdgeList.size() - inEdgeList.size();
            }

        }

        private HashMap<Integer, Node> nodeList;
        private Integer power = 0;

        public Directed() {
            nodeList = new HashMap<>();
        }

        public Directed(HashMap<Integer, Node> nodeList) {
            this.nodeList = nodeList;
        }

        public void buildFrom(HashMap<String, ArrayList<String>> graph, String[] sortedArray) {

            for (Map.Entry<String, ArrayList<String>> pair : graph.entrySet()) {
                Node node = new Node(Arrays.binarySearch(sortedArray, pair.getKey()));
                for (String link: pair.getValue()) {
                    node.addOutEdge(Arrays.binarySearch(sortedArray, link));
                }
                addNode(node);
            }

            for (int i = 0; i < sortedArray.length; i++){
                if (!nodeList.containsKey(i)) {
                    addNode(new Node(i));
                }
            }

            build();

        }

        public void build() {
            for (Map.Entry<Integer, Node> pair : nodeList.entrySet()) {
                power += pair.getValue().getOutEdgeList().size();
                for (Integer index : pair.getValue().getOutEdgeList()) {
                    Node node = nodeList.get(index);
//                    if (!node.getInEdgeList().contains(pair.getKey())) {
                        node.getInEdgeList().add(pair.getKey());
//                    }
                }
            }
            for (Map.Entry<Integer, Node> pair : nodeList.entrySet()) {
                pair.getValue().calcBranch();
            }
        }

        public Integer getPower() {
            return power;
        }

        public Integer calcPower() {
            power = 0;
            for (Map.Entry<Integer, Node> pair : nodeList.entrySet()) {
                power += pair.getValue().getOutEdgeList().size();
            }
            return power;
        }

        public void addNode(Node node) {
            nodeList.put(node.getIndex(), node);
        }

        public boolean containsKey(Integer key) {
            return nodeList.containsKey(key);
        }

        public Node get(Integer index) {
            return nodeList.get(index);
        }

        public void removeEdge(int from, int to) {
            nodeList.get(from).getOutEdgeList().remove((Object) to);
            nodeList.get(to).getInEdgeList().remove((Object) from);
        }

        public Node walk(int from, int outIndex) {

            Node fromNode = nodeList.get(from);
            Node toNode = nodeList.get(fromNode.getOutAndRemove(outIndex));
            toNode.getInEdgeList().remove((Object) from);
            return toNode;
        }

        public String toString() {
            return nodeList.toString();
        }

        public void sort() {
        }

        public ArrayList<Node> findUnbalanced() {
            ArrayList<Node> result = new ArrayList<>();
            for (Map.Entry<Integer, Node> pair : nodeList.entrySet()) {
                if (!pair.getValue().isBalanced()) {
                    result.add(pair.getValue());
                }
            }
            return result;
        }

        public ArrayList<Node> findStarted() {
            ArrayList<Node> result = new ArrayList<>();
            for (Map.Entry<Integer, Node> pair : nodeList.entrySet()) {
                if (!pair.getValue().isNonBranching() && !pair.getValue().getOutEdgeList().isEmpty()) {
                    result.add(pair.getValue());
                }
            }
            return result;
        }

        public ArrayList<Node> findOutNotEmpty() {
            ArrayList<Node> result = new ArrayList<>();
            for (Map.Entry<Integer, Node> pair : nodeList.entrySet()) {
                if (!pair.getValue().getOutEdgeList().isEmpty()) {
                    result.add(pair.getValue());
                }
            }
            return result;
        }

    }

    public static class DirectedWithWeight {

        public static class Node extends ru.pavlenov.bio.assemble.Node {

            private HashMap<Integer, Integer> inEdgeList;
            private HashMap<Integer, Integer> outEdgeList;
            private Integer[] maxPrice = new Integer[]{null,0};
            private HashMap<Integer, Integer> priceList;

            public Node(Integer index) {
                super(index);
                this.outEdgeList = new HashMap<>();
                this.inEdgeList = new HashMap<>();
                this.priceList = new HashMap<>();
            }

            public Node(Integer index, HashMap<Integer, Integer> out) {
                super(index);
                this.outEdgeList = out;
                this.inEdgeList = new HashMap<>();
                this.priceList = new HashMap<>();
            }

            public HashMap<Integer, Integer> getInEdgeList() {
                return inEdgeList;
            }

            public HashMap<Integer, Integer> getOutEdgeList() {
                return outEdgeList;
            }

            public HashMap<Integer, Integer> getPriceList() {
                return priceList;
            }

            public String toString() {
                return inEdgeList + "→{" + index + "←" + maxPrice[0] + ":" + maxPrice[1] + "}→" + outEdgeList;
            }

            public Integer[] getMaxPrice() {
                return maxPrice;
            }

            public void setMaxPrice(Integer maxPrice[]) {
                this.maxPrice = maxPrice;
            }
        }

        private HashMap<Integer, Node> nodeList;
        private ArrayList<Integer> topologicalSort;
        private Integer power = 0;

        public DirectedWithWeight() {
            nodeList = new HashMap<>();
            topologicalSort = new ArrayList<>();
        }

        public DirectedWithWeight(HashMap<Integer, Node> nodeList) {
            this.nodeList = nodeList;
            topologicalSort = new ArrayList<>();
        }

        public void setTopologicalSort(ArrayList<Integer> topologicalSort) {
            this.topologicalSort = topologicalSort;
        }

        public void addNode(Node node) {
            nodeList.put(node.getIndex(), node);
        }

        public Node getNode(int index) {
            return nodeList.get(index);
        }

        public HashMap<Integer, Node> getNodeList() {
            return nodeList;
        }

        public boolean containsKey(Integer key) {
            return nodeList.containsKey(key);
        }

        public void build() {

            HashMap<Integer, Node> tmp = new HashMap<>();

            for (Map.Entry<Integer, Node> pair : nodeList.entrySet()) {
                power += pair.getValue().getOutEdgeList().size();
                for (Map.Entry<Integer, Integer> outPair : pair.getValue().getOutEdgeList().entrySet()) {

                    if (nodeList.containsKey(outPair.getKey())) {
                        Node node = nodeList.get(outPair.getKey());
                        node.getInEdgeList().put(pair.getKey(), outPair.getValue());
                    }
                    else {
                        Node node = new Node(outPair.getKey());
                        node.getInEdgeList().put(pair.getKey(), outPair.getValue());
                        tmp.put(node.getIndex(), node);
                    }

                }
            }

            if (!tmp.isEmpty()) {
                nodeList.putAll(tmp);
            }

        }

        // http://rain.ifmo.ru/cat/view.php/vis/graph-general/topological-sort-2007/algorithm
        public ArrayList<Integer> topologicalSort() {

            HashMap<Integer, Node> graph = new HashMap<>(this.nodeList);

            List<Node> candidates = new ArrayList<>();

            for (Map.Entry<Integer, Node> pair : graph.entrySet()) {
                if (pair.getValue().getInEdgeList().isEmpty()) {
                    candidates.add(pair.getValue());
                }
            }

            while (!candidates.isEmpty()){

                Node node = candidates.get(0);
                candidates.remove(0);
                topologicalSort.add(node.getIndex());

                for (Map.Entry<Integer, Integer> pair : node.getOutEdgeList().entrySet()) {

//                    graph.get(node.getIndex()).getOutEdgeList().remove((Object) pair.getKey());
                    graph.get(pair.getKey()).getInEdgeList().remove((Object) node.getIndex());

                    if (graph.get(pair.getKey()).getInEdgeList().isEmpty()) {
                        candidates.add(graph.get(pair.getKey()));
                    }

                }

            }

            return topologicalSort;
        }

        public void calcPrice() {

            for (Integer index : topologicalSort) {

                Node node = nodeList.get(index);
//                Dump.println(node);

                for (Map.Entry<Integer, Integer> pair : node.getOutEdgeList().entrySet()) {

                    Node currNode = nodeList.get(pair.getKey());
                    Integer currPrice = node.getMaxPrice()[1] + pair.getValue();

                    currNode.getPriceList().put(index, currPrice);

                    if (currNode.getMaxPrice()[1] < currPrice) {
                        currNode.setMaxPrice(new Integer[]{index, currPrice});
                    }

//                    nodeList.get(pair.getKey()).setMaxPrice(sortedMap.get(0));

                }


            }

        }

        public ArrayList<Integer> backtrack(int index) {

            ArrayList<Integer> result = new ArrayList<>();
            result.add(index);
            backtrack(result, index);
            return result;

        }

        private void backtrack(ArrayList<Integer> result, int index) {

            Integer[] track = nodeList.get(index).getMaxPrice();
            if (track[0] != null) {
                result.add(track[0]);
                backtrack(result, track[0]);
            }
        }

        public String toString() {
            return nodeList.toString();
        }



    }

}
