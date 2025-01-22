package enviroment;

public class TurnCounter {

    public static final int INIT_VALUE = 0;
    private int turnCounter;

    public TurnCounter(){
        this.turnCounter = INIT_VALUE;
    }

    public int getTurn(){
        return this.turnCounter;
    }

    public void up(){
        this.turnCounter++;
    }
}
