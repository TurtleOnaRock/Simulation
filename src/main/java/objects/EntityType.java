package objects;

import java.util.Random;

public enum EntityType {
    GRASS,
    ROCK,
    TREE,
    HERBIVORE,
    PREDATOR,
    FISH,
    TURTLE;

    private static final Random random = new Random();

    public static EntityType getRandomEntityType() {
        EntityType[] types = values();
        return types[random.nextInt(types.length)];
    }
}
