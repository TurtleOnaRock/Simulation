package Actions;

import Enviroment.Coordinate;
import Enviroment.World;
import objects.Entity;
import objects.Movable.Creature;

import java.util.Set;

public class BiteAction implements Action {

    public void perform (World world){
        Set<Coordinate> creaturesPosition = world.getCreaturesPosition();
        Creature eater;
        Coordinate foodPosition;
        BFSSearcher searcher;

        for(Coordinate creaturePosition : creaturesPosition){
            if(world.coordinateIsEmpty(creaturePosition)){
                continue;
            }
            eater = (Creature) world.getEntity(creaturePosition);
            searcher = new BFSSearcher(world, creaturePosition);
            foodPosition = searcher.findGoalAround();
            if(foodPosition.equals(creaturePosition)){                  //there is no Goal around
                continue;
            }
            bite(world, eater, foodPosition);
        }
    }

    private void bite(World world, Creature eater, Coordinate foodPosition){
        Entity food = world.getEntity(foodPosition);
        if(food instanceof Creature){
            Creature meatFood = (Creature) food;
            meatFood.hpDown();
            if(meatFood.getHp() <= 0){
                world.removeEntity(foodPosition);
            }
        } else {
            world.removeEntity(foodPosition);
        }
        if(eater.getHp() < eater.getHpLimit()) {
            eater.hpUp();
        }
    }
}
