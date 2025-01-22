package Utils;


import entitys.Entity;
import enviroment.Board;
import enviroment.Coordinate;
import enviroment.UndefinedEmojiForClassException;

public class Renderer {

    public static final String NO_EMOJI_MSG = "Emoji hasn't defined for Class ";

    public static final String ROCK_EMOJI = new String(Character.toChars(0x1F94C));
    public static final String TREE_EMOJI = new String(Character.toChars(0x1F332));
    public static final String GRASS_EMOJI = new String(Character.toChars(0x1F331));
    public static final String HERBIVORE_EMOJI = new String(Character.toChars(0x1F407));
    public static final String PREDATOR_EMOJI = new String(Character.toChars(0x1F43A));
    public static final String SPACE_EMOJI = new String(Character.toChars(0x2B1B));
    public static final String TURTLE_EMOJI = new String(Character.toChars(0x1F422));
    public static final String FISH_EMOJI = new String(Character.toChars(0x1F41F));

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
                    System.out.print(getEmoji(entity));
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
}
