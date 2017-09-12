package classes;

import interfaces.IPaintable;

import javafx.scene.canvas.GraphicsContext;
import java.awt.*;
import static javafx.scene.paint.Color.BLACK;


public class JavaFxPaintable implements IPaintable {

    private javafx.scene.canvas.GraphicsContext graphics;

    public JavaFxPaintable(javafx.scene.canvas.GraphicsContext graphics) {
        this.graphics = graphics;
    }

    public void Paint(Oval o) {
    graphics.fillOval(o.getAnchor().getX(), o.getAnchor().getY(), o.getWidth(), o.getHeight());
    }

    @Override
    public void Paint(Polygon p) {


    }

    @Override
    public void Paint(PaintedText t) {
    graphics.fillText(t.getContent(), t.getAnchor().getX(), t.getAnchor().getY());
    }

    @Override
    public void Paint(Image i) {


    }
}
