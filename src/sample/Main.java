package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        BorderPane mainPane = new BorderPane();
        StackPane stackPane = new StackPane();
        primaryStage.setTitle("Le programme incroyable qui permet de changer les couleurs d'une image");
        primaryStage.setScene(new Scene(mainPane, 300, 275));
        primaryStage.show();
        Scene scene = primaryStage.getScene();

        //Chargement Image travail
        File file = new File("res/steampunk_colored.png");
        String sourceUrl = file.toURI().toURL().toString();
        Image sourceImage = new Image(sourceUrl);
        /*ResponsiveImage responsiveImage = new ResponsiveImage("allrgb",stackPane);
        stackPane.getChildren().add(responsiveImage.getImageView());*/

        //Chargement image Palette
        File filePalette = new File("res/palette.png");
        String paletteUrl = filePalette.toURI().toURL().toString();
        Image paletteImage = new Image(paletteUrl);

        //Choix de la palette
        //ArrayList<Color> replacementColors = PaletteFactory.getPaletteFromImage(paletteImage);
        //ArrayList<Color> replacementColors = PaletteFactory.getRandomPalette(3);
        //ArrayList<Color> replacementColors = PaletteFactory.getTolemackRgb(2);
        //ArrayList<Color> replacementColors = PaletteFactory.getBluePalette(4);

        //Remplacement couleurs
        ImageView imageResultView = new ImageView(sourceImage);
        imageResultView.setPreserveRatio(true);
        imageResultView.fitWidthProperty().bind(mainPane.widthProperty());
        imageResultView.fitHeightProperty().bind(mainPane.heightProperty().subtract(50));

        HBox topHBox = new HBox();
        ToggleGroup tgroup = new ToggleGroup();
        RadioButton rb1 = new RadioButton("Image");
        rb1.setToggleGroup(tgroup);
        RadioButton rb2 = new RadioButton("Random");
        rb2.setToggleGroup(tgroup);
        RadioButton rb3 = new RadioButton("Rainbow6");
        rb3.setToggleGroup(tgroup);
        rb3.setSelected(true);
        Button reloadButton = new Button("Reload");
        TextField textField = new TextField("2");
        reloadButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                int param=1;
                try{
                    param = Integer.parseInt(textField.getText());
                }catch(Exception txtFieldE){
                    System.out.println("Tapez un nombre");
                }
                RadioButton rbSelected = (RadioButton) tgroup.getSelectedToggle();
                ArrayList<Color> replacementColors;
                if(rbSelected.getText().equals("Image"))
                    replacementColors = PaletteFactory.getPaletteFromImage(paletteImage);
                else if(rbSelected.getText().equals("Random"))
                    replacementColors = PaletteFactory.getRandomPalette(param);
                else
                    replacementColors = PaletteFactory.getTolemackRgb(param);
                Image reloadedImage = ImageReplaceColor.replace(sourceImage,replacementColors);
                ImageView reloadedImageView = new ImageView(reloadedImage);
                reloadedImageView.setPreserveRatio(true);
                reloadedImageView.fitWidthProperty().bind(mainPane.widthProperty());
                reloadedImageView.fitHeightProperty().bind(mainPane.heightProperty().subtract(50));
                mainPane.setCenter(reloadedImageView);
                mainPane.setBottom(PaletteViewerFactory.getView(replacementColors));
            }
        });

        //ajout au front
        topHBox.setSpacing(10);
        topHBox.setAlignment(Pos.CENTER);
        topHBox.getChildren().add(rb1);
        topHBox.getChildren().add(rb2);
        topHBox.getChildren().add(rb3);
        topHBox.getChildren().add(textField);
        topHBox.getChildren().add(reloadButton);

        mainPane.setCenter(imageResultView);
        mainPane.setTop(topHBox);

        //PixelReader = new PixelReader();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
