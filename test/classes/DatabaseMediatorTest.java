package classes;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseMediatorTest {
    @org.junit.jupiter.api.Test
    void LoadPaintedText() {
        DatabaseMediator dm = new DatabaseMediator();
       ArrayList<PaintedText> lijst = dm.loadTextById(12);
        System.out.println(lijst.size());
    }


    @org.junit.jupiter.api.Test
    void LoadDrawings() {
        DatabaseMediator dm = new DatabaseMediator();
        ArrayList<Drawing> lijst = dm.loadAll();

    }


}