package FFS;

/**
 * Created by Hans on 27/03/14.
 */
public class Minomaker {

    public static int getNumberOfTypes(){
        SquareType[] types = SquareType.values();
        return types.length-3;
    }

    public static Poly getPoly(int n){
        switch(n){
            case 0: return polyLONG();
            case 1: return polySHORT();
            case 2: return polyDROPLET();
        }
        return null;
    }


    public static Poly getSpiderPoly(){
        return polySPIDER();
    }



    private static Poly polyLONG(){
        SquareType[][] LONG = new SquareType[3][1];
        LONG[0][0] = SquareType.LONG;
        LONG[1][0] = SquareType.LONG;
        LONG[2][0] = SquareType.LONG;
        return new Poly(LONG,3,1);
    }


    private static Poly polySHORT(){
        SquareType[][] SHORT = new SquareType[1][1];
        SHORT[0][0] = SquareType.SHORT;
        return new Poly(SHORT,1,1);
    }

    private static Poly polyDROPLET(){
        SquareType[][] DROPLET = new SquareType[1][1];
        DROPLET[0][0] = SquareType.DROPLET;
        return new Poly(DROPLET,1,1);
    }

    private static Poly polySPIDER(){
        SquareType[][] SPIDER = new SquareType[1][1];
        SPIDER[0][0] = SquareType.SPIDER;
        return new Poly(SPIDER,1,1);
    }

}
