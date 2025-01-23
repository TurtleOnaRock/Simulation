import actions.Action;
import entitys.Entity;
import enviroment.Board;
import enviroment.TurnCounter;
import utils.Renderer;

import java.util.List;

public class Simulation {

    public static final String ENTER_MSG = "Enter '";
    public static final String CONTROL_PANEL_PAUSE = "' to pause Simulation.";
    public static final String CONTROL_PANEL_CONTINUE = "' to continue Simulation.";
    public static final String CONTROL_PANEL_QUIT = "' to quit Simulation.";
    public static final String COUNTER_MSG = "Turn: ";
    public static final String END_MSG = "Simulation successfully completed!";

    public static final long SLEEP_DURATION = 500;

    private final Board<Entity> world;
    private final List<Action> turnActions;
    private final Renderer renderer;
    private final TurnCounter turnCounter;
    private boolean statePause;
    private boolean stateRun;

    public Simulation(Board<Entity> world, List<Action> actions, Renderer renderer, TurnCounter turnCounter) {
        this.world = world;
        this.turnActions = actions;
        this.renderer = renderer;
        this.statePause = false;
        this.stateRun = true;
        this.turnCounter = turnCounter;
    }

    public void start() {
        while (stateRun) {
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
        for (Action action : turnActions) {
            action.perform(this.world);
        }
        this.turnCounter.up();
    }

    public void renderWorld() {
        this.renderer.clean();
        this.renderer.showMap(this.world);
        System.out.println(COUNTER_MSG + this.turnCounter.getTurn());
    }

    public void printControlPanel() {
        System.out.println(ENTER_MSG + SimulationCommands.PAUSE_CHAR + CONTROL_PANEL_PAUSE);
        System.out.println(ENTER_MSG + SimulationCommands.GOINGON_CHAR + CONTROL_PANEL_CONTINUE);
        System.out.println(ENTER_MSG + SimulationCommands.QUIT_CHAR + CONTROL_PANEL_QUIT);
    }

    public void setPause(boolean status) {
       this.statePause = status;
    }

    public void endSimulation(){
        this.stateRun = false;
    }

    public boolean getStateRun(){
        return stateRun;
    }
}