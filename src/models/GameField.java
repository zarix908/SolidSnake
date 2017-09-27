package models;

public class GameField {
    private ICreature[][] _field;
    public GameField(int width, int height){ //TODO: Load level from file? (NOT NEEDED FOR NOW)
        _field = new ICreature[width][height];
    }
}
