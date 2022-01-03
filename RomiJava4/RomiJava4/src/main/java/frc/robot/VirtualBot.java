package frc.robot;

/**
 * A virtual grid turtle robot
 * 
 */
public class VirtualBot extends GridTurtle {

    /**
     * Constructs a virtual grid robot with a starting configuration
     * @param startConfig the starting configuration 
     */
    public VirtualBot(PlanarConfiguration startConfig){
        super(startConfig);
    }

    /**
     * moves the virtual robot in the direction of the heading
     */
    public boolean move(){
        return true;
    }
    /**
     * turns the virtual robot in the direction passed
     * @param destHeading the destination heading
     */
    public boolean turnTo(int destHeading){
        return true;
    }

    /**
     * checks if there is an obstacle in front of the virtual robot
     * 
     * for virtual robots this always returns false
     */
    public boolean isObstacle(){
        return false;
    }
}
