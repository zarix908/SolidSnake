package model;

import model.creatures.CreatureType;
import model.game.Game;
import model.game.GameFrame;
import model.game.GameSettings;
import model.utils.Direction;
import model.utils.Point;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import static model.creatures.CreatureType.*;

public class TestsMain {

    private GameSettings generateSimpleMock(){
        CreatureType[][] field = {
                {Wall,     Wall,     Wall,     Wall,     Wall,     Wall},
                {Wall,     Apple,    None,     None,     None,     Wall},
                {Wall,     Apple,    SnakeHead,None,     None,     Wall},
                {Wall,     Apple,    None,     None,     None,     Wall},
                {Wall,     Apple,    Apple,    None,     None,     Wall},
                {Wall,     Wall,     Wall,     Wall,     Wall,     Wall}
        };
        return new GameSettingsMock(field,
                false,
                0,
                100000,
                0,
                100000,
                1);
    }

    private GameSettings _settingsMock;

    @Before
    public void setUp() throws Exception{
        _settingsMock = generateSimpleMock();
    }

    @Test
    public void testIsMoving(){
        Game game = new Game(_settingsMock);
        Direction[] directions = {Direction.Right};
        GameFrame frame = game.makeTurn(directions);
        Assert.assertTrue(frame.getTypes().get(new Point(3,2))
                == CreatureType.SnakeHead);
    }

    @Test
    public void testDoesNot180(){
        Game game = new Game(_settingsMock);
        Direction[] directions = {Direction.Right};
        Direction[] directions2 = {Direction.Left};
        GameFrame frame = game.makeTurn(directions);
        frame = game.makeTurn(directions2);
        Assert.assertTrue(frame.getTypes().get(new Point(4,2))
                == CreatureType.SnakeHead);
    }

    @Test
    public void testEatsAppleGrowsAndGetsPoints(){
        //TODO: Redo this test
        Game game = new Game(_settingsMock);
        Direction[] directions = {Direction.Up};
        Direction[] directions2 = {Direction.Left};
        Direction[] directions3 = {Direction.Down};
        GameFrame frame = game.makeTurn(directions);
        frame = game.makeTurn(directions2);
        frame = game.makeTurn(directions3);
        Assert.assertTrue(frame
                    .getTypes()
                    .get(new Point(1,2))== CreatureType.SnakeHead);
        Assert.assertTrue(frame
                .getTypes()
                .get(new Point(1, 1)) == CreatureType.SimpleSnakeBodyPart);
        Assert.assertEquals(10, frame.getScores()[0]);
    }

    @Test
    public void testDiesFromWall(){
        Game game = new Game(_settingsMock);
        Direction[] directions = {Direction.Up};
        GameFrame frame = game.makeTurn(directions);
        frame = game.makeTurn(directions);
        Assert.assertEquals(null, frame);
    }

    @Test
    public void testCollisionSelf(){
        Game game = new Game(_settingsMock);
//        Direction[] directions = {Direction.Up};
//        Direction[] directions2 = {Direction.Left};
//        Direction[] directions3 = {Direction.Down};
//        Direction[] directions4 = {Direction.Down};
//        Direction[] directions5 = {Direction.Down};
//        Direction[] directions6 = {Direction.Right};
//        Direction[] directions7 = {Direction.Up};
//        Direction[] directions8 = {Direction.Left};
        Direction[] firstDir = {Direction.Up};
        GameFrame frame = game.makeTurn(firstDir);
        Direction[][] directions = {
                {Direction.Left},
                {Direction.Down},
                {Direction.Down},
                {Direction.Down},
                {Direction.Right},
                {Direction.Up},
                {Direction.Left}
        };
        for (Direction[] direction : directions) {
            frame = game.makeTurn(direction);
        }
        Assert.assertEquals(null, frame);
    }
}
