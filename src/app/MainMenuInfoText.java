package app;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

class MainMenuInfoText extends StackPane{

    MainMenuInfoText(String s){
        Text text = createText(s);
        setAlignment(Pos.BOTTOM_CENTER);
        getChildren().addAll(text);
    }

    void setText(String s){
        getChildren().remove(0);
        Text text = createText(s);
        setAlignment(Pos.BOTTOM_CENTER);
        getChildren().addAll(text);
    }

    private Text createText(String s){
        Text text = new Text(s);
        text.setFill(Color.DARKGREY);
        text.setFont(Font.font("Calibri", FontWeight.SEMI_BOLD, 22));
        return text;
    }
}
