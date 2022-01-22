package sample;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class PaletteViewerFactory {
    //private Group group;

    public static Group getView(ArrayList<Color> colors){
        Group group = new Group();
        double radius = 4;
        double i = 0;
        Collections.sort(colors, new Comparator<Color>() {
            @Override
            public int compare(Color o1, Color o2) {
                return (int)o1.getHue()-(int)o2.getHue();
            }
        });
        for(Color color : colors){
            Circle circle;
            if(i<200)
                circle = new Circle(radius*1.5f*i,0,radius);
            else
                circle = new Circle(radius*1.5f*(i-200),10,radius);
            circle.setFill(color);
            group.getChildren().add(circle);
            i++;
        }
        return group;
    }
}
