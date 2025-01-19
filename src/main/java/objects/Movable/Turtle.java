package objects.Movable;

import objects.EntityType;

public class Turtle extends Creature {

    public static final int DEFAULT_HP = 100;
    public static final int DEFAULT_HP_LIMIT = 1000;
    public static final EntityType DEFAULT_GOAL = EntityType.FISH;
    public static final int DEFAULT_SPEED = 5;
    public static final MoveType DEFAULT_MOVE_TYPE = MoveType.FOUR_DIRECTIONS;

    public Turtle (){
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
