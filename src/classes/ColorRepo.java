package classes;

import java.awt.*;
import javafx.scene.paint.Color;

public final class ColorRepo {
    private ColorRepo() {
    }


     public static Color ConvertEnumToColor(enums.Color kleur){

        if (kleur == enums.Color.BLACK){
            return Color.BLACK;
        }
        else if (kleur == enums.Color.WHITE){
            return Color.WHITE;
        }
        else if (kleur == enums.Color.RED){
            return Color.RED;
        }
        else if (kleur == enums.Color.BLUE){
            return Color.BLUE;
        }
        else if (kleur == enums.Color.GREEN){
            return Color.GREEN;
        }
        return Color.BLACK;


    }
}
