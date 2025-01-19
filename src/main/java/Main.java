import Actions.*;
import Enviroment.Renderer;
import Enviroment.World;
import Enviroment.WorldFactory;
import objects.EntityType;

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

    public static final int WORLD_SIZE_X = 15;
    public static final int WORLD_SIZE_Y = 10;

    public static final int GAME_MIN_GRASS_AMOUNT = 3;
    public static final int GAME_MAX_GRASS_AMOUNT = 7;

    public static final int GAME_MIN_FISH_AMOUNT = 1;
    public static final int GAME_MAX_FISH_AMOUNT = 1;

    public static final int GAME_MIN_HERBIVORE_AMOUNT = 1;
    public static final int GAME_MAX_HERBIVORE_AMOUNT = 6;


    public static void main (String[] args){
        List<Action> initAction = new ArrayList<>();
        initAction.add(new SpawnAction(EntityType.GRASS, INIT_MIN_AMOUNT, INIT_GRASS_AMOUNT));
        initAction.add(new SpawnAction(EntityType.ROCK, INIT_MIN_AMOUNT, INIT_ROCK_AMOUNT));
        initAction.add(new SpawnAction(EntityType.TREE, INIT_MIN_AMOUNT, INIT_TREE_AMOUNT));
        initAction.add(new SpawnAction(EntityType.HERBIVORE, INIT_MIN_AMOUNT , INIT_HERBIVORE_AMOUNT));
        initAction.add(new SpawnAction(EntityType.PREDATOR, INIT_MIN_AMOUNT, INIT_PREDATOR_AMOUNT));
        initAction.add(new SpawnAction(EntityType.TURTLE, INIT_TURTLE_AMOUNT, INIT_TURTLE_AMOUNT));
        initAction.add(new SpawnAction(EntityType.FISH, INIT_MIN_AMOUNT, INIT_FISH_AMOUNT));
        World world = WorldFactory.create(WORLD_SIZE_X, WORLD_SIZE_Y, initAction);

        List<Action> actions = new ArrayList<>();
        actions.add(new MoveAction());
        actions.add(new BiteAction());
        actions.add(new SpawnAction(EntityType.GRASS, GAME_MIN_GRASS_AMOUNT, GAME_MAX_GRASS_AMOUNT));
        actions.add(new SpawnAction(EntityType.FISH, GAME_MIN_FISH_AMOUNT, GAME_MAX_FISH_AMOUNT));
        actions.add(new SpawnAction(EntityType.HERBIVORE, GAME_MIN_HERBIVORE_AMOUNT, GAME_MAX_HERBIVORE_AMOUNT));

        Renderer renderer = new Renderer();

        Simulation simulation = new Simulation(world, actions, renderer);
        Thread controller = new Thread(new SimulationControler(simulation));

        controller.start();
        simulation.start();
    }
}