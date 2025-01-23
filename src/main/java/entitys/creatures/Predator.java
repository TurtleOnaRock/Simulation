package entitys.creatures;

public class Predator extends Creature{

    public static final int DEFAULT_HP = 3;
    public static final int DEFAULT_HP_LIMIT = 5;
    public static final Class DEFAULT_GOAL = Herbivore.class;
    public static final int DEFAULT_SPEED = 2;
    public static final MoveType DEFAULT_MOVE_TYPE = MoveType.EIGHT_DIRECTIONS;

    public Predator(){
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
