package frc.robot;

/**
 * represents a configuration of planar movement on a grid
 */
public class PlanarConfiguration {
    
    int x;
    int y;
    int heading;

    /**
     * constructs a planar configuration 
     * @param x x val on grid
     * @param y y val on grid
     * @param heading heading on grid
     */ 
    public PlanarConfiguration(int x, int y, int heading){
        this.x = x;
        this.y = y;
        this.heading = heading;
    }
    
    /**
     * Constructs a planar configuration with values of another configuration
     * @param toCopy the configuration to copy
     */
    public PlanarConfiguration(PlanarConfiguration toCopy){
        x = toCopy.getX();
        y = toCopy.getY();
        heading = toCopy.getHeading();
    }

    /**
     * Sets current configuration to the values of another configuration
     * @param toCopy the configuration to copy
     */
    public void CopyFrom(PlanarConfiguration toCopy){
        x = toCopy.getX();
        y = toCopy.getY();
        heading = toCopy.getHeading();
    }
    
    /**
     * prints the current configuration
     */
    public void printConfig(){
        System.out.print("  { ");
        System.out.print(x);
        System.out.print(", ");
        System.out.print(y);
        System.out.print(", ");
        System.out.print(heading);
        System.out.print(" }  ");
    }

    /**
     * gets the X location
     * @return X location
     */
    public int getX(){
        return x;
    }
    
    /**
     * gets the Y location
     * @return Y location
     */
    public int getY(){
        return y;
    }

    
    /**
     * gets the configuration heading 
     * @return configuration heading
     */
    public int getHeading(){
        return heading;
    }
    /**
     * sets the configuration heading 
     * @param heading configuration heading
     */
    public void setHeading(int heading){
        this.heading = heading;
    }

}
