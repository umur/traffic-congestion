package congestion.datastructures;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import main.java.model.CongestionEnum;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NodeData {

    private int numberOfLights;

    private int greenDuration;
    private int redDuration;

    private String name;
    private CongestionEnum congestion;


}
