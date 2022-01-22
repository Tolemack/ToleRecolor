package sample;

import javafx.scene.image.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class ImageReplaceColor {
    public static Image replace(Image image, ArrayList<Color> colors){
        int imageWidth = (int)image.getWidth();
        int imageHeight = (int)image.getHeight();

        double red=0;
        double green=0;
        double blue=0;

        PixelReader imagePixelReader = image.getPixelReader();
        WritableImage writableImage = new WritableImage(imageWidth,imageHeight);
        PixelWriter pixelWriter = writableImage.getPixelWriter();
        for(int y=0; y<imageHeight;y++){
            for(int x=0; x<imageWidth; x++){
                Color color = imagePixelReader.getColor(x,y);
                Color writeColor = getNearestColor(color, colors);
                pixelWriter.setColor(x,y,writeColor);
            }
        }

        return writableImage;
    }

    private static void printRgb(double red, double green, double blue){
        System.out.println("red : "+red+" - green : "+green+" - blue : "+blue);
    }

    private static Color getNearestColor(Color color, ArrayList<Color> colorArrayList){
        Color result = colorArrayList.get(0);
        for(Color testColor : colorArrayList){
            if(calculateDistance(color, testColor)<calculateDistance(color, result)){
                result = testColor;
            };
        }
        return result;
    }

    private static Color getFurthestColor(Color color, ArrayList<Color> colorArrayList){
        Color result = colorArrayList.get(0);
        for(Color testColor : colorArrayList){
            if(calculateDistance(color, testColor)>calculateDistance(color, result)){
                result = testColor;
            };
        }
        return result;
    }

    private static Color getBrightnessColor(Color color, ArrayList<Color> colorArrayList){
        Color result = colorArrayList.get(0);
        for(Color testColor : colorArrayList){
            if(calculateDistanceBrightness(color, testColor)<calculateDistanceBrightness(color, result)){
                result = testColor;
            };
        }
        return result;
    }

    private static double calculateDistance(Color c1, Color c2){
        return Math.sqrt(
                Math.pow(c2.getRed()-c1.getRed(),2)
                + Math.pow(c2.getGreen()-c1.getGreen(),2)
                + Math.pow(c2.getBlue()-c1.getBlue(),2)
        );
    }

    private static double calculateDistanceBrightness(Color c1, Color c2){
        return Math.abs(c2.getBrightness()-c1.getBrightness());
    }

    private static double calculateDistanceHue(Color c1, Color c2){
        return Math.abs(c2.getHue()-c1.getHue());
    }


}
