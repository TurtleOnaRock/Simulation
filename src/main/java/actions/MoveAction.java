package actions;

import Utils.BFSSearcher;
import Utils.BoardUtils;
import enviroment.Board;
import enviroment.Coordinate;
import enviroment.TurnCounter;
import entitys.*;
import entitys.creatures.Creature;

import java.util.*;

public class MoveAction implements Action{

    public static final String MOVE_EXCEPTION_MSG = "ERROR: Trying to move into busy coordinate.";
    private TurnCounter turnCounter;

    public MoveAction(TurnCounter turnCounter){
        this.turnCounter = turnCounter;
    }

    public void perform (Board<Entity> world) {
        Set<Coordinate> creaturesPosition = BoardUtils.getCreaturesPosition(world);
        BFSSearcher pathFinder;
        Deque<Coordinate> pathToGoal;
        Coordinate moveTo;
        Creature creature;
        int time = this.turnCounter.getTurn();

        for(Coordinate moveFrom : creaturesPosition){
            creature = (Creature) world.get(moveFrom);
            if(time % creature.getSpeed() != 0){
                continue;
            }
            pathFinder = new BFSSearcher(world, moveFrom, creature.getGoal(), creature.getMoveType());
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

    private void makeMove(Board<Entity> world, Coordinate from, Coordinate to) throws MoveException{
        if(to.equals(from)){
            return;
        }
        if (world.coordinateIsBusy(to)){
            throw new MoveException(MOVE_EXCEPTION_MSG);
        }
        Entity entity;
        entity = world.get(from);
        world.put(to, entity);
        world.remove(from);
    }
}
