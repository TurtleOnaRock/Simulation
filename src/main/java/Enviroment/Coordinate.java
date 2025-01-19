package Enviroment;

import java.util.Objects;
import java.util.Random;

public class Coordinate {

    private final int x;
    private final int y;
    private static Random random = new Random();

    public Coordinate (int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return this.x == that.x && this.y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public static Coordinate getRandomCoordinate(Coordinate max){
        return new Coordinate(random.nextInt(max.getX()), random.nextInt(max.getY()));
    }

    public Coordinate change(int dy, int dx){
        int x = this.x + dx;
        int y = this.y + dy;
        return new Coordinate(x, y);
    }

    public boolean isValued(Coordinate maxValue) {
        if( (this.x > maxValue.getX()) || (this.y > maxValue.getY()) ) {
            return false;
        }
        if( (this.x < 0) || (this.y < 0) ) {
            return false;
        }
        return true;
    }

    @Override
    public String toString (){
        StringBuilder str = new StringBuilder();
        str.append(' ').append(this.x).append(',').append(this.y).append(' ');
        return str.toString();
    }
}
