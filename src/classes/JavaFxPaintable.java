package classes;

import interfaces.IPaintable;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.awt.*;
import java.util.ArrayList;


public class JavaFxPaintable implements IPaintable {

    private javafx.scene.canvas.GraphicsContext graphics;

    public JavaFxPaintable(javafx.scene.canvas.GraphicsContext graphics) {
        this.graphics = graphics;
    }

    public void Paint(Oval o) {


         graphics.setFill(o.getColor());

        graphics.fillOval(o.getAnchor().getX(), o.getAnchor().getY(), o.getWidth(), o.getHeight());
    }

    @Override
    public void Paint(Polygon p) {

        double[] xPoints = new double[p.getVertices().size()];
        double[] yPoints = new double[p.getVertices().size()];

        ArrayList<Point> points = p.getVertices();

        for (int i = 0; i < p.getVertices().size(); i++) {

            xPoints[i] = points.get(i).getX();
            yPoints[i] = points.get(i).getY();

        }
        graphics.setFill(p.getColor());
        graphics.fillPolygon(xPoints, yPoints, p.getAantalPoints());

    }

    @Override
    public void Paint(PaintedText t) {


        graphics.setFill(t.getColor());
        javafx.scene.text.Font f = new Font(t.getFontName(),t.getHeight());
        graphics.setFont(f);
        graphics.fillText(t.getContent(), t.getAnchor().getX(), t.getAnchor().getY());
    }

    @Override
    public void Paint(Image i) {
        System.out.println(i);
        try {
            graphics.drawImage(new javafx.scene.image.Image(i.getFile().toURL().toString(), i.getWidth(), i.getHeight(), false, false, false), i.getAnchor().getX(), i.getAnchor().getY());

        } catch (Exception e) {

        }

    }
}
