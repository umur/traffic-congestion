package congestion.datastructures;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import congestion.model.CongestionEnum;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Edge {

    private Node source;
    private Node destination;
    private float weight;

    private int greenDuration;
    private int redDuration;
    private congestion.model.CongestionEnum congestion;

    public Edge(Node source, Node destination, float weight){
        greenDuration= 10;
        redDuration=10;
        congestion = CongestionEnum.RED;
        this.source=source;
        this.destination=destination;
        this.weight=weight;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "source=" + source +
                ", destination=" + destination +
                '}';
    }


}
