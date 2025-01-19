package Actions;

import Enviroment.Coordinate;
import Enviroment.World;
import objects.Entity;
import objects.EntityFactory;
import objects.EntityType;

public class SpawnAction implements Action{
    private final int minAmount;
    private final int maxAmount;
    private final EntityType type;


    public SpawnAction (EntityType type, int min, int max){
        this.minAmount = min;
        this.maxAmount = max;
        this.type = type ;
    }

    public void perform(World world) {
        int amount = world.amountOf(this.type);
        Entity entity;
        Coordinate coordinate;
        if(amount < this.minAmount){
            for(int i = amount; i < this.maxAmount; i++ ){
                entity = EntityFactory.create(this.type);
                do {
                    coordinate = Coordinate.getRandomCoordinate(world.getMaxCoordinate());
                }while(world.coordinateIsBusy(coordinate));
                world.putEntity(coordinate, entity);
            }
        }
    }
}
