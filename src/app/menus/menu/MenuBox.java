package app.menus.menu;

import javafx.scene.layout.StackPane;

import java.util.Map;

public abstract class MenuBox extends StackPane{
    public abstract Map<String, MenuObject> getButtonsMap();
}
