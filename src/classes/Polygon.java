package classes;

import java.awt.*;
import java.util.ArrayList;

public class Polygon extends DrawingItem {

    private ArrayList<Point> vertices;

    private double weight;
    private int aantalPoints;

    public Polygon(Color color, double weight, int aantalPoints) {
        super(color);
        this.vertices = new ArrayList<Point>();
        this.weight = weight;
        this.aantalPoints = aantalPoints;
    }

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

    public int getAantalPoints() {
        return aantalPoints;
    }

    public void setAantalPoints(int aantalPoints) {
        this.aantalPoints = aantalPoints;
    }

    @Override
    public String toString() {
        return "Polygon{" +
                "vertices=" + vertices +
                ", weight=" + weight +
                '}';
    }
}
