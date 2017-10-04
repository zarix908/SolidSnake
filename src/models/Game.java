package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Game {
    private ICreature[][] _field;
    public Game(int width, int height){ //TODO: Load level from file? (NOT NEEDED FOR NOW)
        _field = new ICreature[width][height];
    }

    GameFrame makeTurn(Direction playerDirection){
        Map<Point, ArrayList<ICreature>> collisions = makeMoves(playerDirection);

    }

    private Map makeMoves(Direction playerDirection){
        Map<Point, ArrayList<ICreature>> collisions =
                new HashMap<Point, ArrayList<ICreature>>();
        for (ICreature[] row : _field){
            for (ICreature creature : row){
                if (creature != null) {
                    SnakeBodyPartSkeleton asBodyPart = creature instanceof SnakeBodyPartSkeleton ? ((SnakeBodyPartSkeleton) creature) : null;
                    if (asBodyPart == null || !asBodyPart.isHead())
                        continue;
                    creature.getNextMove(this); //TODO: pass only field
                    Point location = creature.getLocation();
                    if (collisions.get(location) == null) {
                        collisions.put(location, new ArrayList());
                    }
                    collisions.get(location).add(creature);
                }
            }
        }
        return collisions;
    }
}
