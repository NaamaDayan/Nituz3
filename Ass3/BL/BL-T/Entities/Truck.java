package Entities;
import Trucks;


public class Truck {

    private String id;
    private TruckModel model;
    private String color;
    private int netoWeight;
    private int maxWeight;

    public Truck(String id, TruckModel model, String color, int netoWeight, int maxWeight) {
        this.id = id;
        this.model = model;
        this.color = color;
        this.netoWeight = netoWeight;
        this.maxWeight = maxWeight;
    }

    @Override
    public String toString() {
        return "Truck{" +
                "id ='" + id + '\'' +
                ", model ='" + model + '\'' +
                ", color ='" + color + '\'' +
                ", netoWeight =" + netoWeight +
                ", maxWeight =" + maxWeight +
                '}';
    }

    public String getId() {
        return id;
    }

    public TruckModel getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public int getNetoWeight() {
        return netoWeight;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public void setModel(TruckModel model) {
        this.model = model;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setNetoWeight(int netoWeight) {
        this.netoWeight = netoWeight;
    }

    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }
}
