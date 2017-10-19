package model;

import model.creatures.CreatureType;
import model.game.GameSettings;

public class GameSettingsMock implements GameSettings {

    private final CreatureType[][] _field;
    private final boolean _spawnEnabled;
    private final int _appleSpawnRate;
    private final int _appleDeathRate;
    private final int _mushroomSpawnRate;
    private final int _mushroomDeathRate;
    private final int _snakesAmount;


    public GameSettingsMock(CreatureType[][] field,
                            boolean spawnEnabled,
                            int appleSpawnRate, int appleDeathRate,
                            int mushroomSpawnRate, int mushroomDeathRate, int snakesAmount) {
        _field = field;
        _spawnEnabled = spawnEnabled;
        _appleSpawnRate = appleSpawnRate;
        _appleDeathRate = appleDeathRate;
        _mushroomSpawnRate = mushroomSpawnRate;
        _mushroomDeathRate = mushroomDeathRate;
        _snakesAmount = snakesAmount;
    }

    @Override
    public int getAppleSpawnRate() {
        return _appleSpawnRate;
    }

    @Override
    public int getAppleDeathRate() {
        return _appleDeathRate;
    }

    @Override
    public int getMushroomSpawnRate() {
        return _mushroomSpawnRate;
    }

    @Override
    public int getMushroomDeathRate() {
        return _mushroomDeathRate;
    }

    @Override
    public boolean isFoodSpawnEnabled() {
        return _spawnEnabled;
    }

    @Override
    public CreatureType[][] getInitialField() {
        return _field;
    }

    @Override
    public int getSnakesAmount() {
        return _snakesAmount;
    }
}
