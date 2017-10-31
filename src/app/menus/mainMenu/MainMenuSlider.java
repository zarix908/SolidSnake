package app.menus.mainMenu;

import app.menus.menu.MenuButton;
import javafx.geometry.Pos;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class MainMenuSlider extends MenuButton {

    private Slider slider;

    public MainMenuSlider(double min, double max, double value, String name){
        slider = new Slider(min, max, value);

        VBox root = new VBox();

        LinearGradient gradient = new LinearGradient(
                0, 0,
                1, 0,
                true,
                CycleMethod.NO_CYCLE,
                new Stop(0, Color.TRANSPARENT),
                new Stop(0.4, Color.GREY),
                new Stop(0.6, Color.GREY),
                new Stop(1, Color.TRANSPARENT)
        );

        Text text = new Text(name);
        text.setFill(Color.DARKGREY);
        text.setFont(Font.font("Calibri", FontWeight.SEMI_BOLD, 22));
        text.setTextAlignment(TextAlignment.CENTER);

        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(text, slider);
        setAlignment(Pos.CENTER);
        getChildren().addAll(root);

        setOnMouseEntered(event -> text.setFill(Color.WHITE));
        setOnMouseExited(event -> {
            text.setFill(Color.DARKGREY);
        });
    }

    public Slider getSlider() {
        return slider;
    }
}
