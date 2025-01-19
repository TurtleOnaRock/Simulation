package objects.Movable;

import objects.EntityType;

public class Herbivore extends Creature{

    public static final int DEFAULT_HP = 3;
    public static final int DEFAULT_HP_LIMIT = 3;
    public static final EntityType DEFAULT_GOAL = EntityType.GRASS;
    public static final int DEFAULT_SPEED = 1;
    public static final MoveType DEFAULT_MOVE_TYPE = MoveType.FOUR_DIRECTIONS;

    public Herbivore (){
        super(DEFAULT_HP,
                DEFAULT_HP_LIMIT,
                DEFAULT_GOAL,
                DEFAULT_SPEED,
                DEFAULT_MOVE_TYPE);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
