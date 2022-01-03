package frc.robot;


/**
 * DNode is a node that implements "update cost" in order to use the Dijkstra path planning method
 */
public class DNode extends Node {
    
    /**
     * updates the cost at a node
     * @param Target the target configuration
     */
    public void updateCost(PlanarConfiguration Target){
        if((prevNode != null) && (config != null)){
            
            cost = prevNode.cost()+1;
            
            if(prevNode.getConfiguration().getHeading() != config.getHeading()){
                cost++;
            }

        }
    }

}
