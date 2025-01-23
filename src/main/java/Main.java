import Utils.Renderer;
import actions.*;
import entitys.Entity;
import entitys.*;
import entitys.creatures.Herbivore;
import entitys.creatures.Predator;
import entitys.creatures.Turtle;
import enviroment.*;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static final int INIT_GRASS = 10;
    public static final int INIT_ROCK = 20;
    public static final int INIT_TREE = 20;
    public static final int INIT_HERBIVORE = 7;
    public static final int INIT_PREDATOR = 2;
    public static final int INIT_TURTLE = 1;
    public static final int INIT_FISH = 1;

    public static final int WORLD_WIDTH = 15;
    public static final int WORLD_HEIGHT = 15;

    public static final int GAME_MIN_GRASS = 3;
    public static final int GAME_MAX_GRASS = 10;

    public static final int GAME_MAX_FISH = 1;

    public static final int GAME_MIN_HERBIVORE = 1;
    public static final int GAME_MAX_HERBIVORE = 6;

    public static void main (String[] args){
        List<Action> initActions = new ArrayList<>();
        try {
            initActions.add(new SpawnAction(Grass::new, INIT_GRASS));
            initActions.add(new SpawnAction(Rock::new, INIT_ROCK));
            initActions.add(new SpawnAction(Tree::new, INIT_TREE));
            initActions.add(new SpawnAction(Herbivore::new, INIT_HERBIVORE));
            initActions.add(new SpawnAction(Predator::new, INIT_PREDATOR));
            initActions.add(new SpawnAction(Turtle::new, INIT_TURTLE));
            initActions.add(new SpawnAction(Fish::new, INIT_FISH));
        } catch (WrongAmountOfArgumentsException e){
            e.printStackTrace();
        }
        Board<Entity> world = WorldFactory.create(WORLD_WIDTH, WORLD_HEIGHT, initActions);

        TurnCounter turnCounter = new TurnCounter();

        List<Action> actions = new ArrayList<>();
        actions.add(new MoveAction(turnCounter));
        actions.add(new BiteAction());
        try {
            actions.add(new SpawnAction(Grass::new, GAME_MAX_GRASS, GAME_MIN_GRASS));
            actions.add(new SpawnAction(Fish::new, GAME_MAX_FISH));
            actions.add(new SpawnAction(Herbivore::new, GAME_MAX_HERBIVORE, GAME_MIN_HERBIVORE));
        } catch (WrongAmountOfArgumentsException e){
            e.printStackTrace();
        }

        Renderer renderer = new Renderer();

        Simulation simulation = new Simulation(world, actions, renderer, turnCounter);
        Thread controller = new Thread(new SimulationControler(simulation));

        controller.start();
        simulation.start();
    }
}