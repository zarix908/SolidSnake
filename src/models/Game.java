package models;

import app.GameFrame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Game {
    private ICreature[][] _field;
    public Game(int width, int height){ //TODO: Load level from file? (NOT NEEDED FOR NOW)
        _field = new ICreature[width][height];
    }

    public GameFrame makeTurn(Direction playerDirection){
        Map<Point, ArrayList<ICreature>> collisions = makeMoves(playerDirection);
        Map<Point, ICreature> survivedCreatures = resolveCollisions(collisions);
        //TODO: do smth with the map
        return new GameFrame(_field.length, _field[0].length, survivedCreatures);
    }

    private Map<Point, ICreature> resolveCollisions(Map<Point, ArrayList<ICreature>> collisions){
        Map<Point, ICreature> resolved = new HashMap<>();
        for (Point location: collisions.keySet()) {
            ArrayList<ICreature> collidingCreatures = collisions.get(location);
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

    private Map<Point, ArrayList<ICreature>> makeMoves(Direction playerDirection){
        Map<Point, ArrayList<ICreature>> collisions =
                new HashMap<>();
        for (ICreature[] row : _field){
            for (ICreature creature : row){
                if (creature != null) {
                    SnakeBodyPartSkeleton asBodyPart = creature instanceof SnakeBodyPartSkeleton ? ((SnakeBodyPartSkeleton) creature) : null;
                    if (asBodyPart == null || !asBodyPart.isHead())
                        continue;
                    creature.getNextMove(_field); //TODO: pass only field
                    Point location = creature.getLocation();
                    collisions.computeIfAbsent(location, k -> new ArrayList<>());
                    collisions.get(location).add(creature);
                }
            }
        }
        return collisions;
    }
}
