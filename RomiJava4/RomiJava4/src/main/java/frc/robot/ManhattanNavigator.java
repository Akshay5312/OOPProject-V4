package frc.robot;

/**
 * A planner that generates a path along each degree of freedom at a time. 
 */
public class ManhattanNavigator implements iPlanner {
    Grid grid;
    int[] dofs;
    Field<DNode> field;

    /**
     * Constructor of a manhattan planner
     * @param grid the workspace
     */
    public ManhattanNavigator(Grid grid){
        this.grid = grid;
        dofs = grid.getSize();
        field = new Field<DNode>(dofs);
    }


    /**
     * gets the path from a source configuration to a destination configuration
     * @param Source The source configuration
     * @param Target the target configuration
     * @return the path from Source to Target
     */
    public Path getPath(PlanarConfiguration Source, PlanarConfiguration Target){

        
        PlanarConfiguration currInPath = new PlanarConfiguration(Source);
        Path newPath = new Path(Source);
        if(!grid.inBounds(Target).get()){
            return newPath;
        }
        while(
            ((Target.getX() - currInPath.getX()) != 0)||
            ((Target.getY() - currInPath.getY()) != 0)
        ){
            int xDiff = Target.getX() - currInPath.getX();
            int yDiff = Target.getY() - currInPath.getY();

            if(xDiff != 0){
                currInPath = new PlanarConfiguration(currInPath.getX() + Integer.signum(xDiff), currInPath.getY(), currInPath.getHeading());
            }else if(yDiff != 0){
                currInPath = new PlanarConfiguration(currInPath.getX(), currInPath.getY() + Integer.signum(yDiff), currInPath.getHeading());
            }

            newPath.addConfig(currInPath);
        }
        
        return newPath;

    }


    
}
