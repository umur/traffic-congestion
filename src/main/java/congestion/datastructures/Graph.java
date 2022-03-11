package congestion.datastructures;

import congestion.model.CongestionEnum;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.PublicKey;
import java.util.*;

public class Graph {

    private Map<Node, List<Node>> adjMap;

    public Graph() {
        adjMap = new HashMap<Node, List<Node>>();
    }

    public void generateGraph() throws IOException {
        System.out.println("Working Directory = " + System.getProperty("user.dir"));

        BufferedReader reader = new BufferedReader(new FileReader("lights.csv"));
        String line = reader.readLine();
        int i=0;
        while (line != null) {
            String[] tokens = line.split(",");
            String name = tokens[0];
            String source = tokens[1];
            String destination = tokens[2];
            int weight = Integer.parseInt(tokens[3]);
            int green = Integer.parseInt(tokens[4]);
            int red = Integer.parseInt(tokens[5]);
            String congestion = tokens[6];
            CongestionEnum congestionEnum = CongestionEnum.GREEN;
            if (congestion.equalsIgnoreCase("RED")) {
                congestionEnum = CongestionEnum.RED;
            } else if (congestion.equalsIgnoreCase("YELLOW")) {
                congestionEnum = CongestionEnum.YELLOW;
            }

            // for the root
            if(i==0){
                //source
                NodeData sourceData = new NodeData(name);
                Node sourceNode = new Node(sourceData, new ArrayList<>());

                //destination
                NodeData destData = new NodeData(destination);
                Node destNode = new Node(destData, new ArrayList<>());

                Edge e = new Edge(sourceNode,destNode,weight);

                sourceNode.getEdges().add(e);
                List<Node> adjs = new ArrayList<>();
                adjs.add(destNode);
                adjMap.put(sourceNode,adjs);
                adjMap.put(destNode,new ArrayList<>());

                i++;
            }else{
                NodeData nodeData = new NodeData(destination);
                addNode(source,nodeData,weight,green,red,congestionEnum);
            }

            line = reader.readLine();
        }

    }

    public void addEdge(Node source,
                        Node destination,
                        float weight,
                        int greenDuration,
                        int redDuration,
                        CongestionEnum congestion) {

        if (adjMap.get(source) != null && adjMap.get(destination) != null) {
            adjMap.get(source).add(destination);
            Edge e = new Edge(source, destination, 0);
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
            return result;
        }

        for (int i = 0; i < node.getEdges().size(); i++) {
            Edge e = node.getEdges().get(i);
            e.getSource().setVisited(true);

            if (e.getCongestion().equals(congestion) &&
                    e.getWeight() > weight) {
                return e;
            }
            result = searchDfs(e.getDestination(), congestion, weight);
            return result;
        }
        return result;
    }

    public Edge searchBfs(Node node, congestion.model.CongestionEnum congestion, float weight) {
        Edge result = null;
        if (node.getEdges().size() == 0) return null;

        if (node.isVisited()) {
            return result;
        }
        Queue<Node> children = new ArrayDeque<Node>();
        children.addAll(adjMap.get(node));
        for (int i = 0; i < node.getEdges().size(); i++) {
            Edge e = node.getEdges().get(i);
            e.getSource().setVisited(true);

            System.out.println(e);
            if (e.getCongestion().equals(congestion) &&
                    e.getWeight() > weight) {
                return e;
            }
        }
        while (!children.isEmpty()) {
            result = searchBfs(children.remove(), congestion, weight);
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
        if (node.isVisited()) {
            return;
        }

        for (Edge e : node.getEdges()) {
            node.setVisited(true);
            System.out.println(e);
            printPreOrder(e.getDestination());
        }
    }


    public Map<Node, List<Node>> getAdjMap() {
        return adjMap;
    }

}
