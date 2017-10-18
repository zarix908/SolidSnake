package models;

import app.GameFrame;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class TestsMain {

    private GameSettings generateSimpleMock(){
        CreatureType[][] field = {
                {CreatureType.Wall, CreatureType.Wall, CreatureType.Wall, CreatureType.Wall, CreatureType.Wall, CreatureType.Wall},
                {CreatureType.Wall, CreatureType.Apple, CreatureType.None, CreatureType.None, CreatureType.Apple, CreatureType.Wall},
                {CreatureType.Wall, CreatureType.None, CreatureType.SnakeHead, CreatureType.None, CreatureType.Apple, CreatureType.Wall},
                {CreatureType.Wall, CreatureType.None, CreatureType.None, CreatureType.None, CreatureType.None, CreatureType.Wall},
                {CreatureType.Wall, CreatureType.None, CreatureType.None, CreatureType.None, CreatureType.None, CreatureType.Wall},
                {CreatureType.Wall, CreatureType.Wall, CreatureType.Wall, CreatureType.Wall, CreatureType.Wall, CreatureType.Wall}
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
}
