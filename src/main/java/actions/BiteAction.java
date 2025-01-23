package actions;

import utils.BFSSearcher;
import utils.BoardUtils;
import enviroment.Board;
import enviroment.Coordinate;
import entitys.Entity;
import entitys.creatures.Creature;

import java.util.Set;

public class BiteAction implements Action {

    public void perform (Board<Entity> world){
        Set<Coordinate> creaturesPosition = BoardUtils.getCreaturesPosition(world);
        Creature eater;
        Coordinate foodPosition;
        BFSSearcher searcher;

        for(Coordinate creaturePosition : creaturesPosition){
            if(world.coordinateIsEmpty(creaturePosition)){
                continue;
            }
            eater = (Creature) world.get(creaturePosition);
            searcher = new BFSSearcher(world, creaturePosition, eater.getGoal(), eater.getMoveType());
            foodPosition = searcher.findGoalAround();
            if(foodPosition.equals(creaturePosition)){                  //there is no Goal around
                continue;
            }
            bite(world, eater, foodPosition);
        }
    }

    private void bite(Board<Entity> world, Creature eater, Coordinate foodPosition){
        Entity food = world.get(foodPosition);
        if(food instanceof Creature){
            Creature meatFood = (Creature) food;
            meatFood.hpDown();
            if(meatFood.getHp() <= 0){
                world.remove(foodPosition);
            }
        } else {
            world.remove(foodPosition);
        }
        if(eater.getHp() < eater.getHpLimit()) {
            eater.hpUp();
        }
    }
}
