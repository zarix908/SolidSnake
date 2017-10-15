package app;

import javafx.animation.FadeTransition;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

class MainMenu extends Menu {

    private VBox _menuWithInfo;
    private StackPane _startPane;
    private MainMenuInfoText _infoText;

    MainMenu(){
        _menuWithInfo = new VBox();
        _menuWithInfo.setAlignment(Pos.BOTTOM_CENTER);

        _startPane = new StackPane();
        _infoText = new MainMenuInfoText("");

        MenuButton mainPlay = new MainMenuButton("PLAY");
        MenuButton mainOptions = new MainMenuButton("OPTIONS");
        MenuButton mainExit = new MainMenuButton("EXIT");
        MenuBox menuMain = new MainMenuBox(
                mainPlay,
                mainOptions,
                mainExit
        );

        MenuButton optionsControls = new MainMenuButton("CONTROLS");
        MenuButton optionsSkins = new MainMenuButton("SKINS");
        MenuButton optionsBack = new MainMenuButton("BACK");
        MenuBox menuOptions = new MainMenuBox(
                optionsControls,
                optionsSkins,
                optionsBack
        );

        MenuButton skinsBlue = new MainMenuButton("BLUE SNAKE");
        MenuButton skinsRed = new MainMenuButton("RED SNAKE");
        MenuButton skinsBack = new MainMenuButton("BACK");
        MenuBox menuSkins = new MainMenuBox(
                skinsBlue,
                skinsRed,
                skinsBack
        );

        MenuButton playSolo = new MainMenuButton("SOLO");
        MenuButton playDuo = new MainMenuButton("DUO");
        MenuButton playBack = new MainMenuButton("BACK");
        MenuBox menuPlay = new MainMenuBox(
                playSolo,
                playDuo,
                playBack
        );


        mainPlay.setOnMouseClicked(event -> {
            fadeFromMenuToMenu(menuMain, menuPlay);
            _infoText.setText("");
        });
        mainOptions.setOnMouseClicked(event -> {
            fadeFromMenuToMenu(menuMain, menuOptions);
            _infoText.setText("");
        });
        mainExit.setOnMouseClicked(event -> System.exit(0));


        optionsControls.setOnMouseClicked(event -> {
            _infoText.setText("Not featured yet");
        });
        optionsSkins.setOnMouseClicked(event -> {
            fadeFromMenuToMenu(menuOptions, menuSkins);
            _infoText.setText("");
        });
        optionsBack.setOnMouseClicked(event -> {
            fadeFromMenuToMenu(menuOptions, menuMain);
            _infoText.setText("");
        });


        skinsBlue.setOnMouseClicked(event -> {
            _infoText.setText("Not featured yet");
        });
        skinsRed.setOnMouseClicked(event -> {
            _infoText.setText("Not featured yet");
        });
        skinsBack.setOnMouseClicked(event -> {
            fadeFromMenuToMenu(menuSkins, menuOptions);
            _infoText.setText("");
        });


        playSolo.setOnMouseClicked(event -> {
//            GameLoop gl = new GameLoop(1);
//            gl.run();
            App.setSnakeCount(1);
            App.playSnake();
        });
        playDuo.setOnMouseClicked(event -> {
            _infoText.setText("Not featured yet");
        });
        playBack.setOnMouseClicked(event -> {
            fadeFromMenuToMenu(menuPlay, menuMain);
            _infoText.setText("");
        });


        initMenu(menuMain);
        initMenu(menuOptions);
        initMenu(menuSkins);
        initMenu(menuPlay);

        _startPane.getChildren().add(menuMain);
        _menuWithInfo.getChildren().addAll(_startPane, _infoText);
        getChildren().add(_menuWithInfo);
    }

//    VBox getMenu(){
//        return _menuWithInfo;
//    }

    private void fadeFromMenuToMenu(MenuBox from, MenuBox to){
        FadeTransition frFrom = new FadeTransition(Duration.millis(200), from);
        frFrom.setFromValue(1);
        frFrom.setToValue(0);

        FadeTransition ftTo = new FadeTransition(Duration.millis(200), to);
        ftTo.setFromValue(0);
        ftTo.setToValue(1);

        frFrom.play();
        frFrom.setOnFinished(event -> {
            _startPane.getChildren().remove(from);
        });
        _startPane.getChildren().add(to);
        ftTo.play();
    }

    private void initMenu(MenuBox menu){
        menu.setAlignment(Pos.BOTTOM_CENTER);
        menu.setMaxWidth(300);
        menu.setTranslateY(-20);
    }
}
