package classes;

import java.io.Serializable;
import java.util.ArrayList;

public class Drawing extends DrawingItem implements Serializable  {

    private String name;

    private ArrayList<DrawingItem> items;

    private int Id;

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

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    @Override
    public String toString() {
        return name;
    }
}
