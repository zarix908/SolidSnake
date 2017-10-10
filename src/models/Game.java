package models;

import app.GameFrame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;


public class Game {
    private Creature[][] _field;
    private final Snake[] _snakes;

    public Game(int width, int height, int snakeCount){ //TODO: Load level from file? (NOT NEEDED FOR NOW)
        _field = new Creature[width][height];
        //TODO: generate borders
        _snakes = new Snake[snakeCount];
        for (int i = 0; i < snakeCount; i++) {
            Point randomPoint;
            //TODO: WARNING: POTENTIAL INFINITE LOOP
            while (true){
                randomPoint = Point.generateRandomInBounds(0, width - 1,
                        0, height - 1,
                        1, 1);
                if (_field[randomPoint.getX()][randomPoint.getY()] == null){
                    break;
                }
            }
            _snakes[i] = new Snake(randomPoint, Direction.None);
            //TODO: Cyka blyat gotta generate field and random location for snakes
        }
    }

    public GameFrame makeTurn(Direction[] playerDirection){
        Map<Point, List<Creature>> collisions = makeMoves(playerDirection);
        Map<Point, Creature> survivedCreatures = resolveCollisions(collisions);
        cleanUp();
        makeNewField(survivedCreatures);
        int[] scores = new int[_snakes.length];
        for (int i = 0; i < _snakes.length; i++) {
            scores[i] = _snakes[i].getScore();
        }
        return new GameFrame(_field.length, _field[0].length, survivedCreatures, scores);
    }

    private void makeNewField(Map<Point, Creature> survivedCreatures) {
        _field = new Creature[_field.length][_field[0].length];
        for (Point location : survivedCreatures.keySet()) {
            _field[location.getX()][location.getY()] = survivedCreatures.get(location);
        }
        //TODO: generate food
        Point randomPoint;
        //TODO: WARNING: POTENTIAL INFINITE LOOP
        while (true){
            randomPoint = Point.generateRandomInBounds(0, _field.length - 1,
                    0, _field[0].length - 1,
                    0, 0);
            if (_field[randomPoint.getX()][randomPoint.getY()] == null){
                break;
            }
        }
        double random = ThreadLocalRandom.current().nextDouble(0, 1);
        _field[randomPoint.getX()][randomPoint.getY()] = random >= 0.75
                ? new Mushroom(randomPoint)
                : new Apple(randomPoint);

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
            for (int i = 0; i < length - 1; i++) {
                for (int j = i; j < length; j++) {
                    collidingCreatures.get(i).interactWith(collidingCreatures.get(j));
                    collidingCreatures.get(j).interactWith(collidingCreatures.get(i));
                }
            }
            Creature csurvivedCreature = null;
            for (Creature creature : collidingCreatures) {
                if(creature.isDead()){
                    continue;
                }
                if (csurvivedCreature != null) {
                    throw new IllegalStateException("Anotha one!" +
                            " Two creatures collided and still alive");
                }
                csurvivedCreature = creature;
            }
            if (csurvivedCreature == null) {
                continue;
            }
            resolved.put(location, csurvivedCreature);
        }
        return resolved;
    }

    private Map<Point, List<Creature>> makeMoves(Direction[] playerDirection){
        Map<Point, List<Creature>> collisions =
                new HashMap<>();
        for (Creature[] row : _field){
            for (Creature creature : row){
                if (creature != null) {
                    SnakeBodyPartSkeleton asBodyPart = creature instanceof SnakeBodyPartSkeleton ? ((SnakeBodyPartSkeleton) creature) : null;
                    if (asBodyPart == null)
                        continue;
                    creature.makeMove(_field);
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
                snakeBodyPart.makeMove(_field);
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
}
