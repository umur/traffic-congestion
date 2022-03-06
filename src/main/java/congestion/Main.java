package congestion;

import congestion.datastructures.Graph;
import congestion.datastructures.Node;
import congestion.datastructures.NodeData;
import congestion.model.CongestionEnum;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Graph g = new Graph();

        // root
        NodeData aData = new NodeData();
        aData.setName("A");

        Node a = new Node();
        a.setNodeData(aData);

        g.getAdjMap().put(a, new ArrayList<>());

        // B A->B
        NodeData b = new NodeData();
        b.setName("B");
        g.addNode("A", b, 1, 10, 10, null);

        // C A->C
        NodeData c = new NodeData();
        c.setName("C");
        g.addNode("A", c, 1, 10, 10, null);

        // D C->D
        NodeData d = new NodeData();
        d.setName("D");
        g.addNode("C", d, 10, 10, 10, CongestionEnum.GREEN);

        NodeData gg = new NodeData();
        gg.setName("G");
        g.addNode("D", gg, 10, 10, 10, null);
        g.addEdge(g.getNodeByName("G"),g.getNodeByName("B"));
        g.addEdge(g.getNodeByName("D"),g.getNodeByName("G"));

        // E C->E
        NodeData e = new NodeData();
        e.setName("E");
        g.addNode("C", e, 1, 10, 10, null);


        g.printPreOrder(a);

//        Edge res = g.searchDfs(a, CongestionEnum.GREEN, -5);
//        System.out.println(res);

    }
}
