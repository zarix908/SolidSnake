package app;

class PauseMenuBox extends MenuBox {

    PauseMenuBox(MenuButton... items) {
        for (MenuButton item : items) {
            getChildren().add(item);
        }
    }
}
