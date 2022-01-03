package frc.robot;

import java.util.ArrayList;
import java.util.List;

/**
 * A 2D grid workspace
 */
public class Grid implements CSpace {

    MutableBoolean[][] BoolOc;
    int sX;
    int sY;

    /**
     * constructs a grid
     * @param sY the Y dimension size
     * @param sX the X dimension size
     */
    public Grid(int sY, int sX){
        this.sX = sX;
        this.sY = sY;
        BoolOc = new MutableBoolean[sY][sX];
        for(int i = 0; i < sX; i++){
            for(int j = 0; j < sY; j++){
                BoolOc[j][i] = new MutableBoolean();

                BoolOc[j][i].set(true);
            }
        }
    }

    /**
     * gets the size as an array
     * @return an array representing Ysize, Xsize
     */
    public int[] getSize(){
        int[] retVal = {sY,sX};
        return retVal;
    }

    /**
     * gets the free neighbors of index y,x
     * @param y the y value
     * @param x the x value
     * @return a list of indeces
     */
    public List<int[]> getFreeNeighborIndeces(int y, int x){
        int[][] Neighbors = {{y,x-1,3},{y,x+1,1},{y-1,x,2},{y+1,x,0}};
        ArrayList<int[]> NBs = new ArrayList<int[]>();
        for(int[] N : Neighbors){
            if(isFree(N[0],N[1]).get()){
                NBs.add(N);
            }
        }
        return NBs;
    }

    /**
     * implements CSpace's isfree() at configuration x y
     * @param y y index
     * @param x x index
     * @return if configuration is free
     */
    private MutableBoolean isFree(int y, int x){
        if((x < 0) || (x >= sX)){
            return new MutableBoolean(false);
        }
        if((y < 0) || (y >= sY)){
            return new MutableBoolean(false);
        }

        return BoolOc[y][x];
    }

    /**
     * Checks if a configuration is in bounds
     * @param C the configuration to check
     * @return if it's in bounds
     */
    public MutableBoolean inBounds(PlanarConfiguration C){
        int x = C.getX();
        int y = C.getY();
        if((x < 0) || (x >= sX)){
            return new MutableBoolean(false);
        }
        if((y < 0) || (y >= sY)){
            return new MutableBoolean(false);
        }
        return new MutableBoolean(true);
    }

    /**
     * implements CSpace's isfree() at configuration C
     * @param C the configuration to test 
     * @return if C is an available configuration
     */
    public MutableBoolean isFree(PlanarConfiguration C){
        return isFree(C.getY(), C.getX());
    }

    /**
     * sets a configuration C as occupied
     * @param C The configuration to set as occupied
     */
    public void setObstacle(PlanarConfiguration C){
        BoolOc[C.getY()][C.getX()].set(false);
    }
}
