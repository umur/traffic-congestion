package congestion.datastructures;

import main.java.model.CongestionEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {

    private Map<Node, List<Node>> adjMap;

    public Graph() {
        adjMap = new HashMap<Node, List<Node>>();
    }

    public void addEdge(Node source, Node destination) {
        if (adjMap.get(source) != null && adjMap.get(destination) != null) {
            adjMap.get(source).add(destination);
        }
    }

    public Node getNodeByName(String name) {
      return adjMap.keySet()
               .stream()
               .filter(l->l.getNodeData().getName().equals(name))
               .findFirst()
               .orElse(null);
    }

    /**
     * Creates a new node and connects it to the node specified with srcName.
     *
     * @param srcName
     * @param nodeData
     * @param weight
     */
    public void addNode(String srcName,
                        NodeData nodeData,
                        int weight,
                        int greenDuration,
                        int redDuration,
                        CongestionEnum congestion) {

        if (congestion == null) {
            congestion = CongestionEnum.RED;
        }
//
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
            Edge e = new Edge(srcNode, destNode, weight, greenDuration, redDuration, congestion);
            srcNode.getEdges().add(e);
            adjMap.putIfAbsent(destNode, new ArrayList<>());
        }
    }

    public void adjustDuration(Node node, main.java.model.CongestionEnum congestion, float weight) {
        Edge e = searchDfs(node, congestion, weight);
        if (e != null) {

        }
    }

    public Edge searchDfs(Node node, main.java.model.CongestionEnum congestion, float weight) {
        Edge temp = null;
        if (node.getEdges().size() == 0) return null;

        for (int i = 0; i < node.getEdges().size(); i++) {
            Edge e = node.getEdges().get(i);

            if (e.getCongestion().equals(congestion) &&
                    e.getWeight() > weight) {
                return e;
            }
            // return (e!=null) ? e : searchDfs(e.getDestination(), congestion, weight);
            temp = searchDfs(e.getDestination(), congestion, weight);
        }
        return temp;
    }


    /**
     * Traverse the graph in pre-order
     *
     * @param node
     */
    public void printPreOrder(Node node) {
        if (node.getEdges().size() == 0) return;

        for (Edge e : node.getEdges()) {
            System.out.println(e);
            printPreOrder(e.getDestination());
        }
    }


    public Map<Node, List<Node>> getAdjMap() {
        return adjMap;
    }

}
