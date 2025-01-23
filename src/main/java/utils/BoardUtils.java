package Utils;

import entitys.Entity;
import entitys.creatures.Creature;
import enviroment.Board;
import enviroment.Coordinate;

import java.util.HashSet;
import java.util.Set;

public class BoardUtils {

    public static Set<Coordinate> getCreaturesPosition(Board<Entity> board) {
        Set<Coordinate> creatures = new HashSet<>();
        Entity entity;
        for (Coordinate entityPosition : board.getPositions()) {
            entity = board.get(entityPosition);
            if (entity instanceof Creature) {
                creatures.add(entityPosition);
            }
        }
        return creatures;
    }

    public static <T> boolean isValidCoordinate(Board<T> board, Coordinate coordinate) {
        if( (coordinate.getWidth() > board.getWidth()) || (coordinate.getHeight() > board.getHeight()) ){
            return false;
        }
        if( (coordinate.getWidth() < 1) || (coordinate.getHeight() < 1) ) {
            return false;
        }
        return true;
    }

}
