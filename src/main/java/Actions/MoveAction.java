package Actions;

import Enviroment.Coordinate;
import Enviroment.World;
import objects.*;
import objects.Movable.Creature;

import java.util.*;

public class MoveAction implements Action{

    public static final String MOVE_EXCEPTION_MSG = "ERROR: Trying to move into busy coordinate.";

    public void perform (World world) {
        Set<Coordinate> creaturesPosition = world.getCreaturesPosition();
        BFSSearcher pathFinder;
        Deque<Coordinate> pathToGoal;
        Coordinate moveTo;
        Creature creature;
        int time = world.getTime();

        for(Coordinate moveFrom : creaturesPosition){
            creature = (Creature) world.getEntity(moveFrom);
            if(time % creature.getSpeed() != 0){
                continue;
            }
            pathFinder = new BFSSearcher(world, moveFrom);
            pathToGoal = pathFinder.findPathToGoal();
            if(pathToGoal.size() == 1 ){                  //no goal or goal is close and ready to be bitten
                continue;
            }
            moveTo = pathToGoal.pop();
            try {
                makeMove(world, moveFrom, moveTo);
            } catch (MoveException e){
                e.printStackTrace();
            }
        }
    }

    private void makeMove(World world, Coordinate from, Coordinate to) throws MoveException{
        if(to.equals(from)){
            return;
        }
        if (world.coordinateIsBusy(to)){
            throw new MoveException(MOVE_EXCEPTION_MSG);
        }
        Entity entity;
        entity = world.getEntity(from);
        world.putEntity(to, entity);
        world.removeEntity(from);
    }
}
