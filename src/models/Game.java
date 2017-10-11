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

            //TODO: Shitty code of food gen
            Point randomPoint1;
            while (true) {
                randomPoint1 = Point.generateRandomInBounds(0, _field.length - 1,
                        0, _field[0].length - 1,
                        0, 0);
                if (_field[randomPoint1.getX()][randomPoint1.getY()] == null) {
                    break;
                }
            }
            _field[randomPoint1.getX()][randomPoint1.getY()] = new Apple(randomPoint1);
            //TODO: Cyka blyat gotta generate field and random location for snakes
        }
    }

    public GameFrame makeTurn(Direction[] playerDirection){
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
//        _field[randomPoint.getX()][randomPoint.getY()] = random >= 0.75
//                ? new Mushroom(randomPoint)
//                : new Apple(randomPoint);


        // New food generation (may need refactoring, eg. separate method)
        // lastBoost eaten is set during SimpleSnakeBodyPart.interactWith()
        // it is needed for generating the same boost when previous was eaten
        // Mushroom generates every 50 points
        for (Snake snake : _snakes) {
            CreatureType lastBoost = snake.getLastBoost();
            if (lastBoost != null) {
                switch (lastBoost) {
                    case Apple:

                        //TODO: Make separate method for randomPoints with null in field
                        Point randomPoint;
                        while (true) {
                            randomPoint = Point.generateRandomInBounds(0, _field.length - 1,
                                    0, _field[0].length - 1,
                                    0, 0);
                            if (_field[randomPoint.getX()][randomPoint.getY()] == null) {
                                break;
                            }
                        }

                        _field[randomPoint.getX()][randomPoint.getY()] = new Apple(randomPoint);
                        break;
                    default:
                        throw new IllegalStateException("WAT? (%s) is not a boost or shouldn't be generated when the same boost was eaten");
                }
                snake.resetLastBoost();
            }

            // Generate mushroom:
            //TODO: won't generate mushroom if score increased from 190 to 210 (but it should)
            //TODO: load scoreGap (it's 50 now) somewhere from settings
            if (snake.getScore()%50 == 0 && snake.getScore() != 0){
                Point randomPoint;
                while (true) {
                    randomPoint = Point.generateRandomInBounds(0, _field.length - 1,
                            0, _field[0].length - 1,
                            0, 0);
                    if (_field[randomPoint.getX()][randomPoint.getY()] == null) {
                        break;
                    }
                }
                _field[randomPoint.getX()][randomPoint.getY()] = new Mushroom(randomPoint);
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
