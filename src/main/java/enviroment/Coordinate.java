package enviroment;

import java.util.Objects;

public class Coordinate {

    private final int width;
    private final int height;

    public Coordinate (int width, int height){
        this.width = width;
        this.height = height;
    }

    public int getWidth(){
        return this.width;
    }

    public int getHeight(){
        return this.height;
    }

    public Coordinate add(int dWidth, int dHeight){
        int Width = this.width + dWidth;
        int Height = this.height + dHeight;
        return new Coordinate(Width, Height);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return this.width == that.width && this.height == that.height;
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, height);
    }

    @Override
    public String toString (){
        return " " + this.width + "," + this.height + " ";
    }
}
