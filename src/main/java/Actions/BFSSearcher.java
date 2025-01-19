package Actions;

import Enviroment.Coordinate;
import Enviroment.World;
import objects.Movable.Creature;
import objects.Movable.MoveType;

import java.util.*;

public class BFSSearcher {

    public static final Coordinate INVALUED_COORDINATE = new Coordinate(-1, -1);

    private final World world;
    private final Coordinate start;
    private final String goal;
    private final Creature creature;
    private final MoveType moveType;
    private Coordinate locationOfGoal;
    private Coordinate point;

    public BFSSearcher(World world, Coordinate creaturePosition){
        this.world = world;
        this.start = creaturePosition;
        this.creature = (Creature) world.getEntity(creaturePosition);
        this.goal = this.creature.getGoal().toString();
        this.locationOfGoal = INVALUED_COORDINATE;
        this.moveType = this.creature.getMoveType();
    }

    public Deque<Coordinate> findPathToGoal(){
        Deque<Coordinate> queueToCheck = new LinkedList<>();
        Set<Coordinate> visited = new HashSet<>();
        Map<Coordinate, Coordinate> relations = new HashMap<>();
        List<Coordinate> coordinateToAdd;

        queueToCheck.add(this.start);
        while(!queueToCheck.isEmpty()){
            this.point = queueToCheck.pop();
            visited.add(this.point);
            if(this.world.coordinateIsBusy(this.point) && !point.equals(start)){
                if(world.getEntity(this.point).getClass().getSimpleName().equalsIgnoreCase(this.goal)){
                    this.locationOfGoal = this.point;
                    break;
                }
                continue;
            }

            if(this.moveType == MoveType.EIGHT_DIRECTIONS){
                coordinateToAdd = get8CoordinateAround(this.point);
            }else {
                coordinateToAdd = get4CoordinateAround(this.point);
            }
            queueToCheck.addAll(coordinateToAdd.stream()
                            .filter(c -> c.isValued(this.world.getMaxCoordinate()))
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
        if(this.locationOfGoal.equals(INVALUED_COORDINATE)){
            path.add(start);
            return path;
        }

        Coordinate ptr = this.locationOfGoal;
        path.add(locationOfGoal);

        // if goal near to start return only goal coordinate
        if(parents.get(locationOfGoal).equals(this.start)){
            return path;
        }

        // return chane of coordinate from 1st step to the goal, except start coordinate
        do {
            path.push(parents.get(ptr));
            ptr = parents.get(ptr);
        }while(!parents.get(ptr).equals(this.start));
        return path;
    }

    /**
     *          1
     *      4   b   2
     *          3
     */
    private List<Coordinate> get4CoordinateAround (Coordinate base) {
        List<Coordinate> container = new LinkedList<>();
        container.add(base.change(-1, 0));
        container.add(base.change(0, 1));
        container.add(base.change(1, 0));
        container.add(base.change(0, -1));
        return container;
    }

    /**
     *      1   2   3
     *      8   b   4
     *      7   6   5
     */
    private List<Coordinate> get8CoordinateAround (Coordinate base) {
        List<Coordinate> container = new LinkedList<>();
        container.add(base.change(-1, -1));
        container.add(base.change(-1, 0));
        container.add(base.change(-1, 1));
        container.add(base.change(0, 1));
        container.add(base.change(1, 1));
        container.add(base.change(1, 0));
        container.add(base.change(1, -1));
        container.add(base.change(0, -1));
        return container;
    }

    public Coordinate findGoalAround(){
        List<Coordinate> coordinatesToCheck;
        if(creature.getMoveType() == MoveType.EIGHT_DIRECTIONS){
            coordinatesToCheck = get8CoordinateAround(this.start);
        }else {
            coordinatesToCheck = get4CoordinateAround(this.start);
        }
        Coordinate result = coordinatesToCheck.stream()
                .filter( c -> c.isValued(this.world.getMaxCoordinate()))
                .filter( c -> this.world.coordinateIsBusy(c))
                .filter( c -> this.world.getEntity(c).getClass().getSimpleName().equalsIgnoreCase(this.goal))
                .findFirst().orElse(this.start);
        return result;
    }
}


