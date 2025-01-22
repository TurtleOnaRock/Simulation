package entitys.creatures;

import entitys.Entity;

public abstract class Creature extends Entity {

    private int hp;
    private Class goal;                //food type
    private int speed;                      // how many days are needed to make 1 step
    private int hpLimit;
    private MoveType moveType;

    public Creature(int hp, int hpLimit, Class goal, int speed, MoveType moveType) {
        this.hp = hp;
        this.hpLimit = hpLimit;
        this.goal = goal;
        this.speed = speed;
        this.moveType = moveType;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public Class getGoal(){
        return this.goal;
    }

    public MoveType getMoveType(){
        return this.moveType;
    }

    public int getHpLimit(){
        return this.hpLimit;
    }

    public void hpUp() {
        this.hp++;
    }

    public void hpDown() {
        this.hp--;
    }

    public int getHp(){
        return this.hp;
    }

    public int getSpeed(){
        return this.speed;
    }
}
