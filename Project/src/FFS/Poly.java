package FFS;

/**
 * Created by Hans on 27/03/14.
 */
public class Poly {
    private int width;
    private int height;
    private SquareType[][] polytype;
    private SquareType square;

    public Poly(SquareType[][] type, int height, int width){
        this.polytype = type;
        this.height = height;
        this.width = width;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public SquareType getSquare() {
        return square;
    }

    public SquareType[][] getPolytype() {
        return polytype;
    }
    public void setPosXY(int x, int y){
        square = polytype[x][y];
    }
    public SquareType getSquaretypeAtXY(int x, int y){
        return polytype[x][y];
    }
}
