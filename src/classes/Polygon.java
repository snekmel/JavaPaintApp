package classes;

import java.awt.*;
import java.util.ArrayList;

public class Polygon  extends DrawingItem{

    private ArrayList<Point> vertices;
    private double weight;

    public ArrayList<Point> getVertices() {
        return vertices;
    }

    public void addVertices(Point vertices) {
        this.vertices.add(vertices);
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Polygon(Color color, double weight) {
        super(color);
        this.vertices = new ArrayList<Point>();
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Polygon{" +
                "vertices=" + vertices +
                ", weight=" + weight +
                '}';
    }
}
