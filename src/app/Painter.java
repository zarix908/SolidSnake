package app;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import models.Point;

public class Painter {
    static int size = Settings.getSize();

    public static void paint(GameFrame frame, GraphicsContext gc){
        gc.setFill(Color.DARKGRAY);
        gc.fillRect(0,0, Settings.getWidth(), Settings.getHeight());

        frame.getTexturesInfo().forEach((p, ti) -> {
            //TODO: considering that Point is (col, row), not pixels
            gc.setFill(Settings.getColorDict().get(ti.getType()));
            paintPoint(p, gc);
        });

        //TODO: draw dead Snake
        //TODO: draw score (need score in model)
    }

    private static void paintPoint(Point point, GraphicsContext gc) {
        gc.fillRect(point.getX() * size, point.getY() * size, size, size);
    }

    public static void paintResetMessage(GraphicsContext gc) {
        gc.setFill(Color.AQUAMARINE);
        gc.fillText("Hit RETURN to reset.", 10, 10);
    }
}
