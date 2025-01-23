package utils;


import entitys.Entity;
import entitys.creatures.Creature;
import enviroment.Board;
import enviroment.Coordinate;
import enviroment.UndefinedEmojiForClassException;

public class Renderer {

    public static final String NO_EMOJI_MSG = "Emoji hasn't defined for Class ";

    public static final String ROCK_EMOJI = "🥌";
    public static final String TREE_EMOJI = "🌲";
    public static final String GRASS_EMOJI = "🌱";
    public static final String HERBIVORE_EMOJI = "🐇";
    public static final String PREDATOR_EMOJI = "🐺";
    public static final String SPACE_EMOJI = "⬛";
    public static final String TURTLE_EMOJI = "🐢";
    public static final String FISH_EMOJI = "🐟";

    public static final String RED_BACKGROUND = "\u001B[41m";
    public static final String BLUE_BACKGROUND = "\u001B[44m";
    public static final String DEFAULT_BACKGROUND = "\u001B[0m";


    public static final String CLEAN_TERMINAL = "\u001Bc";

    public static final char NEW_LINE = '\n';

    public void showMap(Board<Entity> world)
    {
        Entity entity;
        for(int row = 1; row <= world.getHeight(); row++){
            for(int column = 1; column <= world.getWidth(); column++){
                if(world.coordinateIsEmpty(new Coordinate(column, row))){
                    System.out.print(SPACE_EMOJI);
                } else {
                    entity = world.get(new Coordinate(column, row));
                    if(entity instanceof Creature){
                       setBackground(((Creature) entity).getHp());
                    }
                    System.out.print(getEmoji(entity));
                    System.out.print(DEFAULT_BACKGROUND);
                }
            }
            System.out.print(NEW_LINE);
        }
    }

    private String getEmoji(Entity entity){
         return switch(entity.getClass().getSimpleName()){
            case "Tree" ->  TREE_EMOJI;
            case "Grass" -> GRASS_EMOJI;
            case "Rock" -> ROCK_EMOJI;
            case "Herbivore" -> HERBIVORE_EMOJI;
            case "Predator" -> PREDATOR_EMOJI;
            case "Turtle" -> TURTLE_EMOJI;
            case "Fish" -> FISH_EMOJI;
            default -> throw new UndefinedEmojiForClassException(NO_EMOJI_MSG + entity.getClass());
         };
    }

    public void clean () {
        System.out.print(CLEAN_TERMINAL);
    }

    private void setBackground(int hpValue){
        switch(hpValue) {
            case 1 -> System.out.print(RED_BACKGROUND);
            case 2 -> System.out.print(BLUE_BACKGROUND);
            default -> System.out.print(DEFAULT_BACKGROUND);
        }
    }
}
