package main.java.datastructures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {

    private Map<Node, List<Node>> adjMap;

    public Graph() {
        adjMap = new HashMap<Node, List<Node>>();
    }


    /**
     * Add the edge to the graph.
     * Source of edge must exist in the graph.
     * @param e Edge
     */
    public void addNode(Edge e) {
        Node source = e.getSource();
        if (!adjMap.containsKey(source)) {
            System.err.println("Source does not exist !!! : " + e);
        } else {
            if (adjMap.get(source) == null) {
                var adj = adjMap.get(source);
                adj = new ArrayList<>();
            }
            adjMap.get(source).add(e.getDestination());
        }
    }


}
