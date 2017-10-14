package models;

import app.GameFrame;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


public class Game {
    private Creature[][] _field;
    private final Snake[] _snakes;
    private int _turnNumber = 0;
    private final int _appleSpawnRate = 20;
    private final int _appleDeathRate = 30;
    private final int _mushroomSpawnRate = 50;
    private final int _mushroomDeathRate = 15;

    public Game(int width, int height, int snakeCount){ //TODO: Load level from file? (NOT NEEDED FOR NOW)
        _field = new Creature[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if(i == 0 || i == width - 1 || j == 0 || j == height - 1) {
                    _field[i][j] = new Wall(new Point(i, j));
                }
            }
        }
        _snakes = new Snake[snakeCount];
        Point[] snakesLocations = generateSafeRandomPoints(snakeCount,0, _field.length - 1,
                0, _field[0].length - 1, 2, 2);
        Point[] applesLocations = generateSafeRandomPoints(snakeCount, 0, _field.length - 1,
                0, _field[0].length - 1, 1, 1);
        for (int i = 0; i < snakeCount; i++) {
            if (snakesLocations[i] == null) {
                throw new UnsupportedOperationException("The world is too small for both of us!" +
                        "(Field too small - could not spawn a snake)");
            }
            _snakes[i] = new Snake(snakesLocations[i], Direction.None);
            if(applesLocations[i] != null){
                _field[applesLocations[i].getX()][applesLocations[i].getY()] =
                            new Apple(applesLocations[i], _turnNumber, _appleDeathRate);
            }
        }
    }

    public GameFrame makeTurn(Direction[] playerDirection){
        _turnNumber++;
        Map<Point, List<Creature>> collisions = makeMoves(playerDirection);
        Map<Point, Creature> survivedCreatures = resolveCollisions(collisions);
        cleanUp();
        makeNewField(survivedCreatures);
        int[] scores = new int[_snakes.length];
        boolean isThereAnySnakeAlive = true;
        for (int i = 0; i < _snakes.length; i++) {
            scores[i] = _snakes[i].getScore();
            isThereAnySnakeAlive = isThereAnySnakeAlive && !_snakes[i].isDead();
        }
        if(!isThereAnySnakeAlive) {
            return null;
        }
        return new GameFrame(_field.length, _field[0].length, survivedCreatures, scores);
    }

    private void makeNewField(Map<Point, Creature> survivedCreatures) {
        _field = new Creature[_field.length][_field[0].length];
        for (Point location : survivedCreatures.keySet()) {
            _field[location.getX()][location.getY()] = survivedCreatures.get(location);
        }

        double random = ThreadLocalRandom.current().nextDouble(0, 1);
        if(_turnNumber % _appleSpawnRate == 0) {
            Point[] apples = generateSafeRandomPoints(_snakes.length,
                    0, _field.length - 1,
                    0, _field[0].length - 1,
                    1, 1);
            for (Point appleLocation : apples) {
                if(appleLocation != null){
                    _field[appleLocation.getX()][appleLocation.getY()] = new Apple(appleLocation, _turnNumber, _appleDeathRate);
                }
            }
        }
        if(_turnNumber % _mushroomSpawnRate == 0) {
            Point[] mushrooms = generateSafeRandomPoints(_snakes.length
                    , 0, _field.length - 1,
                    0, _field[0].length - 1,
                    1, 1);
            for (Point mushroomLocation : mushrooms) {
                if(mushroomLocation != null){
                    _field[mushroomLocation.getX()][mushroomLocation.getY()] = new Mushroom(mushroomLocation, _turnNumber, _mushroomDeathRate);
                }
            }
        }
    }

    private void cleanUp() {
        for (Creature[] row : _field) {
            for (Creature creature : row) {
                if (creature != null && creature.isDead()) {
                    creature.cleanUp();
                }
            }
        }
    }

    private Map<Point, Creature> resolveCollisions(Map<Point, List<Creature>> collisions){
        Map<Point, Creature> resolved = new HashMap<>();
        for (Point location: collisions.keySet()) {
            List<Creature> collidingCreatures = collisions.get(location);
            int length = collidingCreatures.size();
            if(length > 1)

            for (int i = 0; i < length - 1; i++) {
                for (int j = i + 1; j < length; j++) {
                    collidingCreatures.get(i).interactWith(collidingCreatures.get(j));
                    collidingCreatures.get(j).interactWith(collidingCreatures.get(i));
                }
            }
            Creature survivedCreature = null;
            for (Creature creature : collidingCreatures) {
                if(creature.isDead()){
                    continue;
                }
                if (survivedCreature != null) {
                    throw new IllegalStateException("Anotha one!" +
                            " Two creatures collided and still alive");
                }
                survivedCreature = creature;
            }
            if (survivedCreature == null) {
                continue;
            }
            resolved.put(location, survivedCreature);
        }
        return resolved;
    }

    private Map<Point, List<Creature>> makeMoves(Direction[] playerDirection){
        Map<Point, List<Creature>> collisions =
                new HashMap<>();
        for (Creature[] row : _field){
            for (Creature creature : row){
                if (creature != null) {
                    SnakeBodyPart asBodyPart = creature instanceof SnakeBodyPart ? ((SnakeBodyPart) creature) : null;
                    if (asBodyPart != null)
                        continue;
                    creature.makeMove(_field, _turnNumber);
                    Point location = creature.getLocation();
                    collisions.computeIfAbsent(location, k -> new ArrayList<>());
                    collisions.get(location).add(creature);
                }
            }
        }
        for (int i = 0; i < _snakes.length; i++) {
            Snake snake = _snakes[i];
            if (snake.isDead())
                continue;
            snake.setCurrentDirection(playerDirection[i]);
            SnakeBodyPart snakeBodyPart = snake.getHead();
            while (true){
                snakeBodyPart.makeMove(_field, _turnNumber);
                Point location = snakeBodyPart.getLocation();
                collisions.computeIfAbsent(location, k -> new ArrayList<>());
                collisions.get(location).add(snakeBodyPart);
                snakeBodyPart = snakeBodyPart.getNextBodyPart();
                if (snakeBodyPart == null) {
                    break;
                }
            }
        }
        return collisions;
    }

    private Set<Point> generatePointForEveryCell(int x1, int x2, int y1, int y2, int borderX, int borderY){
        Set<Point> points = new HashSet<>();
        for (int x = x1 + borderX; x < x2 - borderX + 1; x++) {
            for (int y = y1 + borderY; y < y2 - borderY + 1; y++) {
                points.add(new Point(x, y));
            }
        }
        return points;
    }

    private Point[] generateSafeRandomPoints(int amount, int x1, int x2, int y1, int y2, int borderX, int borderY){
        Set<Point> points = generatePointForEveryCell(x1, x2, y1, y2, borderX, borderY);
        Point[] randomPoints = new Point[amount];
        int pointNumber = 0;
        while (true) {
            if (points.size() == 0){
                break;
            }
            Point randomPoint = Point.generateRandomInBounds(x1, x2,
                    y1, y2,
                    borderX, borderY);
            if (_field[randomPoint.getX()][randomPoint.getY()] == null) {
                randomPoints[pointNumber] = randomPoint;
                pointNumber++;
            }
            if (pointNumber == amount){
                break;
            }
            points.remove(randomPoint);
        }
        return randomPoints;
    }
}
