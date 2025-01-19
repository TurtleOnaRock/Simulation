package Enviroment;

import objects.Entity;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

public class Renderer {
    public static final char ROCK_CHAR = 'R';
    public static final char TREE_CHAR = 'T';
    public static final char GRASS_CHAR = 'G';
    public static final char HERBIVORE_CHAR = 'H';
    public static final char PREDATOR_CHAR = 'P';
    public static final char SPACE_CHAR = ' ';
    public static final char TURTLE_CHAT = 'U';
    public static final char FISH_CHAR = 'F';

    public static final String ROCK_EMOJI = new String(Character.toChars(0x1F94C));
    public static final String TREE_EMOJI = new String(Character.toChars(0x1F332));
    public static final String GRASS_EMOJI = new String(Character.toChars(0x1F331));
    public static final String HERBIVORE_EMOJI = new String(Character.toChars(0x1F407));
    public static final String PREDATOR_EMOJI = new String(Character.toChars(0x1F43A));
    public static final String SPACE_EMOJI = new String(Character.toChars(0x2B1B));
    public static final String TURTLE_EMOJI = new String(Character.toChars(0x1F422));
    public static final String FISH_EMOJI = new String(Character.toChars(0x1F41F));

    public static final String BACKGROUND_RED = "\u001B[41m";
    public static final String BACKGROUND_GREEN = "\u001B[42m";
    public static final String BACKGROUND_BROWN = "\u001B[43m";
    public static final String BACKGROUND_DEFAULT = "\u001B[49m";

    public static final String CLEAN_TERMINAL = "\u001Bc";

    public static final char NEW_LINE = '\n';

    public void showMap(World world)
    {
        char[][] worldAsCharArray = transformToArray(world);
        drawMapEmoji(worldAsCharArray);
    }

    public void clean () {
        System.out.print(CLEAN_TERMINAL);
    }

    private char[][] transformToArray (World world)
    {
        char [][] worldAsArray = new char[world.getHeight()][world.getWidth()];
        for(int i = 0 ; i < worldAsArray.length; i++){
            Arrays.fill(worldAsArray[i], SPACE_CHAR);
        }
        Coordinate currentCoordinate;
        Entity currentEntity;
        char signOfEntity;

        Set<Coordinate> catalogOfEntitys = world.getEntitysPosition();
        Iterator<Coordinate> iteratorOfEntitys = catalogOfEntitys.iterator();

        while(iteratorOfEntitys.hasNext()){
            currentCoordinate = iteratorOfEntitys.next();
            currentEntity = world.getEntity(currentCoordinate);
            signOfEntity = getCharSignOfEntity(currentEntity);
            worldAsArray[currentCoordinate.getY()][currentCoordinate.getX()] = signOfEntity;
        }
        return worldAsArray;
    }

    private void drawMapEmoji (char[][] world)
    {
        for(int i = 0; i < world.length; i++){
            for(int j = 0; j < world[i].length; j++){
                System.out.print(getEmoji(world[i][j]));
            }
            System.out.print(NEW_LINE);
        }
    }

    private static char getCharSignOfEntity(Entity entity)
    {
        char sign = '!';
        String asStringEntityClass = entity.getClass().getSimpleName();
        switch (asStringEntityClass) {
            case "Tree" -> sign = TREE_CHAR;
            case "Grass" -> sign = GRASS_CHAR;
            case "Rock" ->  sign = ROCK_CHAR;
            case "Herbivore" -> sign = HERBIVORE_CHAR;
            case "Predator" -> sign = PREDATOR_CHAR;
            case "Turtle" -> sign = TURTLE_CHAT;
            case "Fish" -> sign = FISH_CHAR;
        }
        return sign;
    }

    private String getEmoji(char signOfEntyty){
        String emoji = "";
        switch(signOfEntyty){
            case TREE_CHAR -> emoji = TREE_EMOJI;
            case GRASS_CHAR -> emoji = GRASS_EMOJI;
            case ROCK_CHAR -> emoji = ROCK_EMOJI;
            case HERBIVORE_CHAR -> emoji = HERBIVORE_EMOJI;
            case PREDATOR_CHAR -> emoji = PREDATOR_EMOJI;
            case SPACE_CHAR -> emoji = SPACE_EMOJI;
            case TURTLE_CHAT -> emoji = TURTLE_EMOJI;
            case FISH_CHAR -> emoji = FISH_EMOJI;
        }
        return emoji;
    }

}
