package models;

public interface GameSettings {
    int getAppleSpawnRate();
    int getAppleDeathRate();
    int getMushroomSpawnRate();
    int getMushroomDeathRate();
    boolean isFoodSpawnEnabled();
    Creature[][] getInitialField();
    Snake[] getSnakes();
}
