package enviroment;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Board<T> {

    public static final String EMPTY_SPACE_MSG = "ERROR: trying to get Entity from empty coordinate: ";
    public static final String BUSY_SPACE_MSG = "ERROR: Trying to put thing into busy coordinate: ";
    public static final String OUT_BOARD_MSG = "ERROR: Trying to put thing out of board space: ";
    public static final String BOARD_SIZE_MSG = "Board size: ";
    public static final String COORDINATE_MSG = "coordinate: ";

    private final int width;
    private final int height;
    private Map<Coordinate, T> things;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.things = new HashMap<Coordinate, T>();
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public void put(Coordinate coordinate, T thing) throws OutOfBoardException, BusyCoordinateException{
        if(isOutside(coordinate)){
            throw new OutOfBoardException(OUT_BOARD_MSG
                    + BOARD_SIZE_MSG + this.width + "x" + this.height + " "
                    + COORDINATE_MSG + coordinate);
        }
        if(this.things.containsKey(coordinate)){
            throw new BusyCoordinateException(BUSY_SPACE_MSG + thing);
        }
        this.things.put(coordinate, thing);
    }

    public T get(Coordinate coordinate){
        if(coordinateIsEmpty(coordinate)){
            throw new EmptyCoordinateException(EMPTY_SPACE_MSG + coordinate);
        }
        return things.get(coordinate);
    }

    public void remove(Coordinate coordinate){
        things.remove(coordinate);
    }

    public Set<Coordinate> getPositions(){
        return this.things.keySet();
    }

    public boolean coordinateIsBusy(Coordinate coordinate){
       return this.things.containsKey(coordinate);
    }

    public boolean coordinateIsEmpty(Coordinate coordinate){
        return !coordinateIsBusy(coordinate);
    }

    private boolean isOutside(Coordinate coordinate){
        return  coordinate.getWidth() < 1 ||
                coordinate.getHeight() < 1 ||
                coordinate.getWidth() > this.width ||
                coordinate.getHeight() > this.height;
    }
}

