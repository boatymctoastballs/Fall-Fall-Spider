package FFS;

/**
 * Created by Hans on 27/03/14.
 */
public class Minomaker {

    public static int getNumberOfTypes(){
        SquareType[] types = SquareType.values();
        return types.length-2;
    }

    public static Poly getPoly(int n){
        switch(n){
            case 0: return polyOBSTACLE();
            case 1: return polySPIDER();
        }
        return null;
    }


    public static Poly getSpiderPoly(){
        return polySPIDER();
    }


    private static Poly polyOBSTACLE(){
        SquareType[][] OBSTACLE = new SquareType[1][1];
        OBSTACLE[0][0] = SquareType.OBSTACLE;
        return new Poly(OBSTACLE,1,1);
    }


    private static Poly polySPIDER(){
        SquareType[][] SPIDER = new SquareType[1][1];
        SPIDER[0][0] = SquareType.SPIDER;
        return new Poly(SPIDER,1,1);
    }

}
