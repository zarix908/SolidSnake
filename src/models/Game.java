package models;

import app.GameFrame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Game {
    private ICreature[][] _field;
    private final Snake[] _snakes;
    public Game(int width, int height, Snake[] snakes){ //TODO: Load level from file? (NOT NEEDED FOR NOW)
        _field = new ICreature[width][height];
        _snakes = snakes;
    }

    public GameFrame makeTurn(Direction[] playerDirection){
        Map<Point, List<ICreature>> collisions = makeMoves(playerDirection);
        Map<Point, ICreature> survivedCreatures = resolveCollisions(collisions);
        cleanUp();
        makeNewField(survivedCreatures);
        int[] scores = new int[_snakes.length];
        for (int i = 0; i < _snakes.length; i++) {
            scores[i] = _snakes[i].getScore();
        }
        return new GameFrame(_field.length, _field[0].length, survivedCreatures, scores);
    }

    private void makeNewField(Map<Point, ICreature> survivedCreatures) {
        _field = new ICreature[_field.length][_field[0].length];
        for (Point location : survivedCreatures.keySet()) {
            _field[location.getX()][location.getY()] = survivedCreatures.get(location);
        }
    }

    private void cleanUp() {
        for (ICreature[] row : _field) {
            for (ICreature creature : row) {
                if (creature != null && creature.isDead()) {
                    creature.cleanUp();
                }
            }
        }
    }

    private Map<Point, ICreature> resolveCollisions(Map<Point, List<ICreature>> collisions){
        Map<Point, ICreature> resolved = new HashMap<>();
        for (Point location: collisions.keySet()) {
            List<ICreature> collidingCreatures = collisions.get(location);
            int length = collidingCreatures.size();
            for (int i = 0; i < length - 1; i++) {
                for (int j = i; j < length; j++) {
                    collidingCreatures.get(i).interactWith(collidingCreatures.get(j));
                    collidingCreatures.get(j).interactWith(collidingCreatures.get(i));
                }
            }
            ICreature csurvivedCreature = null;
            for (ICreature creature : collidingCreatures) {
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

    private Map<Point, List<ICreature>> makeMoves(Direction[] playerDirection){
        Map<Point, List<ICreature>> collisions =
                new HashMap<>();
        for (ICreature[] row : _field){
            for (ICreature creature : row){
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
            ISnakeBodyPart snakeBodyPart = snake.getHead();
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
