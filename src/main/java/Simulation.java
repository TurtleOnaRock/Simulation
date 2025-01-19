import Actions.Action;
import Enviroment.World;
import Enviroment.Renderer;

import java.util.List;
import java.util.Scanner;

public class Simulation {

    public static final String CONTROL_PANEL = "Press 'P' to pause, Press 'C' to continue, Press 'Q' to quit simulation.";
    public static final String COUNTER_MSG = "Turn: ";
    public static final String END_MSG = "Simulation successfully completed!";

    public static final long SLEEP_DURATION = 500;

    private final World world;
    private final List<Action> turnAction;
    private final Renderer renderer;
    private final Scanner scanner;
    private boolean statePause;
    private boolean stateRun;

    public Simulation(World world, List<Action> actions, Renderer renderer) {
        this.world = world;
        this.turnAction = actions;
        this.renderer = renderer;
        this.scanner = new Scanner(System.in);
        this.statePause = false;
        this.stateRun = true;
    }

    public void start() {
        while (running()) {
            renderWorld();
            printControlPanel();
            do {
                try {
                    Thread.sleep(SLEEP_DURATION);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }while(this.statePause);
            nextTurn();
        }
        System.out.println(END_MSG);
    }

    public void nextTurn() {
        for (Action action : turnAction) {
            action.perform(this.world);
        }
        this.world.timeUp();
    }

    public void renderWorld() {
        this.renderer.clean();
        this.renderer.showMap(this.world);
        System.out.println(COUNTER_MSG + this.world.getTime());
    }

    public void printControlPanel() {
        System.out.println(CONTROL_PANEL);
    }

    public void setPause(boolean status) {
       this.statePause = status;
    }

    private boolean running(){
        return stateRun && world.movableEntitysExists();
    }

    public void endSimulation(){
        this.stateRun = false;
    }

    public boolean getStateRun(){
        return stateRun;
    }
}