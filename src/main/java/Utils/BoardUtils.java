package Utils;

import entitys.Entity;
import entitys.creatures.Creature;
import enviroment.Board;
import enviroment.Coordinate;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class BoardUtils {

    public static int amountOf(Board<Entity> board, Class clazz) {
        int amount = 0;
        Entity entity;
        for (Coordinate entityPosition : board.getPositions()) {
            entity = board.get(entityPosition);
            if (entity.getClass() == clazz) {
                amount++;
            }
        }
        return amount;
    }

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

    public static boolean isValidCoordinate(Board<Entity> board, Coordinate coordinate) {
        if( (coordinate.getWidth() > board.getWidth()) || (coordinate.getHeight() > board.getHeight()) ){
            return false;
        }
        if( (coordinate.getWidth() < 1) || (coordinate.getHeight() < 1) ) {
            return false;
        }
        return true;
    }

    public static Coordinate getRandomEmptyCoordinate(Board<Entity> world){
        Random random = new Random(System.currentTimeMillis());
        int maxWidht = world.getWidth();
        int maxHeight = world.getHeight();
        Coordinate randomCoordinate;
        do{
            randomCoordinate = new Coordinate(random.nextInt(maxWidht), random.nextInt(maxHeight));
        } while (!validEmptyCoordinate(world, randomCoordinate));
        return randomCoordinate;
    }

    private static boolean validEmptyCoordinate(Board<Entity> world, Coordinate coordinate){
        return isValidCoordinate(world, coordinate) && world.coordinateIsEmpty(coordinate);
    }
}
