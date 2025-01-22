package enviroment;

import actions.Action;
import entitys.Entity;

import java.util.List;

public class WorldFactory {

    public static Board<Entity> create(int width, int heigth, List<Action> initActions){
        Board<Entity> world = new Board<>(width, heigth);

        for(Action action : initActions) {
            action.perform(world);
        }
        return world;
    }

}
