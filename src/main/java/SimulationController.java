import java.util.Scanner;

public class SimulationController implements Runnable{

    public static final int ONE_CHAR_STRING = 1;

    public static final String UNKNOWN_COMMAND_MSG = "Unknown command, try again!";
    public static final String WRONG_COMMAND_FORMAT = "Command to long! Use only 1 letter!";

    private final Simulation simulation;
    private final Scanner scanner;

    public SimulationController(Simulation simulation){
        this.simulation = simulation;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void run() {
        char ch;
        while(this.simulation.getStateRun()) {
            ch = getChar();
            switch (ch) {
                case SimulationCommands.PAUSE_CHAR -> this.simulation.setPause(true);
                case SimulationCommands.GOINGON_CHAR -> this.simulation.setPause(false);
                case SimulationCommands.QUIT_CHAR -> this.simulation.endSimulation();
                default -> System.out.print(UNKNOWN_COMMAND_MSG);
            }
        }
    }

    private char getChar(){
        String input;
        do {
            input = this.scanner.nextLine();
            if(input.length() > ONE_CHAR_STRING){
                System.out.println(WRONG_COMMAND_FORMAT);
            }
        }while(input.length() != ONE_CHAR_STRING);
        return input.toUpperCase().charAt(0);
    }
}
