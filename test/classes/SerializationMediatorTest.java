package classes;

import org.junit.jupiter.api.Test;

import java.io.Serializable;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SerializationMediatorTest {
    @Test
    void load() {
        SerializationMediator sm = new SerializationMediator();
        Drawing d =sm.load("Test");
        System.out.println(d.getName());
        System.out.println(d.getItems().size());



    }


    @Test
    void loadAll() {
        SerializationMediator sm = new SerializationMediator();
       ArrayList<Drawing> lijst = sm.loadAll();

        System.out.println(lijst.get(0).getItems().size());
        }
}