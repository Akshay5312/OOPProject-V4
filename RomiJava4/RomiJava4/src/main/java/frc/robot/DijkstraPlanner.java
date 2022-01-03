package frc.robot;
/**
 * planner that is used to get the path to a target node using Dijkstras algorithm
 * It is of type DAPlanner, and uses an DNode as the Node
 */
public class DijkstraPlanner extends DAPlanner<DNode> {
    /**
     * constructs a planner with a workspace
     * @param grid the workspace
     */
    DijkstraPlanner(Grid grid){
        super(grid);
    }
    
    /**
     * Creates a node used for Djikstra planning
     * used because you can't instantiate an DNode in DAPlanner, a generic
     * 
     * @return a new DNode
     */
    protected DNode createNode(){
        return new DNode();
    }
}
