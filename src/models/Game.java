package models;

public class Game {
    private ICreature[][] _field;
    public Game(int width, int height){ //TODO: Load level from file? (NOT NEEDED FOR NOW)
        _field = new ICreature[width][height];
    }

    //ICreature[][] makeTurn();
}
