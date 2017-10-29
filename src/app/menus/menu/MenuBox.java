package app.menus.menu;

import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.Map;

public abstract class MenuBox extends StackPane{
    public abstract Map<String, MenuButton> getButtonsMap();
}
