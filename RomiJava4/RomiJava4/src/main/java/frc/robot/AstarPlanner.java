package frc.robot;

/**
 * planner that is used to get the path to a target node using A* algorithm
 * It is of type DAPlanner, and uses an ANode as the Node
 */
public class AstarPlanner extends DAPlanner<ANode> {
    /**
     * A* constructor that uses a grid 
     * @param grid the 2D workspace
     */
    AstarPlanner(Grid grid){
        super(grid);
    }
    
    /**
     * Creates a node used for A*planning
     * used because you can't instantiate an ANode in DAPlanner, a generic
     * 
     * @return a new ANode
     */
    protected ANode createNode(){
        return new ANode();
    }

}
