package congestion;

import congestion.datastructures.Graph;
import congestion.datastructures.Node;
import congestion.datastructures.NodeData;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Graph g = new Graph();

        // root
        NodeData aData= new NodeData();
        aData.setName("A");

        Node a = new Node();
        a.setNodeData(aData);

        g.getAdjMap().put(a, new ArrayList<>());

        // B A->B
        NodeData b= new NodeData();
        b.setName("B");
        g.addNode("A",b,1);

        // C A->C
        NodeData c= new NodeData();
        c.setName("C");
        g.addNode("A",c,1);

        // D C->D
        NodeData d= new NodeData();
        d.setName("D");
        g.addNode("C",d,1);

        // E C->E
        NodeData e= new NodeData();
        e.setName("E");
        g.addNode("C",e,1);


        g.printPreOrder(a);
    }
}
