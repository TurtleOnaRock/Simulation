package actions;

import Utils.BoardUtils;
import enviroment.Coordinate;
import entitys.*;
import enviroment.Board;

import java.util.function.Supplier;

public class SpawnAction implements Action{
    private final int minAmount;
    private final int maxAmount;
    private final Supplier<Entity> supplier;


    public SpawnAction (Supplier<Entity> supplier, int min, int max){
        this.minAmount = min;
        this.maxAmount = max;
        this.supplier = supplier;
    }

    public void perform(Board<Entity> world) {
        int amount = BoardUtils.amountOf(world, supplier.get().getClass());
        Entity entity;
        Coordinate coordinate;
        if(amount < this.minAmount){
            for(int i = amount; i < this.maxAmount; i++ ){
                entity = supplier.get();
                coordinate = BoardUtils.getRandomEmptyCoordinate(world);
                world.put(coordinate, entity);
            }
        }
    }

}
