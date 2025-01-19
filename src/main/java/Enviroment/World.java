package Enviroment;

import objects.Entity;
import objects.EntityType;
import objects.Movable.Creature;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class World {

    private final int width;
    private final int height;
    private int turnCounter;
    private HashMap<Coordinate, Entity> entitysPosition;

    public World(int width, int height) {
        this.width = width;
        this.height = height;
        this.entitysPosition = new HashMap<Coordinate, Entity>();
        this.turnCounter = 0;
    }


    public int getWidth() {
        return this.width;
    }

    public void timeUp(){
        this.turnCounter++;
    }

    public int getTime(){
        return turnCounter;
    }

    public int getHeight() {
        return this.height;
    }

    public Coordinate getMaxCoordinate() {
        return new Coordinate(this.width - 1, this.height-1);
     }

    public Entity getEntity(Coordinate coordinate){
        return entitysPosition.get(coordinate);
    }

    public Set<Coordinate> getEntitysPosition(){
        return this.entitysPosition.keySet();
    }

    public void removeEntity(Coordinate coordinate){
        entitysPosition.remove(coordinate);
    }

    public boolean coordinateIsBusy(Coordinate coordinate){
       return this.entitysPosition.containsKey(coordinate);
    }

    public boolean coordinateIsEmpty(Coordinate coordinate){
        return !this.entitysPosition.containsKey(coordinate);
    }

    public void putEntity(Coordinate coordinate, Entity entity){
       if(this.entitysPosition.containsKey(coordinate)){
           System.out.println("Coordinate is busy!");
       }
       this.entitysPosition.put(coordinate, entity);
    }

    public boolean movableEntitysExists (){
        Entity entity;
        for(Coordinate entityPosition : this.entitysPosition.keySet()) {
            entity = this.entitysPosition.get(entityPosition);
            if (entity instanceof Creature){
                return true;
            }
        }
        return false;
    }

    public int amountOf (EntityType type){
        int amount = 0;
        Entity entity;
        for(Coordinate entityPosition : this.entitysPosition.keySet()){
            entity = this.entitysPosition.get(entityPosition);
            if(entity.getClass().getSimpleName().equalsIgnoreCase(type.toString())){
                amount++;
            }
        }
        return amount;
    }

    public Set<Coordinate> getCreaturesPosition (){
        Set<Coordinate> creatures = new HashSet<>();
        Entity entity;

        for(Coordinate entityPosition : this.entitysPosition.keySet()){
            entity = this.entitysPosition.get(entityPosition);
            if (entity instanceof Creature) {
                creatures.add(entityPosition);
            }
        }
        return creatures;
    }
}
