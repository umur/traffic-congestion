package congestion.datastructures;

import java.util.ArrayList;
import java.util.List;

public class Node {

    private NodeData nodeData;
    private List<Edge> edges;


    public Node(){
        this.edges = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Node{" +
                "name='" + nodeData.getName() + '\'' +
                '}';
    }

    public NodeData getNodeData() {
        return nodeData;
    }

    public void setNodeData(NodeData nodeData) {
        this.nodeData = nodeData;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }
}
