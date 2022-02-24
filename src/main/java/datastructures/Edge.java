package main.java.datastructures;

public class Edge {

    private Node source;
    private Node destination;
    private float weight;

    public Edge() {

    }

    public Edge(Node source, Node destination, float weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

}
