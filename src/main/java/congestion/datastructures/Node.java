package congestion.datastructures;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Node {

    private NodeData nodeData;
    private List<Edge> edges;


    public Node(){
        this.edges = new ArrayList<>();
    }

    public Node(NodeData nodeData, List<Edge> edges) {
        this.nodeData = nodeData;
        this.edges = edges;
    }

    @Override
    public String toString() {
        return "Node{" +
                "name='" + nodeData.getName() + '\'' +
                '}';
    }

}
