package interfaces;

import classes.Image;
import classes.Oval;
import classes.PaintedText;
import classes.Polygon;

public interface IPaintable
{
    void Paint(Oval o);
    void Paint(Polygon p);
    void Paint(PaintedText t);
    void Paint(Image i);
}
