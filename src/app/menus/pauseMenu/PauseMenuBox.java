package app.menus.pauseMenu;

import app.menus.menu.MenuBox;
import app.menus.menu.MenuButton;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

import java.util.Map;

public class PauseMenuBox extends MenuBox {

    public PauseMenuBox(MenuButton... items) {
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        for (MenuButton item : items) {
            root.getChildren().add(item);
        }
        getChildren().add(root);
    }

    @Override
    public Map<String, MenuButton> getButtonsMap() {
        return null;
    }
}
