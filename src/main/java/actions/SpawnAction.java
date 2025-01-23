package actions;

import utils.BoardUtils;
import enviroment.Coordinate;
import entitys.*;
import enviroment.Board;

import java.util.Random;
import java.util.function.Supplier;

public class SpawnAction implements Action{

    private static final int DEFAULT_MIN_AMOUNT = 1;

    private final int minAmount;
    private final int maxAmount;
    private final Supplier<Entity> supplier;


    public SpawnAction (Supplier<Entity> supplier, int ... amount) throws WrongAmountOfArgumentsException {
        this.supplier = supplier;
        this.maxAmount = amount[0];
        if(amount.length == 1){
            this.minAmount = DEFAULT_MIN_AMOUNT;
        } else if(amount.length == 2){
            this.minAmount = amount[1];
        } else {
            throw new WrongAmountOfArgumentsException();
        }
    }

    public void perform(Board<Entity> world) {
        int amount = amountOf(world, supplier.get().getClass());
        Entity entity;
        Coordinate coordinate;
        if(amount < this.minAmount){
            for(int i = amount; i < this.maxAmount; i++ ){
                entity = supplier.get();
                coordinate = getRandomEmptyCoordinate(world);
                world.put(coordinate, entity);
            }
        }
    }

    private int amountOf(Board<Entity> board, Class clazz) {
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

    private Coordinate getRandomEmptyCoordinate(Board<Entity> world){
        Random random = new Random(System.currentTimeMillis());
        int maxWidht = world.getWidth();
        int maxHeight = world.getHeight();
        Coordinate randomCoordinate;
        do{
            randomCoordinate = new Coordinate(random.nextInt(maxWidht), random.nextInt(maxHeight));
        } while (!validEmptyCoordinate(world, randomCoordinate));
        return randomCoordinate;
    }

    private boolean validEmptyCoordinate(Board<Entity> world, Coordinate coordinate){
        return BoardUtils.isValidCoordinate(world, coordinate) && world.coordinateIsEmpty(coordinate);
    }

}
