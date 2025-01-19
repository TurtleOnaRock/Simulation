package Enviroment;

import Actions.Action;

import java.util.List;

public class WorldFactory {

    public static World create(int width, int heigth, List<Action> initAction){
        World world = new World(width, heigth);

        for(Action action : initAction) {
            action.perform(world);
        }
        return world;
    }

}
