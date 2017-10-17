package models;

import app.GameFrame;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class TestsMain {

    private GameSettings generateSimpleMock(){
        CreatureType[][] field = {
                {CreatureType.Wall, CreatureType.Wall, CreatureType.Wall, CreatureType.Wall, CreatureType.Wall, CreatureType.Wall},
                {CreatureType.Wall, CreatureType.Apple, CreatureType.None, CreatureType.None, CreatureType.None, CreatureType.Wall},
                {CreatureType.Wall, CreatureType.None, CreatureType.SnakeHead, CreatureType.None, CreatureType.None, CreatureType.Wall},
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
}
