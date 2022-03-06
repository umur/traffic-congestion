package congestion.datastructures;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
public class Node {

    private NodeData nodeData;
    private List<Edge> edges;

    private boolean visited;

    public Node(){
        visited = false;
        this.edges = new ArrayList<>();
    }

    public Node(NodeData nodeData, List<Edge> edges) {
        this.nodeData = nodeData;
        this.edges = edges;
    }

    public boolean equals(Object another){
        Node a = (Node) another;
        return a.getNodeData().getName().equals(this.getNodeData().getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodeData.getName());
    }

    @Override
    public String toString() {
        return "Node{" +
                "name='" + nodeData.getName() + '\'' +
                '}';
    }

}
