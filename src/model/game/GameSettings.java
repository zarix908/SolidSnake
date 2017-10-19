package model.game;

import model.creatures.CreatureType;

public interface GameSettings {
    int getAppleSpawnRate();
    int getAppleDeathRate();
    int getMushroomSpawnRate();
    int getMushroomDeathRate();
    boolean isFoodSpawnEnabled();
    CreatureType[][] getInitialField();
    int getSnakesAmount();
}
