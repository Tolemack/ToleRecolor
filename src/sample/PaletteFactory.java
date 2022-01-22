package sample;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class PaletteFactory {
    public static ArrayList<Color> getPaletteFromImage(Image image){
        int imageWidth = (int)image.getWidth();
        int imageHeight = (int)image.getHeight();

        ArrayList<Color> result = new ArrayList<>();
        PixelReader imagePixelReader = image.getPixelReader();
        for(int y=0; y<imageHeight;y++){
            for(int x=0; x<imageWidth; x++){
                Color color = imagePixelReader.getColor(x,y);
                if(!result.contains(color)){
                    result.add(color);
                }
            }
        }
        return result;
    }

    public static ArrayList<Color> getRandomPalette(int nbColors){
        ArrayList<Color> result = new ArrayList<>();
        Random rand = new Random();
        for(int i=0;i<nbColors;i++){
            result.add(
                new Color(
                    rand.nextFloat(),
                    rand.nextFloat(),
                    rand.nextFloat(),1
                )
            );
        }
        return result;
    }

    public static ArrayList<Color> getTolemackRgbTooVivid(int couleurParTeinte){
        ArrayList<Color> result = new ArrayList<>();
        Random rand = new Random();
        for(int i=0;i<couleurParTeinte;i++){
            //rouges
            result.add(
                    new Color(
                            (rand.nextFloat()+1)/2,
                            rand.nextFloat()/2,
                            rand.nextFloat()/2,
                            1
                    )
            );
            //verts
            result.add(
                    new Color(
                            rand.nextFloat()/2,
                            (rand.nextFloat()+1)/2,
                            rand.nextFloat()/2,
                            1
                    )
            );
            //bleus
            result.add(
                    new Color(
                            rand.nextFloat()/2,
                            rand.nextFloat()/2,
                            (rand.nextFloat()+1)/2,
                            1
                    )
            );
        }
        return result;
    }

    public static ArrayList<Color> getTolemackRgb(int couleurParTeinte){
        ArrayList<Color> result = new ArrayList<>();
        ArrayList<Float> values = new ArrayList<>();
        Random rand = new Random();
        for(int i=0;i<couleurParTeinte;i++){
            values.add(rand.nextFloat());
            values.add(rand.nextFloat());
            values.add(rand.nextFloat());
            Collections.sort(values);
            result.add(
                    new Color(values.get(2),values.get(1), values.get(0),1)
            );
            values.clear();
            values.add(rand.nextFloat());
            values.add(rand.nextFloat());
            values.add(rand.nextFloat());
            Collections.sort(values);
            result.add(
                    new Color(values.get(0),values.get(2), values.get(1),1)
            );
            values.clear();
            values.add(rand.nextFloat());
            values.add(rand.nextFloat());
            values.add(rand.nextFloat());
            Collections.sort(values);
            result.add(
                    new Color(values.get(1),values.get(0), values.get(2),1)
            );
            values.clear();
            values.add(rand.nextFloat());
            values.add(rand.nextFloat());
            values.add(rand.nextFloat());
            Collections.sort(values);
            result.add(
                    new Color(values.get(0),values.get(1), values.get(2),1)
            );
            values.clear();
            values.add(rand.nextFloat());
            values.add(rand.nextFloat());
            values.add(rand.nextFloat());
            Collections.sort(values);
            result.add(
                    new Color(values.get(2),values.get(0), values.get(1),1)
            );
            values.clear();
            values.add(rand.nextFloat());
            values.add(rand.nextFloat());
            values.add(rand.nextFloat());
            Collections.sort(values);
            result.add(
                    new Color(values.get(1),values.get(2), values.get(0),1)
            );
            values.clear();
        }
        return result;
    }

    public static ArrayList<Color> getBluePalette(int nbColors){
        ArrayList<Color> result = new ArrayList<>();
        Random rand = new Random();
        for(int i=0;i<nbColors;i++){
            float h = rand.nextInt(100)+180;
            float s = rand.nextFloat();
            float b = rand.nextFloat();
            Color newColor = Color.hsb(h,s,b);

            result.add(newColor);
        }
        return result;
    }
}
