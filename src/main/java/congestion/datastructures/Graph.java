package congestion.datastructures;

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
     * Creates a new node and connects it to the node specified with srcName.
     * @param srcName
     * @param nodeData
     * @param weight
     */
    public void addNode(String srcName, NodeData nodeData, int weight) {
        Node srcNode = adjMap.keySet().stream().filter(key ->
                        key.getNodeData()
                                .getName()
                                .equals(srcName))
                .findFirst()
                .orElse(null);
        if (srcNode == null) {
            System.err.println("Source does not exist !!! : ");
        } else {
            Node destNode = new Node(nodeData, new ArrayList<>());
            Edge e = new Edge(srcNode, destNode, weight);
            srcNode.getEdges().add(e);
            adjMap.putIfAbsent(destNode, new ArrayList<>());
        }
    }

    /**
     *  Traverse the graph in pre-order
     * @param root Could be any node
     */
    public void printPreOrder(Node root) {
        if (root.getEdges().size() == 0) return;

        for (Edge e: root.getEdges()) {
            System.out.println(e);
            printPreOrder(e.getDestination());
        }
    }

    /**
     *
     * @param root Could be any node
     */
    public void searchBfs(Node root){

    }

    public Map<Node, List<Node>> getAdjMap() {
        return adjMap;
    }

}
