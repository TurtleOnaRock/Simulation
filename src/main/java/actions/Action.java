package actions;

import entitys.Entity;
import enviroment.Board;

public interface Action {

    void perform (Board<Entity> world);
}
