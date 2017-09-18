package classes;

import java.util.ArrayList;

public class Drawing {

    private String name;

    private ArrayList<DrawingItem> items;

    public Drawing(String name) {
        this.name = name;
        this.items = new ArrayList<>();

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<DrawingItem> getItems() {
        return items;
    }

    public void AddDrawingItem(DrawingItem item) {
        this.items.add(item);
    }

    @Override
    public String toString() {
        return "Drawing{" +
                "name='" + name + '\'' +
                ", items=" + items +
                '}';
    }
}
