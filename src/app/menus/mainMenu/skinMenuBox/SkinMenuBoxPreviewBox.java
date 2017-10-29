package app.menus.mainMenu.skinMenuBox;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import app.menus.menu.MenuButton;
import javafx.scene.layout.VBox;
import model.utils.Direction;
import java.util.Map;

public class SkinMenuBoxPreviewBox extends StackPane {

    private ImageView imageView;
    private Map<String, MenuButton> buttons;

    public SkinMenuBoxPreviewBox(Image image){
        setAlignment(Pos.CENTER);
        this.imageView = new ImageView(image);

        VBox box = new VBox();
        MenuButton buttonPrev = new SkinMenuBoxArrowButton(Direction.Up);
        MenuButton buttonNext = new SkinMenuBoxArrowButton(Direction.Down);
        buttons = Map.of(
                "buttonPrev", buttonPrev,
                "buttonNext", buttonNext
        );

        box.getChildren().addAll(buttonPrev, this.imageView, buttonNext);
        getChildren().add(box);
    }

    public void setImage(Image image) {
        this.imageView.setImage(image);
    }

    public Map<String, MenuButton> getButtonsMap() {
        return buttons;
    }
}
