package congestion.datastructures;

import congestion.model.CongestionEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {

    private Map<Node, List<Node>> adjMap;

    public Graph() {
        adjMap = new HashMap<Node, List<Node>>();
    }

    public void addEdge(Node source,
                        Node destination,
                        float weight,
                        int greenDuration,
                        int redDuration,
                        CongestionEnum congestion) {

        var a = adjMap.get(source);
        var b = adjMap.get(destination);
        if (adjMap.get(source) != null && adjMap.get(destination) != null) {
            adjMap.get(source).add(destination);
            Edge e = new Edge(source,destination,0);
            source.getEdges().add(e);
        }
    }

    public Node getNodeByName(String name) {
        return adjMap.keySet()
                .stream()
                .filter(l -> l.getNodeData().getName().equals(name))
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
    public void addNode(String srcName, // a
                        NodeData nodeData, // b
                        int weight,
                        int greenDuration,
                        int redDuration,
                        CongestionEnum congestion) {

        if (congestion == null) {
            congestion = CongestionEnum.RED;
        }

        Node srcNode = null;
        for (Node n : adjMap.keySet()) {
            if (n.getNodeData().getName().equals(srcName)) {
                srcNode = n;
                break;
            }
        }

        if (srcNode == null) {
            System.err.println("Source does not exist !!! : ");
        } else {
            Node destNode = new Node(nodeData, new ArrayList<>());
            Edge e = new Edge(srcNode, destNode, weight, greenDuration, redDuration, congestion);
            srcNode.getEdges().add(e);
            adjMap.get(srcNode).add(destNode);
            adjMap.putIfAbsent(destNode, new ArrayList<>());
        }
    }

    public void adjustDuration(Node node, congestion.model.CongestionEnum congestion, float weight) {
        Edge e = searchDfs(node, congestion, weight);
        if (e != null) {

        }
    }

    public Edge searchDfs(Node node, congestion.model.CongestionEnum congestion, float weight) {
        Edge result = null;
        if (node.getEdges().size() == 0) return null;

        if (node.isVisited()) {
            return null;
        }

        for (int i = 0; i < node.getEdges().size(); i++) {
            Edge e = node.getEdges().get(i);
//            if(e.getSource().isVisited()){
//                continue;
//            }
            e.getSource().setVisited(true);

            if (e.getCongestion().equals(congestion) &&
                    e.getWeight() > weight) {
                return e;
            }
            result = searchDfs(e.getDestination(), congestion, weight);
        }
        return result;
    }


    /**
     * Traverse the graph in pre-order
     *
     * @param node
     */
    public void printPreOrder(Node node) {
        if (node.getEdges().size() == 0) return;
//        if (node.isVisited()) {
//            return;
//        }

        for (Edge e : node.getEdges()) {
            node.setVisited(true);
             System.out.println(e);
           // System.out.println("+++++   " + adjMap);
            printPreOrder(e.getDestination());
        }
    }

    public void dispose() {

    }


    public Map<Node, List<Node>> getAdjMap() {
        return adjMap;
    }

}
