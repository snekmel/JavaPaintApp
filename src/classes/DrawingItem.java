package classes;

import java.awt.*;

public abstract class DrawingItem {

    private Color color;

    private DrawingItem previousState;


    public DrawingItem(Color color) {
        this.color = color;
    }

    public DrawingItem() {
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public DrawingItem getPreviousState() {
        return previousState;
    }

    public void setPreviousState(DrawingItem previousState) {
        this.previousState = previousState;
    }

    public Point getAnchor() {
        return null;
    }

    public double getWidth() {
        return 0;
    }

    public double getHeight() {
        return 0;
    }

    @Override
    public String toString() {
        return "DrawingItem{" +
                "color=" + color +
                ", previousState=" + previousState +
                '}';
    }
}
