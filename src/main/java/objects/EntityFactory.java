package objects;

import objects.Inmovable.Fish;
import objects.Movable.Herbivore;
import objects.Movable.Predator;
import objects.Inmovable.Grass;
import objects.Inmovable.Rock;
import objects.Inmovable.Tree;
import objects.Movable.Turtle;

public class EntityFactory{

    public static Entity create(EntityType type) throws WrongEntityTypeException{
        Entity entity;
        switch (type){
            case TREE ->        entity = new Tree();
            case ROCK ->        entity = new Rock();
            case GRASS ->       entity = new Grass();
            case PREDATOR ->    entity = new Predator();
            case HERBIVORE ->   entity = new Herbivore();
            case TURTLE ->      entity = new Turtle();
            case FISH ->        entity = new Fish();
            default -> throw new WrongEntityTypeException("ERROR: Unknowen EntityType");
        }
        return entity;
    }
}
