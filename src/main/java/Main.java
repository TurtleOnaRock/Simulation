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

    public static final int INIT_MIN_AMOUNT = 1;
    public static final int INIT_GRASS_AMOUNT = 10;
    public static final int INIT_ROCK_AMOUNT = 20;
    public static final int INIT_TREE_AMOUNT = 20;
    public static final int INIT_HERBIVORE_AMOUNT = 8;
    public static final int INIT_PREDATOR_AMOUNT = 2;
    public static final int INIT_TURTLE_AMOUNT = 1;
    public static final int INIT_FISH_AMOUNT = 1;

    public static final int WORLD_SIZE_WIDTH = 15;
    public static final int WORLD_SIZE_HEIGHT = 10;

    public static final int GAME_MIN_GRASS_AMOUNT = 3;
    public static final int GAME_MAX_GRASS_AMOUNT = 7;

    public static final int GAME_MIN_FISH_AMOUNT = 1;
    public static final int GAME_MAX_FISH_AMOUNT = 1;

    public static final int GAME_MIN_HERBIVORE_AMOUNT = 1;
    public static final int GAME_MAX_HERBIVORE_AMOUNT = 6;

    public static void main (String[] args){
        List<Action> initActions = new ArrayList<>();
        initActions.add(new SpawnAction(Grass::new, INIT_MIN_AMOUNT, INIT_GRASS_AMOUNT));
        initActions.add(new SpawnAction(Rock::new, INIT_MIN_AMOUNT, INIT_ROCK_AMOUNT));
        initActions.add(new SpawnAction(Tree::new, INIT_MIN_AMOUNT, INIT_TREE_AMOUNT));
        initActions.add(new SpawnAction(Herbivore::new, INIT_MIN_AMOUNT , INIT_HERBIVORE_AMOUNT));
        initActions.add(new SpawnAction(Predator::new, INIT_MIN_AMOUNT, INIT_PREDATOR_AMOUNT));
        initActions.add(new SpawnAction(Turtle::new, INIT_TURTLE_AMOUNT, INIT_TURTLE_AMOUNT));
        initActions.add(new SpawnAction(Fish::new, INIT_MIN_AMOUNT, INIT_FISH_AMOUNT));

        Board<Entity> world = WorldFactory.create(WORLD_SIZE_WIDTH, WORLD_SIZE_HEIGHT, initActions);

        TurnCounter turnCounter = new TurnCounter();

        List<Action> actions = new ArrayList<>();
        actions.add(new MoveAction(turnCounter));
        actions.add(new BiteAction());
        actions.add(new SpawnAction(Grass::new, GAME_MIN_GRASS_AMOUNT, GAME_MAX_GRASS_AMOUNT));
        actions.add(new SpawnAction(Fish::new, GAME_MIN_FISH_AMOUNT, GAME_MAX_FISH_AMOUNT));
        actions.add(new SpawnAction(Herbivore::new, GAME_MIN_HERBIVORE_AMOUNT, GAME_MAX_HERBIVORE_AMOUNT));

        Renderer renderer = new Renderer();

        Simulation simulation = new Simulation(world, actions, renderer, turnCounter);
        Thread controller = new Thread(new SimulationControler(simulation));

        controller.start();
        simulation.start();
    }
}