package frc.robot;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a node in the grid
 */
public class Node implements Costable{

    PlanarConfiguration config;
    Node prevNode = null;
    double cost = 0;
    boolean visited = false;
    
    /**
     * node constructor
     */
    public Node(){
        
    }
    
    /**
     * Sets the configuration the node is at
     * @param C configuration to set
     */
    public void setConfiguration(PlanarConfiguration C){
        config = new PlanarConfiguration(C);
    }
    
    /**
     * sets the node this node arrived from
     * @param prevnode the node that led to this node
     */
    public void setPrevnode(Node prevnode){
        visited = true;
        prevNode = prevnode;
    }

    /**
     * updates cost at node depending on implementation 
     * @param Target the target configuration
     */
    public void updateCost(PlanarConfiguration Target){}
    
    /**
     * gets the configuration at the node
     * @return configuration at the node
     */
    public PlanarConfiguration getConfiguration(){
        return config;
    }

    /**
     * gets the nodes from the source to this node
     * @return The path as a list of nodes
     */
    public List<Node> getPathTo(){
        List<Node> retL = new ArrayList<Node>();
        retL.add(this);
        if(prevNode == null ){
            //do nothing
        }else{
            retL.addAll(prevNode.getPathTo());
        }
        return retL;
    }



    /**
     * Checks if node has been populated
     * @return if node has been populated
     */
    public boolean isVisited(){
        return visited;
    }

    /**
     * gets the cost at the node
     * @return cost at the node
     */
    public double cost(){
        return cost;
    }

    /**
     * Gets an approximate distance to the target
     * @param Target the target configuration 
     * @return the approximate cost of move to target
     */
    public double heuristic(PlanarConfiguration Target){
        return Math.abs(Target.getX() - config.getX()) + Math.abs(Target.getY() - config.getY());
    }


}
