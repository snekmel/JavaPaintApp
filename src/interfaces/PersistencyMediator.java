package interfaces;

import classes.Drawing;

import java.util.ArrayList;
import java.util.Properties;

public interface PersistencyMediator {

    Drawing load(String nameDrawing);
    ArrayList<Drawing> loadAll();
    boolean save(Drawing drawing);
    boolean init(Properties props);

}
