package classes;

import org.junit.jupiter.api.Test;

import java.io.Serializable;

import static org.junit.jupiter.api.Assertions.*;

class SerializationMediatorTest {
    @Test
    void load() {
        SerializationMediator sm = new SerializationMediator();
        System.out.println(sm.load("dFSDF"));

    }


    @Test
    void loadAll() {
        SerializationMediator sm = new SerializationMediator();
        sm.loadAll();

    }
}