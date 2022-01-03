package frc.robot;

/**
 * ANode is a node that implements "update cost" in order to use the Astar path planning method
 * 
 * 
 */
public class ANode extends Node {
    /**
     * updates the cost at a node
     * For ANode this includes the estimated cost to a target
     * @param Target the target configuration
     */
    public void updateCost(PlanarConfiguration Target){
        if((prevNode != null) && (config != null)){
            
            cost = prevNode.cost()+1;
            
            if(prevNode.getConfiguration().getHeading() != config.getHeading()){
                cost++;
            }

            cost += heuristic(Target);

        }
    }


}
