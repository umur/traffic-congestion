package main.java.datastructures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {

    private Map<Node, List<Node>> adjMap;
    private int size = 0;

    public Graph() {
        adjMap = new HashMap<Node, List<Node>>();
    }


    public void add(String srcName, NodeData nodeData, int weight) {
        Node srcNode = adjMap.keySet().stream().filter(key ->
                        key.getNodeData()
                                .getName()
                                .equals(srcName))
                .findFirst()
                .orElse(null);
        if (srcNode == null) {
            System.err.println("Source does not exist !!! : " + srcNode);
        } else {
            Node destNode = new Node();
            destNode.setNodeData(nodeData);
            destNode.setEdges(new ArrayList<>());
            Edge e = new Edge(srcNode, destNode, weight);
            srcNode.getEdges().add(e);
            adjMap.putIfAbsent(destNode, new ArrayList<>());
        }

    }


    public void printPreOrder(Node root) {
        if (root.getEdges().size() == 0) {
            return;
        }
        for (int i = 0; i < root.getEdges().size(); i++) {
            System.out.println(root.getEdges().get(i));
            printPreOrder(root.getEdges().get(i).getDestination());
        }
    }

    public Map<Node, List<Node>> getAdjMap() {
        return adjMap;
    }

    public void setAdjMap(Map<Node, List<Node>> adjMap) {
        this.adjMap = adjMap;
    }
}
