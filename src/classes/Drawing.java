package classes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Drawing extends DrawingItem implements Serializable  {

    private String name;

    private ArrayList<DrawingItem> items;

    private int Id;

    private transient ObservableList<DrawingItem> observableList;

    public Drawing(String name) {
        this.name = name;
        this.items = new ArrayList<>();
        observableList = FXCollections.observableList(new ArrayList<DrawingItem>());
    }

    public ObservableList<DrawingItem> itemsToObserve(){

        return FXCollections.unmodifiableObservableList(observableList);
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
      this.observableList.add(item);
    }

    public void RemoveItem(DrawingItem i){
        this.items.remove(i);
        this.observableList.remove(i);
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
