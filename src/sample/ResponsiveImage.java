package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.io.File;
import java.net.MalformedURLException;

public class ResponsiveImage {
    private ImageView imageView;

    public ResponsiveImage(String pngResFile, StackPane scene){
        // Image
        File file = new File("res/"+pngResFile+".png");
        String localUrl = null;
        try {
            localUrl = file.toURI().toURL().toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Image image = new Image(localUrl);
        imageView = new ImageView(image);
        configureImage();
        putListeners(scene);
    }

    public ResponsiveImage(Image image, StackPane scene){
        imageView = new ImageView(image);
        configureImage();
        putListeners(scene);
    }

    private void configureImage(){
        imageView.setPreserveRatio(true);
    }

    private void putListeners(StackPane scene){

        scene.widthProperty().addListener(new ChangeListener<Number>() {
            @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
                double newSceneWidth2 = newSceneWidth.doubleValue();
                imageView.setFitWidth(newSceneWidth2);
            }
        });
        scene.heightProperty().addListener(new ChangeListener<Number>() {
            @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
                double newSceneHeight2 = newSceneHeight.doubleValue();
                imageView.setFitHeight(newSceneHeight2);
            }
        });
    }
    public ImageView getImageView() {
        return imageView;
    }

    public Image getImage(){
        return imageView.getImage();
    }
}
