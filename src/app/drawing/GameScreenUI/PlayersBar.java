package app.drawing.GameScreenUI;

import app.Settings;
import app.SkinSettings;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class PlayersBar extends StackPane {

    private HBox root;

    public PlayersBar(Settings settings) {
        SkinSettings skinSettings = (SkinSettings) settings.getSkins();
        int playersCount = settings.getGameplaySettings().getSnakesAmount();

        root = new HBox(10);
        root.setAlignment(Pos.CENTER);

        //TODO: change to array in skinSettings
        if (playersCount > 0){
            PlayerBox box1 = new PlayerBox(skinSettings.getPlayer1Skin(), 1);
            root.getChildren().add(box1);
        }
        if (playersCount > 1){
            PlayerBox box2 = new PlayerBox(skinSettings.getPlayer2Skin(), 2);
            root.getChildren().add(box2);
        }
        if (playersCount > 2){
            PlayerBox box3 = new PlayerBox(skinSettings.getPlayer3Skin(), 3);
            root.getChildren().add(box3);
        }

        getChildren().add(root);
    }

    public void update(int[] scores) {
        for (Node node : root.getChildren()) {
            if (node instanceof PlayerBox) {
                ((PlayerBox) node).update(scores);
            }
        }
    }
}
