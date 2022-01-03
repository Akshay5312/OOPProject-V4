package frc.robot;

import java.util.ArrayList;
import java.util.List;


/**
 * A node based planner
 */
public abstract class DAPlanner<N extends Node> implements iPlanner {
    Grid grid;
    PQ<N> Q = new PQ<N>();
    int[] dofs;
    Field<N> field;

    /**
     * Constructor of a Node populating style planner
     * @param grid the workspace
     */
    public DAPlanner(Grid grid){
        this.grid = grid;
        dofs = grid.getSize();
        field = new Field<N>(dofs);
    }

    /**
     * Implemented to create a new node of type N
     * @return a new node of type N
     */
    protected abstract N createNode();

    /**
     * Starts an operation to populate each of the nodes in the workspace.
     * @param Source the starting configuration
     * @param Target the target ending configuration
     */
    private void startOp(PlanarConfiguration Source, PlanarConfiguration Target){
        N nNode = createNode();
        nNode.setPrevnode(null);
        nNode.setConfiguration(Source);
        int Y = Source.getY();
        int X = Source.getX();
        int[] arr = {Y,X};
        field.put(arr, nNode);
        Q.add(field.get(arr));
        while(Q.size() > 0){
            N toAnalyze = Q.pullLeast();
            for(int[] nbs : grid.getFreeNeighborIndeces(
                    toAnalyze.getConfiguration().getY(),
                    toAnalyze.getConfiguration().getX()
                    )
                ){

                    N newNode = createNode();
                    PlanarConfiguration configIndex = new PlanarConfiguration(nbs[1], nbs[0], nbs[2]);
                    newNode.setConfiguration(configIndex);
                    newNode.setPrevnode(toAnalyze);
                    newNode.updateCost(Target);
                    int[] ind = {configIndex.getY(), configIndex.getX()};
                    N prevNode = field.get(ind);
                    
                    if( (prevNode == null)||(prevNode.cost() > newNode.cost()) ){
                        field.put(ind, newNode);
                        Q.add(newNode);
                    }
                    
            }
        }
        
        //System.out.println("____________");
    }


    /**
     * resets the workspace
     */
    private void reset(){
        field = new Field<N>(dofs);
        Q = new PQ<N>();
    }

    /**
     * returns the path to a target node from a start node. If target node is innaccesible it returns a path to an accessible node closest to the target
     * 
     * @param Source the starting configuration
     * @param Target the target ending configuration
     */
    public Path getPath(PlanarConfiguration Source, PlanarConfiguration Target){
        
        reset();
        
        startOp(Source, Target);
        

        Path retP = new Path();
        PlanarConfiguration retConfig =  new PlanarConfiguration(Target);
        
        if(field.get(Target.getY(), Target.getX()) == null){

             int[] ind = {Source.getY(), Source.getX()};
             double min = field.get(Source.getY(), Source.getX()).heuristic(Target);
             retConfig = Source;
             System.out.println();
             for(N n : field.everyElement()){

                 if(n == null){
                     
                 }else{
                     if(min > n.heuristic(Target)){
                         min = n.heuristic(Target);
                         retConfig = n.getConfiguration();
                     }

                 }

             }

        }

        List<Node> P = (field.get(
            retConfig.getY(), retConfig.getX()
            ).getPathTo());

        retP.addConfig(Source);
        for(Node p: P){
            retP.addConfig(p.getConfiguration());
        }
        
        return retP;
    }
    
}
