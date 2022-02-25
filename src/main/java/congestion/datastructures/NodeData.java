package main.java.datastructures;

import main.java.model.CongestionEnum;

public class NodeData {

    private int numberOfLights;

    private int greenDuration;
    private int redDuration;

    private String name;
    private CongestionEnum congestion;

    public int getNumberOfLights() {
        return numberOfLights;
    }

    public void setNumberOfLights(int numberOfLights) {
        this.numberOfLights = numberOfLights;
    }

    public int getGreenDuration() {
        return greenDuration;
    }

    public void setGreenDuration(int greenDuration) {
        this.greenDuration = greenDuration;
    }

    public int getRedDuration() {
        return redDuration;
    }

    public void setRedDuration(int redDuration) {
        this.redDuration = redDuration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CongestionEnum getCongestion() {
        return congestion;
    }

    public void setCongestion(CongestionEnum congestion) {
        this.congestion = congestion;
    }
}
