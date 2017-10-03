package classes;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseMediatorTest {
    @org.junit.jupiter.api.Test
    void LoadPaintedText() {
        DatabaseMediator dm = new DatabaseMediator();

    }


    @org.junit.jupiter.api.Test
    void LoadDrawings() {
        DatabaseMediator dm = new DatabaseMediator();
       ArrayList<Drawing> d = dm.loadAll();
        System.out.println(d.get(0).getName() + d.get(1).getName());
    }


}