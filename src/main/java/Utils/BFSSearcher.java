package Utils;

import entitys.Entity;
import entitys.creatures.MoveType;
import enviroment.Coordinate;
import enviroment.Board;

import java.util.*;

public class BFSSearcher {

    public static final Coordinate INVALUED_COORDINATE = new Coordinate(-1, -1);

    private final Board<Entity> world;
    private final Coordinate searchFrom;
    private final Class target;
    private final MoveType moveType;
    private Coordinate locationOfTarget;
    private Coordinate point;

    public BFSSearcher(Board<Entity> world, Coordinate searchFrom, Class target, MoveType moveType){
        this.world = world;
        this.searchFrom = searchFrom;
        this.target = target;
        this.moveType = moveType;
        this.locationOfTarget = INVALUED_COORDINATE;
    }

    public Deque<Coordinate> findPathToGoal(){
        Deque<Coordinate> queueToCheck = new LinkedList<>();
        Set<Coordinate> visited = new HashSet<>();
        Map<Coordinate, Coordinate> relations = new HashMap<>();
        List<Coordinate> coordinatesToAdd;

        queueToCheck.add(this.searchFrom);
        while(!queueToCheck.isEmpty()){
            this.point = queueToCheck.pop();
            visited.add(this.point);
            if(coordinateHasAnotherEntity()){
                if(world.get(this.point).getClass() == this.target){
                    this.locationOfTarget = this.point;
                    break;
                }
                continue;
            }

            if(this.moveType == MoveType.EIGHT_DIRECTIONS){
                coordinatesToAdd = get8CoordinatesAround(this.point);
            }else {
                coordinatesToAdd = get4CoordinatesAround(this.point);
            }
            queueToCheck.addAll(coordinatesToAdd.stream()
                            .filter(c -> BoardUtils.isValidCoordinate(world, c))
                            .filter(c -> !visited.contains(c))
                            .peek(c-> {
                                if(!relations.containsKey(c)){
                                    relations.put(c,this.point);
                                }
                            })
                            .toList());
        }
        return getPath(relations);
    }

    private boolean coordinateHasAnotherEntity(){
        return this.world.coordinateIsBusy(this.point) && !this.point.equals(this.searchFrom);
    }

    /**
     *  getPath
     * @return
     *      If there is not any ways to the goal, getPath returns only coordinate of "Hunter"
     *      If the goal is close to "Hunter", getPath returns only coordinate of the "Goal"
     *      If the goal is over some steps, getPath return coordinates of all steps
     *                                                      and coordinate of "Goal" at the end of queue;
     */
    private Deque<Coordinate> getPath(Map<Coordinate, Coordinate> parents){
        Deque<Coordinate> path = new LinkedList<>();
        // if no way to goal, return entity's coordination
        if(this.locationOfTarget.equals(INVALUED_COORDINATE)){
            path.add(searchFrom);
            return path;
        }

        Coordinate ptr = this.locationOfTarget;
        path.add(locationOfTarget);

        // if goal near to start return only goal coordinate
        if(parents.get(locationOfTarget).equals(this.searchFrom)){
            return path;
        }

        // return chane of coordinate from 1st step to the goal, except start coordinate
        do {
            path.push(parents.get(ptr));
            ptr = parents.get(ptr);
        }while(!parents.get(ptr).equals(this.searchFrom));
        return path;
    }

    /**
     *          1
     *      4   b   2
     *          3
     */
    private List<Coordinate> get4CoordinatesAround (Coordinate base) {
        List<Coordinate> coordinates = new LinkedList<>();
        coordinates.add(base.add(0, -1));
        coordinates.add(base.add(1, 0));
        coordinates.add(base.add(0, 1));
        coordinates.add(base.add(-1, 0));
        return coordinates;
    }

    /**
     *      1   2   3
     *      8   b   4
     *      7   6   5
     */
    private List<Coordinate> get8CoordinatesAround (Coordinate base) {
        List<Coordinate> container = new LinkedList<>();
        container.add(base.add(-1, -1));
        container.add(base.add(0, -1));
        container.add(base.add(1, -1));
        container.add(base.add(1, 0));
        container.add(base.add(1, 1));
        container.add(base.add(0, 1));
        container.add(base.add(-1, 1));
        container.add(base.add(-1, 0));
        return container;
    }

    public Coordinate findGoalAround(){
        List<Coordinate> coordinatesToCheck;
        if(this.moveType == MoveType.EIGHT_DIRECTIONS){
            coordinatesToCheck = get8CoordinatesAround(this.searchFrom);
        }else {
            coordinatesToCheck = get4CoordinatesAround(this.searchFrom);
        }
        Coordinate result = coordinatesToCheck.stream()
                .filter( c -> BoardUtils.isValidCoordinate(world, c))
                .filter( c -> this.world.coordinateIsBusy(c))
                .filter( c -> this.world.get(c).getClass() == this.target)
                .findFirst().orElse(this.searchFrom);
        return result;
    }
}


