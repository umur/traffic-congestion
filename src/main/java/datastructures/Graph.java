package main.java.datastructures;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {

    private Map<Node, List<Node>> adjList;

    public Graph(){
        adjList = new HashMap<Node, List<Node>>();
    }

}
