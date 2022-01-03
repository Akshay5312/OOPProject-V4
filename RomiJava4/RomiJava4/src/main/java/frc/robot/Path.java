package frc.robot;

import java.util.ArrayList;
import java.util.List;


/**
 * represents a path of onfigurations
 */
public class Path {
    List<PlanarConfiguration> P = new ArrayList<PlanarConfiguration>();
    PlanarConfiguration LastPose = new PlanarConfiguration(0,0,0);
    
    /**
     * constructor for a path
     */
    public Path(){}
    
    /**
     * Constructor for a path with an initial configuration
     * @param init the initial configuration
     */
    public Path(PlanarConfiguration init){
        P.add(init);
    }
    
   

    /**
     * prints the formatted path 
     */
    public void printPath(){
        for(int i = 0; i < P.size(); i++){
            P.get(i).printConfig();
        }
    }

    /**
     * gets the path as a list of configurations
     * @return gets the path as a list of configurations
     */
    public List<PlanarConfiguration> getPath(){
        return P;
    }

    /**
     * gets the most recent configuration
     * @return gets the last configuration
     */
    public PlanarConfiguration getConfig(){
        //return P.get(P.size()-1);
        return LastPose;
    }

    /**
     * gets recent configurations x val
     * @return XVal of recent configuration
     */
    public int getX(){
        return getConfig().getX();
    }
 
    /**
     * gets recent configurations y val
     * @return YVal of recent configuration
     */   
    public int getY(){
        return getConfig().getY();
    }

    /**
     * gets recent configurations heading
     * @return heading of recent configuration
     */     
    public int getheading(){
        return getConfig().getHeading();
    }

    /**
     * adds a configuration to the path
     * @param toAdd the configuration to add
     */
    public void addConfig(PlanarConfiguration toAdd){
        P.add(toAdd);
        LastPose.CopyFrom(toAdd);
    }

    /**
     * copies a path element by element
     * @param PathToCopy the path to copy
     */
    public void copy(Path PathToCopy){
        P = new ArrayList<PlanarConfiguration>();
        for(PlanarConfiguration p : PathToCopy.getPath()){
            P.add(p);
        }
    }
}
